package com.fpt.projectfinal.models;

import org.springframework.web.multipart.MultipartFile;

public class Image {
	private MultipartFile fileDatas;
	public MultipartFile getFileDatas() {
		return fileDatas;
	}
	public void setFileDatas(MultipartFile fileDatas) {
		this.fileDatas = fileDatas;
	}
}
