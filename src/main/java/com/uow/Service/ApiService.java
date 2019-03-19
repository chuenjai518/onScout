package com.uow.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfAcroForm;
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

	public UserInfo getUserInfo(String username) {
		return apiDAO.getUserInfo(username);
	}

	public String findDateByQuestID(int questID, List<CompletedQuest> questList) {
		String date = "";
		Iterator<CompletedQuest> iterator = questList.iterator();
		while (iterator.hasNext()) {
			CompletedQuest quest = iterator.next();
			if (quest.getQuestID() == questID) {
				SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
				date = dt1.format(quest.getFinishDate()).toString();

			}
		}
		System.out.println("Got Date: " + date);
		return date;
	}

	public boolean checkCSACompleted(String username) {
		return apiDAO.checkCSACompleted(username);
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
		List<CompletedQuest> questList = apiDAO.getFinal(username);

		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("|----------Checker 1");
		fields.setField("fill_2", user.getLastName() + " " + user.getFirstName());
		System.out.println("|----------Checker 2");
		fields.setField("fill_3", dt1.format(user.getDOB()).toString());
		System.out.println("|----------Checker 5");
		Date today = new Date();
		int age = today.getYear() - user.getDOB().getYear();
		user.getDOB().setYear(today.getYear());
		if (today.before(user.getDOB())) {
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

		if (!findDateByQuestID(11400, questList).equals("")) {
			if (!findDateByQuestID(21400, questList).equals("")) {
				if (!findDateByQuestID(31400, questList).equals("")) {
					fields.setField("Check Box1", "0");

					String date = findDateByQuestID(11400, questList);
					String[] parts = date.split("/");
					fields.setField("fill_16", parts[0]);
					fields.setField("fill_17", parts[1]);
					fields.setField("fill_18", parts[2]);

					date = findDateByQuestID(21400, questList);
					parts = date.split("/");
					fields.setField("fill_26", parts[0]);
					fields.setField("fill_27", parts[1]);
					fields.setField("fill_28", parts[2]);

					date = findDateByQuestID(31400, questList);
					parts = date.split("/");
					fields.setField("fill_36", parts[0]);
					fields.setField("fill_37", parts[1]);
					fields.setField("fill_38", parts[2]);
				}
			}
		}

		if (!findDateByQuestID(11500, questList).equals("")) {
			if (!findDateByQuestID(21500, questList).equals("")) {
				if (!findDateByQuestID(31500, questList).equals("")) {
					fields.setField("Check Box2", "0");

					String date = findDateByQuestID(11500, questList);
					String[] parts = date.split("/");
					fields.setField("fill_16", parts[0]);
					fields.setField("fill_17", parts[1]);
					fields.setField("fill_18", parts[2]);

					date = findDateByQuestID(21500, questList);
					parts = date.split("/");
					fields.setField("fill_26", parts[0]);
					fields.setField("fill_27", parts[1]);
					fields.setField("fill_28", parts[2]);

					date = findDateByQuestID(31500, questList);
					parts = date.split("/");
					fields.setField("fill_36", parts[0]);
					fields.setField("fill_37", parts[1]);
					fields.setField("fill_38", parts[2]);
				}
			}
		}

		if (!findDateByQuestID(11600, questList).equals("")) {
			if (!findDateByQuestID(21600, questList).equals("")) {
				if (!findDateByQuestID(31600, questList).equals("")) {
					fields.setField("Check Box3", "0");

					String date = findDateByQuestID(11500, questList);
					String[] parts = date.split("/");
					fields.setField("fill_16", parts[0]);
					fields.setField("fill_17", parts[1]);
					fields.setField("fill_18", parts[2]);

					date = findDateByQuestID(21500, questList);
					parts = date.split("/");
					fields.setField("fill_26", parts[0]);
					fields.setField("fill_27", parts[1]);
					fields.setField("fill_28", parts[2]);

					date = findDateByQuestID(31500, questList);
					parts = date.split("/");
					fields.setField("fill_36", parts[0]);
					fields.setField("fill_37", parts[1]);
					fields.setField("fill_38", parts[2]);
				}
			}
		}

		String date = findDateByQuestID(11000, questList);
		if (!findDateByQuestID(11000, questList).equals("")) {
			String[] parts = date.split("/");
			fields.setField("fill_19", parts[0]);
			fields.setField("fill_20", parts[1]);
			fields.setField("fill_21", parts[2]);
		}

		date = findDateByQuestID(10000, questList);
		if (!findDateByQuestID(10000, questList).equals("")) {
			String[] parts = date.split("/");
			fields.setField("fill_22", parts[0]);
			fields.setField("fill_23", parts[1]);
			fields.setField("fill_24", parts[2]);
		}

		date = findDateByQuestID(21000, questList);
		if (!findDateByQuestID(21000, questList).equals("")) {
			String[] parts = date.split("/");
			fields.setField("fill_29", parts[0]);
			fields.setField("fill_30", parts[1]);
			fields.setField("fill_31", parts[2]);
		}

		date = findDateByQuestID(20000, questList);
		if (!findDateByQuestID(20000, questList).equals("")) {
			String[] parts = date.split("/");
			fields.setField("fill_32", parts[0]);
			fields.setField("fill_33", parts[1]);
			fields.setField("fill_34", parts[2]);
		}

		date = findDateByQuestID(31000, questList);
		if (!findDateByQuestID(31000, questList).equals("")) {
			String[] parts = date.split("/");
			fields.setField("fill_39", parts[0]);
			fields.setField("fill_40", parts[1]);
			fields.setField("fill_41", parts[2]);
		}

		date = findDateByQuestID(30000, questList);
		if (!findDateByQuestID(30000, questList).equals("")) {
			String[] parts = date.split("/");
			fields.setField("fill_42", parts[0]);
			fields.setField("fill_43", parts[1]);
			fields.setField("fill_44", parts[2]);
		}
		fields.setField("fill_64", findDateByQuestID(41110, questList));
		fields.setField("fill_65", findDateByQuestID(41120, questList), findDateByQuestID(41120, questList));
		fields.setField("fill_66", findDateByQuestID(41130, questList));
		fields.setField("fill_67", findDateByQuestID(41210, questList));
		fields.setField("fill_68", findDateByQuestID(41220, questList));
		fields.setField("fill_69", findDateByQuestID(41230, questList));
		fields.setField("fill_70", findDateByQuestID(41310, questList));
		fields.setField("fill_71", findDateByQuestID(41320, questList));
		fields.setField("fill_72", findDateByQuestID(41410, questList));
		fields.setField("fill_73", findDateByQuestID(41420, questList));
		fields.setField("fill_74", findDateByQuestID(41510, questList));
		fields.setField("fill_75", findDateByQuestID(41520, questList));
		fields.setField("fill_76", findDateByQuestID(41610, questList));
		fields.setField("fill_77", findDateByQuestID(41620, questList));
		System.out.println("|----------Checker 3");
		fields.setField("fill_78", findDateByQuestID(42110, questList));
		fields.setField("fill_79", findDateByQuestID(42120, questList));
		fields.setField("fill_80", findDateByQuestID(42210, questList));
		fields.setField("fill_83", findDateByQuestID(42220, questList));
		fields.setField("fill_84", findDateByQuestID(42310, questList));
		fields.setField("fill_85", findDateByQuestID(42410, questList));
		fields.setField("fill_88", findDateByQuestID(42420, questList));

		fields.setField("fill_89", findDateByQuestID(43110, questList));
		fields.setField("fill_90", findDateByQuestID(43120, questList));
		fields.setField("fill_91", findDateByQuestID(43130, questList));
		fields.setField("fill_92", findDateByQuestID(43210, questList));
		fields.setField("fill_95", findDateByQuestID(43220, questList));
		fields.setField("fill_96", findDateByQuestID(43310, questList));
		fields.setField("fill_99", findDateByQuestID(43320, questList));

		fields.setField("fill_100", findDateByQuestID(44110, questList));
		fields.setField("fill_101", findDateByQuestID(44210, questList));
		fields.setField("fill_104", findDateByQuestID(44220, questList));
		fields.setField("fill_105", findDateByQuestID(44310, questList));
		fields.setField("fill_106", findDateByQuestID(44320, questList));

		fields.setField("fill_107", findDateByQuestID(45100, questList));
		fields.setField("fill_108", findDateByQuestID(45200, questList));

		pdfStamper.setFormFlattening(false);
		System.out.println("|----------Checker 4");
		pdfStamper.close();
		pdfReader.close();
		return baos.toByteArray();
	}
}
