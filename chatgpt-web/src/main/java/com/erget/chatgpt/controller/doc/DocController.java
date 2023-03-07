package com.erget.chatgpt.controller.doc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.util.StyleUtils;
import com.erget.chatgpt.dto.DocQueryReq;
import com.erget.chatgpt.entity.ChatData;
import com.erget.chatgpt.service.ChatDataStorageService;
import com.erget.chatgpt.util.DateUtils;
import com.erget.chatgpt.util.UserContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController()
public class DocController {

    @Autowired
    ChatDataStorageService chatDataStorageService;

    @Autowired
    UserContextUtil userContextUtil;

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
