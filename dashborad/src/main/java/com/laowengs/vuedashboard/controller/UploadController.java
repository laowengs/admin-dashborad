package com.laowengs.vuedashboard.controller;

import com.laowengs.vuedashboard.common.Result;
import com.laowengs.vuedashboard.vueadmindb.po.VueRole;
import com.laowengs.vuedashboard.vueadmindb.po.VueUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public Result<String> list(@RequestParam("file") MultipartFile file,@RequestParam("file2") MultipartFile file2, VueUser user){

        System.out.println(user);
        // 获取文件存储路径（绝对路径）
        String path = "E:\\workspace\\admin-dashborad\\logs\\file";
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        try {
            file.transferTo(filePath);
            file2.transferTo(new File(path, file2.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.getInstance(0,"","success");
    }
}
