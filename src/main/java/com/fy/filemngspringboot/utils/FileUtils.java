package com.fy.filemngspringboot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 名称：FileUtils<br>
 * 功能：〈功能详细描述〉<br>
 * 方法：〈方法简述 - 方法描述〉<br>
 * 版本：1.0 <br>
 * 作者：fy <br>
 * 日期：2019/11/29 14:09 <br>
 * 说明：<br>
 */
public class FileUtils {

    /**
     * 文件上传方法
     *
     * @param file     文件byte[]
     * @param filePath 文件上传路径
     * @param fileName 文件保存路径
     * @throws IOException
     * @throws Exception   void
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws IOException {

        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
