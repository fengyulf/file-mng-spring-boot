package com.fy.filemngspringboot.controller;

import com.fy.filemngspringboot.utils.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 名称：FileUploadController<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 作者：fy <br>
 * 日期：2019/11/29 10:41 <br>
 * 说明：<br>
 */
@RestController
public class FileUploadController {

    /**
     *
     * @param file 文件
     */
    @PostMapping("/uploadSingle")
    public String upload(MultipartFile file){
        try{
            //取得当前上传文件的文件名称
            String originalFilename = file.getOriginalFilename();
            //transferTo是保存文件，参数就是要保存到的目录和名字
            String filePath = "C:\\workspace\\study\\img\\";
            file.transferTo(new File(filePath + originalFilename));
            System.out.println("文件类型："+file.getContentType() + "；\n" + "原文件名："+originalFilename + "；\n" + "是否为空："+file.isEmpty() +
                    "；\n" + "文件大小："+file.getSize());
            return "文件上传完毕";
        }catch (Exception e){
            e.printStackTrace();
            return "文件上传失败";
        }
    }

    /**
     * 多文件上传，大量文件时，防止文件名相同，重新修改存储文件名
     *
     * @param files 文件
     * @return
     */
    @PostMapping("/uploadMultiple")
    public String upload2(MultipartFile[] files) {
        try{
            if(files.length == 0){
                return "请选择要上传的文件";
            }
            for (MultipartFile multipartFile : files) {
                if(multipartFile.isEmpty()){
                    return "文件上传失败";
                }
                byte[] fileBytes = multipartFile.getBytes();
                String filePath = "C:\\workspace\\study\\img\\";
                //取得当前上传文件的文件名称
                String originalFilename = multipartFile.getOriginalFilename();
                //生成文件名
                String fileName = UUID.randomUUID() +"&"+ originalFilename;
                FileUtils.uploadFile(fileBytes, filePath, fileName);
            }

            return "文件上传完毕";
        }catch (Exception e){
            e.printStackTrace();
            return "文件上传失败";
        }
    }

}
