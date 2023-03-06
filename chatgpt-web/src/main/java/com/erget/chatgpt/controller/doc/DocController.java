package com.erget.chatgpt.controller.doc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.erget.chatgpt.dto.DocQueryReq;
import com.erget.chatgpt.entity.ChatData;
import com.erget.chatgpt.service.ChatDataStorageService;
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
                    "attachment;filename=user_word_" + System.currentTimeMillis() + ".docx");
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
        content.put("author", userContextUtil.getUserName());
        content.put("site", new HyperlinkTextRenderData("https://www.baidu", "https://www.baidu"));

        content.put("poiText", "Apache POI 是创建和维护操作各种符合Office Open XML（OOXML）标准和微软的OLE 2复合文档格式（OLE2）的Java API。用它可以使用Java读取和创建,修改MS Excel文件.而且,还可以使用Java读取和创建MS Word和MSPowerPoint文件。更多请参考[官方文档](https://poi.apache.org/index.html)");

        content.put("poiText2", "生成xls和xlsx有什么区别？POI对Excel中的对象的封装对应关系？");
        content.put("poiList", Numberings.create("excel03只能打开xls格式，无法直接打开xlsx格式",
                "xls只有65536行、256列; xlsx可以有1048576行、16384列",
                "xls占用空间大, xlsx占用空间小，运算速度也会快一点"));

        RowRenderData headRow = Rows.of("ID", "Description").textColor("FFFFFF")
                .bgColor("4472C4").center().create();
        TableRenderData table = Tables.create(headRow);
        list.forEach(a -> table.addRow(Rows.create(a.getId() + "", a.getContent())));
        content.put("poiTable", table);

//        Resource resource = new ClassPathResource("pdai-guli.png");
//        content.put("poiImage", Pictures.ofStream(new FileInputStream(resource.getFile())).create());
        File file = new ClassPathResource("template/poi-tl-template.docx").getFile();
//       new XWPFDocument();
//        XWPFTemplate.compile()
        return XWPFTemplate.compile(file).render(content);
    }

}
