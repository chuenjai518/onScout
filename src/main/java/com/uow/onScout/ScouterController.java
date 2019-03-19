package com.uow.onScout;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.itextpdf.text.pdf.PdfAcroForm;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.uow.Model.EmerContact;
import com.uow.Model.ScoutManage;
import com.uow.Model.User;
import com.uow.Model.UserInfo;
import com.uow.Service.ScouterService;

@Controller
public class ScouterController {
	@Autowired
	ScouterService scouterService;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	public boolean checkScouterLogin(HttpSession session) {
		boolean login = false;
		if (session.getAttribute("username") != null) {
			if ((int) session.getAttribute("roleID") == 2) {
				login = true;
			}
		}
		return login;
	}

	@GetMapping("scouter")
	public String redirect(Model model, HttpSession session) {
		if (!checkScouterLogin(session)) {
			return "redirect:/login";
		}
		return "redirect:/scouter/scoutProcess";
	}

	@GetMapping("scouter/scoutProcess")
	public String index(Model model, HttpSession session) {
		
		if (!checkScouterLogin(session)) {
			return "redirect:/login";
		}
		List<UserInfo> scoutList = scouterService.getAllUser();
		model.addAttribute("scoutList", scoutList);
		return "Scouter/scoutProcess";
	}

	@GetMapping("scouter/scoutProcess/{username}")
	public String scoutProcess(@PathVariable("username") String username, Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		if (!checkScouterLogin(session)) {
			return "redirect:/login";
		}
		UserInfo user = scouterService.getScoutInfo(username);
		model.addAttribute("openSSA", scouterService.checkSSA(username));
		model.addAttribute("openSAA", scouterService.checkSAA(username));
		model.addAttribute("openCSA", scouterService.checkCSA(username));
		model.addAttribute("user", user);
		return "Scouter/scoutProcessDetail";
	}

	@GetMapping("scouter/scoutManage")
	public String scoutManage(Model model, HttpSession session) {
		if (!checkScouterLogin(session)) {
			return "redirect:/login";
		}
		List<ScoutManage> userList = scouterService.getScoutManageList();
		model.addAttribute("userList", userList);
		return "Scouter/scoutManage";
	}

	@GetMapping("scouter/scoutManage/{username}")
	public String scoutManageDetail(@PathVariable("username") String username, Model model, HttpSession session) {
		if (!checkScouterLogin(session)) {
			return "redirect:/login";
		}
		List<EmerContact> emerList = scouterService.getEmerContact(username);
		UserInfo userInfo = scouterService.getScoutInfo(username);

		model.addAttribute("userInfo", userInfo);
		model.addAttribute("emerList", emerList);
		model.addAttribute("emer", new EmerContact());
		return "Scouter/scoutInfoDetail";
	}

	@PostMapping("scouter/editScoutProfileProcess/{username}")
	public String editScoutProfileProcess(@PathVariable("username") String username, Model model, HttpSession session,
			@ModelAttribute EmerContact emerTel, @ModelAttribute UserInfo userInfo) {
		scouterService.editScoutProfileProcess(username, userInfo);
		System.out.println(userInfo.getEmail());
		return "redirect:/scouter/scoutManage";
	}
	
	@PostMapping("scouter/editEmer/{username}")
	public String editEmerContact(@PathVariable("username") String username, @ModelAttribute EmerContact emer) {
		System.out.println(emer.getEmerID());
		scouterService.editEmerContact(emer, username);
		return "redirect:/scouter/scoutManage/" + username;
	}

	@GetMapping("scouter/viewAw")
	public String viewAw(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		if (!checkScouterLogin(session)) {
			return "redirect:/login";
		}
		List<UserInfo> userList = scouterService.getAllUser();
		model.addAttribute("userList", userList);
		return "Scouter/viewAw";
	}

	@GetMapping("scouter/editProfile")
	public String editProfile(Model model, HttpSession session) {
		// model.addAttribute("user", (User)session.getAttribute("user"));
		if (!checkScouterLogin(session)) {
			return "redirect:/login";
		}
		UserInfo scouterInfo = scouterService.getScouterInfo((String) session.getAttribute("username"));
		model.addAttribute("userInfo", scouterInfo);
		return "Scouter/editProfile";
	}

	@PostMapping("scouter/editScouterProfileProcess")
	public String editProfileProcess(Model model, HttpSession session, @ModelAttribute UserInfo userInfo) {
		scouterService.editScoutProfileProcess((String) session.getAttribute("username"), userInfo);
		return "redirect:/scouter/editProfile";
	}

	@PostMapping("scouter/createUser")
	public RedirectView forgetPassword(RedirectAttributes model, @RequestParam String username) {
	
		boolean exists = scouterService.createUserProcess(username);
		if (exists) {
			model.addFlashAttribute("message", "Username is used!");
			return new RedirectView("/onScout/scouter#addUser");
		}
		return new RedirectView("/onScout/scouter/scoutManage");
	}

	@GetMapping("scouter/showPDF/{name}")
	public StreamingResponseBody getSteamingFile(@PathVariable("name") String name, HttpServletResponse response)
			throws URISyntaxException {
		String url = "static/pdf/" + name + ".pdf";
		File file = new File(getClass().getClassLoader().getResource(url).toURI());

		// viewing in web browser
		response.setContentType("application/pdf");
		// for downloading the file directly if viewing is not possible
		response.setHeader("Content-Disposition", "inline; filename=" + file.getName());

		file = null;

		// put the directory architecture according to your target directory
		// generated during compilation in maven spring boot
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(url);

		return outputStream -> {
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				outputStream.write(data, 0, nRead);
			}
			inputStream.close();
		};
	}

	

}