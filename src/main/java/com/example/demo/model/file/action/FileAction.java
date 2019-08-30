
package com.example.demo.model.file.action;


import com.example.demo.common.base.BaseController;
import com.example.demo.common.dto.ResultDto;
import com.example.demo.common.exception.exception.BusinessException;
import com.example.demo.model.file.manager.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "file")
public class FileAction extends BaseController {

    @Autowired
    private FileManager fileManager;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping(value = "/{module}/upload")
    public ResultDto fileUpload(@RequestParam("file") MultipartFile file, @PathVariable("module") String module,
                                @RequestParam(required = false, value = "validScript") String validScript,
                                HttpServletRequest request) {


        //获取信息
        if (file == null || file.isEmpty()) {
            throw new BusinessException("请选择文件!");
        }

        String fileName = file.getOriginalFilename();

        String moduleDir = String.format("%s/%s", module,
                getLoginUser() == null ? "0" : getLoginUser().getId());
        //保存
        String savePath = fileManager.getUploadDir(moduleDir);
        logger.info("文件保存路径 " + savePath);
        try {
            fileManager.saveTo(String.format("%s/%s", savePath, fileName), file.getInputStream());
        } catch (Exception e) {
            logger.error("文件保存失败!", e);
            throw new BusinessException("文件保存失败!");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("fileName", fileName);
        result.put("filePath", fileManager.getUploadURL(moduleDir, fileName));
        return ResultDto.success(result);

    }
}
