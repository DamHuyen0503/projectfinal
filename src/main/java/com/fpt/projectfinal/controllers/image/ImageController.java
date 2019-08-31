package com.fpt.projectfinal.controllers.image;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.projectfinal.models.Image;
import com.fpt.projectfinal.services.image.ImageService;

@CrossOrigin
@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;
	
    /*
     * Show photo download page.
     * 写真のダウンロードページを表示します。
     */
    @RequestMapping(value = "/uploadOneFile/{imageName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable(name="imageName") String imageName) throws IOException {
      
        return ResponseEntity.ok()
        	      .cacheControl(CacheControl.maxAge(2592000, TimeUnit.SECONDS).cachePublic())
        	      .body(IOUtils.toByteArray(imageService.inputStream(imageName)));
    }
    
    
    /*
     * Processing image downloads.
     * 画像ダウンロードの処理。
     */
    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody String uploadOneFileHandlerPOST(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("image") Image image) {
        return imageService.doUpload(request, model, image);
 
    }
}
