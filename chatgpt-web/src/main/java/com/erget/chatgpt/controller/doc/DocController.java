package com.erget.chatgpt.controller.doc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.erget.chatgpt.dto.DocQueryReq;
import com.erget.chatgpt.entity.ChatData;
import com.erget.chatgpt.service.ChatDataStorageService;
import com.erget.chatgpt.util.DateUtils;
import com.erget.chatgpt.util.UserContextUtil;
import fun.mingshan.markdown4j.Markdown;
import fun.mingshan.markdown4j.constant.FlagConstants;
import fun.mingshan.markdown4j.type.block.Block;
import fun.mingshan.markdown4j.type.block.StringBlock;
import fun.mingshan.markdown4j.type.block.TableBlock;
import fun.mingshan.markdown4j.type.block.TitleBlock;
import fun.mingshan.markdown4j.writer.MdWriter;
import org.apache.commons.lang.StringUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


@RestController()
public class DocController {

    @Autowired
    ChatDataStorageService chatDataStorageService;

    @Autowired
    UserContextUtil userContextUtil;

    /**
     * 下载markdown文档
     * @param docQueryReq
     * @param response
     */
    @PostMapping("/markdown/download")
    public void downloadMarkdown(@RequestBody DocQueryReq docQueryReq, HttpServletResponse response) throws IOException {
        //标题Block
        TitleBlock secondLevelTitleParamDesc = TitleBlock.builder().level(TitleBlock.Level.SECOND).content("chat对话\n").build();

        //        ArrayList<Block> rows = new ArrayList<>();
//        TableBlock.TableRow tableRowOne = new TableBlock.TableRow();
//        TableBlock.TableRow tableRowTwo = new TableBlock.TableRow();
//        tableRowOne.setRows(Arrays.asList("20", "促销", "仅限本月"));
//        tableRowTwo.setRows(Arrays.asList("40", "促销", "限制本周"));
//        rows.add(tableRowOne);
//        rows.add(tableRowTwo);
//        //表格标题
//        TableBlock tableBlock = TableBlock.builder()
//                .titles(Arrays.asList("价格", "说明", "备注"))
//                .rows(rows)
//                .build();

        LambdaQueryWrapper<ChatData> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<ChatData> wrapper = queryWrapper.ge(ChatData::getCreatedTime, docQueryReq.getStartTime())
                .le(ChatData::getCreatedTime, docQueryReq.getEndTime());
        if (StringUtils.isNotBlank(docQueryReq.getContentType())){
            wrapper = wrapper.eq(ChatData::getContentType,docQueryReq.getContentType());
        }
        List<ChatData> list = chatDataStorageService.list(wrapper);

        Markdown.MarkdownBuilder chat = Markdown.builder()
                .name("chat")
                .block(secondLevelTitleParamDesc);
        for (ChatData chatData : list) {
            if (chatData.getContentType().startsWith("Q_")){
                TitleBlock build = TitleBlock.builder().level(TitleBlock.Level.FOURTH).content(chatData.getContent()).build();
                chat = chat.block(build);
            }else {
                String substring = chatData.getContent().substring(2, chatData.getContent().length() - 2);
                String replace = substring.replace("\\n\\n", "\r\n \t").replace("\\n", "\r\n \t");
                StringBlock build = StringBlock.builder().content(replace+"\r\n").build();
                chat= chat.block(build);
            }
        }
        //构建markdown文件
        Markdown markdown = chat.build();

        //提供下载
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition",
                "attachment;filename=chat_" + DateUtils.getNowDateShort() + ".md");
        try (OutputStream fos = response.getOutputStream();
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {
            String[] arrs = markdown.toString().split(FlagConstants.LINE_BREAK);
            for (String arr : arrs) {
                bw.write(arr + "\r\n");
            }
        }

    }
    @PostMapping("/word/download")
    public void download(@RequestBody DocQueryReq docQueryReq, HttpServletResponse response){
        try {
            XWPFTemplate document = generateWordXWPFTemplate(docQueryReq);
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition",
                    "attachment;filename=chat_" + DateUtils.getNowDateShort() + ".docx");
            OutputStream os = response.getOutputStream();
            document.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private XWPFTemplate generateWordXWPFTemplate(DocQueryReq docQueryReq) throws IOException {

        LambdaQueryWrapper<ChatData> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<ChatData> wrapper = queryWrapper.ge(ChatData::getCreatedTime, docQueryReq.getStartTime())
                .le(ChatData::getCreatedTime, docQueryReq.getEndTime());
        if (StringUtils.isNotBlank(docQueryReq.getContentType())){
            wrapper = wrapper.eq(ChatData::getContentType,docQueryReq.getContentType());
        }
        List<ChatData> list = chatDataStorageService.list(wrapper);

        Map<String, Object> content = new HashMap<>();
        content.put("title", "Chat 对话");
        content.put("author", StringUtils.isBlank(userContextUtil.getUserName()) ? "至尊宝":userContextUtil.getUserName());
        content.put("dateStart",docQueryReq.getStartTime());
        content.put("dateEnd",docQueryReq.getEndTime());
//        content.put("poiList", Numberings.create("excel03只能打开xls格式，无法直接打开xlsx格式",
//                "xls只有65536行、256列; xlsx可以有1048576行、16384列",
//                "xls占用空间大, xlsx占用空间小，运算速度也会快一点"));

        ArrayList<TextRenderData> arrayList = new ArrayList<>();
        list.forEach(x->{
            if (x.getContentType().startsWith("Q_")){
                TextRenderData textRenderData = Texts.of("提问：").create();
                Style build = Style.builder().build();
                build.setFontFamily("微软雅黑");
                build.setColor("00FF00");
                build.setFontSize(8);
                textRenderData.setStyle(build);
                textRenderData.setText(x.getContent());
                arrayList.add(textRenderData);
            }else {
                TextRenderData textRenderData = Texts.of("回答：").create();
                String substring = x.getContent().substring(2, x.getContent().length() - 2);
                String replace = substring.replace("\\n\\n", "\r\n \t").replace("\\n", "\r\n \t");
                Style build = Style.builder().build();
                build.setFontFamily("微软雅黑");
                build.setFontSize(8);
                textRenderData.setStyle(build);
                textRenderData.setText(replace);
                arrayList.add(textRenderData);
            }
        });

        Numberings.NumberingBuilder numberingBuilder = Numberings.of(NumberingFormat.DECIMAL_PARENTHESES);
        arrayList.forEach(x->{
            numberingBuilder.addItem(x);
        });

        content.put("poiList",numberingBuilder.create());

//        RowRenderData headRow = Rows.of("ID", "Description").textColor("FFFFFF")
//                .bgColor("4472C4").center().create();
//        TableRenderData table = Tables.create(headRow);
//        list.forEach(a -> table.addRow(Rows.create(a.getId() + "", a.getContent())));
//        content.put("poiTable", table);

//        Resource resource = new ClassPathResource("pdai-guli.png");
//        content.put("poiImage", Pictures.ofStream(new FileInputStream(resource.getFile())).create());
        File file = new ClassPathResource("template/poi-tl-template.docx").getFile();
        return XWPFTemplate.compile(file).render(content);
    }

}
