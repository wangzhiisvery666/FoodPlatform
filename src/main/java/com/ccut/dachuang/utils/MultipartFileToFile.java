package com.ccut.dachuang.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class MultipartFileToFile {
 
    /**
     * MultipartFile 转 File
     */
    public static File toFileOne(MultipartFile file) throws Exception {
 
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }
 
    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 删除本地临时文件
     */
    public static void delteTempFile(File file) {
    if (file != null) {
        File del = new File(file.toURI());
        del.delete();
    }
}

    /**
     * 方法二利用 transferTo()
     * @param file
     * @return
     */
    public  static File toFileTwo(MultipartFile file){

        try {
            //建立工作目录下的临时文件
            File file1 = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + file.getName());
            //创建临时文件文件
            file1.createNewFile();
            //将前端传来的文件存到临时文件中
            file.transferTo(file1);
            return  file1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}