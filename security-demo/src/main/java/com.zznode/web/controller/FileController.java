package com.zznode.web.controller;

import com.zznode.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @Author: sc
 * @Descriptions:
 * @Date: Create in 21:37 2018/2/7
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        String folder = "D:\\";
        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void downLoad(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try (
                InputStream inputStream = new FileInputStream(new File("D:\\", id + ".txt"));
                OutputStream outputStream = response.getOutputStream()
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
        }

    }
}
