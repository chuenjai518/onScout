package com.uow.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.uow.DAO.ApiDAO;
import com.uow.Model.CompletedQuest;
import com.uow.Model.UserInfo;

@Service
public class ApiService {
	@Autowired
	ApiDAO apiDAO;

	public List<CompletedQuest> getSPAFinishDate(String username) {
		return apiDAO.getSPAFinishDate(username);
	}

	public List<CompletedQuest> getSSAFinishDate(String username) {
		return apiDAO.getSSAFinishDate(username);
	}

	public List<CompletedQuest> getSAAFinishDate(String username) {
		return apiDAO.getSAAFinishDate(username);
	}

	public List<CompletedQuest> getCSAFinishDate(String username) {
		return apiDAO.getCSAFinishDate(username);
	}
	
	public UserInfo getUserInfo(String username){
		return apiDAO.getUserInfo(username);
	}

	public byte[] pdfGerenate(String username, String templatePath)
			throws DocumentException, IOException, URISyntaxException {

		File file = new File(getClass().getClassLoader().getResource(templatePath).toURI());
		System.out.println(file.getPath());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfReader pdfReader = new PdfReader(file.getPath());
		PdfStamper pdfStamper = new PdfStamper(pdfReader, baos);
		AcroFields fields = pdfStamper.getAcroFields();
		
		UserInfo user = getUserInfo(username);
		
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
	
		fields.setField("fill_2", user.getLastName() + " " + user.getFirstName());
		fields.setField("fill_3", dt1.format(user.getDOB()).toString());
		
		Date today =  new Date();
		int age = today.getYear() - user.getDOB().getYear();
		user.getDOB().setYear(today.getYear());
		System.out.println(today.toString() + " " + user.getDOB().toString() + " " +today.before(user.getDOB()));
		if(today.before(user.getDOB())) {
			age--;
		}
		fields.setField("fill_4", String.valueOf(age));
		
		fields.setField("fill_5", user.getGender());
		String HKID = user.getHKID();
		String newHKID = HKID.substring(0, Math.min(HKID.length(), 4));
		fields.setField("fill_6", newHKID);
		fields.setField("fill_7", user.getUsername());
		fields.setField("fill_8", dt1.format(user.getDOI()).toString());
		fields.setField("fill_9", user.getRegion());
		fields.setField("fill_10", user.getDistrict());
		fields.setField("fill_11", user.getScoutGroup());
		fields.setField("fill_13", user.getAddress());
		fields.setField("fill_14", String.valueOf(user.getPhoneNum()));
		fields.setField("fill_15", user.getEmail());

		pdfStamper.setFormFlattening(false);
		pdfStamper.close();
		pdfReader.close();
		 return baos.toByteArray();
	}
}
