package com.example.demo.model.pic.controller;


import com.example.demo.common.base.BaseController;
import com.example.demo.common.dto.ParamDto;
import com.example.demo.common.dto.ResultDto;
import com.example.demo.common.exception.exception.BusinessException;
import com.example.demo.model.file.manager.FileManager;
import com.example.demo.model.pic.dto.PicPo;
import com.example.demo.model.pic.service.PicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "pic")
public class PicController extends BaseController {
    @Autowired
    private PicService picService;
    @Autowired
    private FileManager fileManager;

    @GetMapping(value = "page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto page() {
        ParamDto paramDto = getParam();
        return ResultDto.success(picService.page(paramDto, PicPo.class));
    }

    @PostMapping(value = "/{type}/upload")
    public ResultDto fileUpload(@RequestParam("file") MultipartFile file, @PathVariable("type") String module,
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
        log.info("文件保存路径 " + savePath);
        try {
            fileManager.saveTo(String.format("%s/%s", savePath, fileName), file.getInputStream());
        } catch (Exception e) {
            throw new BusinessException("文件保存失败!");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("fileName", fileName);
        result.put("filePath", fileManager.getUploadURL(moduleDir, fileName));

        PicPo picPo = new PicPo();
        picPo.setType(module);
        picPo.setFileName(fileName);
        picPo.setFilePath(fileManager.getUploadURL(moduleDir, fileName));
        picPo.setCreateTime(new Date());
        picService.create(picPo);
        return ResultDto.success(result);
    }

}
