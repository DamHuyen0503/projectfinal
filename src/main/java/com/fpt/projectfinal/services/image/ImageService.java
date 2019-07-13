package com.fpt.projectfinal.services.image;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.fpt.projectfinal.models.Image;


public interface ImageService {
	public String doUpload(HttpServletRequest request, Model model, Image image);
	public InputStream inputStream( String imageName);
}
