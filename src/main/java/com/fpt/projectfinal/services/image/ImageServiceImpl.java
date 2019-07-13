package com.fpt.projectfinal.services.image;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fpt.projectfinal.models.Image;
@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private Environment env;
	
	@Override
	public String doUpload(HttpServletRequest request, Model model, Image image) {
	
		String result = "";
		
        // Thư mục gốc upload file.
        String uploadRootPath = env.getProperty("app.imagepath", "C:\\image\\");
        
        File uploadRootDir = new File(uploadRootPath);
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        List<File> uploadedFiles = new ArrayList<File>();
        List<String> failedFiles = new ArrayList<String>();
        
 
            // Tên file gốc tại Client.
        String name = new Date().getTime() + image.getFileDatas().getOriginalFilename();
            System.out.println("Client File Name = " + name);
 
            if (name != null && name.length() > 0) {
                try {
                    // Tạo file tại Server.
                    File serverFile = new File(uploadRootDir + File.separator +  name );
 
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(image.getFileDatas().getBytes());
                    stream.close();
                    // 
                    uploadedFiles.add(serverFile);
                    result = ""+serverFile;
                    System.out.println("Write file: " + serverFile);
                   return name;
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                    result = ""; 
                }
            }
        return "";
	}

	@Override
	public InputStream inputStream(String imageName) {
		InputStream inputSt = null;
		try {
			inputSt = new FileInputStream(new File(env.getProperty("app.imagepath", "C:\\image\\") + File.separator +  imageName ));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return inputSt;
	}

}
