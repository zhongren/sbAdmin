package com.example.demo.model.file.manager;


import com.example.demo.common.util.PropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Component
@Slf4j
public class FileManager {


    public String saveTo(String absolutePath, InputStream input) {
        if (absolutePath == null || absolutePath.trim().length() == 0) {
            throw new IllegalArgumentException("文件保存路径为空!");
        }
        absolutePath = getCleanPath(absolutePath);
        File file = new File(absolutePath);
        if (file.exists()) {
            file.delete();
        }
        //throw new BusinessException("文件名称已存在!") ;

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            while (IOUtils.read(input, buff) != 0) {
                IOUtils.write(buff, output);
            }
        } catch (Exception e) {
            log.error("保存文件出错", e);
        } finally {
            try {
                output.close();
                input.close();
            } catch (Exception e) {
                log.error("文件流关闭错误", e);
            }
        }
        return absolutePath;
    }

    public String getUploadDir(String module) {
        if (module == null || (module = module.trim()).length() == 0)
            module = "";
        else
            module += "/";
        return getCleanPath(String.format("%s/%s/%s", PropertyUtil.getProperty("fileUploadPath", ""), PropertyUtil.getProperty("fileUploadMapping", ""), module));
    }

    public String getUploadURL(String module, String fileName) {
        if (module == null || (module = module.trim()).length() == 0)
            module = "";
        else
            module += "/";
        return getCleanPath(String.format("%s/%s/%s/%s", PropertyUtil.getProperty("fileServerDomain", ""),
                PropertyUtil.getProperty("fileUploadMapping", ""), module, fileName));
    }

    public String getCleanPath(String path) {
        if (path == null || path.trim().length() == 0) {
            return path;
        }
        String proto = path.substring(0, path.indexOf("://") + 3);
        path = path.replace(proto, "").replaceAll("/{2,}", "/").replaceAll("\\\\+", "/");
        return proto + path;
    }

}
