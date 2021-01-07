package com.aadhar.app.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aadhar.app.model.AadharCard;

import org.springframework.util.StringUtils;

@Controller
@RequestMapping("/owner")
public class OwnerController {
	
	@GetMapping("/dashboard")
	public String getOwnerHomePage() {
		return "owner/dashboard";		
	}
	@GetMapping("/addaadharpage")
	public String getAadharForm(Model model) {
		AadharCard aadharCard = new AadharCard();
		 long numb = (long)(Math.random() * 10000000 * 100000);
			aadharCard.setAadharNo(String.valueOf(numb));
		model.addAttribute("theAadhar", aadharCard);
		return "owner/aadharform";		
	}
	@GetMapping("/updateaadharpage")
	public String getupdateAadharForm(Model model) {
		AadharCard aadharCard = new AadharCard(); 
		 long numb = (long)(Math.random() * 10000000 * 100000);
			aadharCard.setAadharNo(String.valueOf(numb));
		model.addAttribute("theAadhar", aadharCard);
		return "owner/updateuser";		
	}
	@PostMapping("/addaadharcard")
	public String addAadharCard(AadharCard aadharCard,@RequestParam("photo") MultipartFile multipartFile) throws IOException, SQLException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); 
		Blob bl=getBlobData(multipartFile);
		aadharCard.setImage(bl);
		System.out.println(aadharCard);
		System.out.println(aadharCard.getImage());
		return "owner/dashboard";		
	} 
	 public static Blob getBlobData(MultipartFile file) throws IOException, SQLException {
	        byte[] bytes = file.getBytes();
	        return new SerialBlob(bytes);
	    }

}
