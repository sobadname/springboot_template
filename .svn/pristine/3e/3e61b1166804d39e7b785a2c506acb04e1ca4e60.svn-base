package com.tykj.template.web.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tykj.template.service.sys.SysService;
import com.tykj.template.utils.StringUtils;

@Controller
@RequestMapping(value = "/password")
public class PasswordController {

	@Autowired
	private SysService sysService;

	@GetMapping("/find")
	public String passwordFindForm() {
		return "password/findForm";
	}

	@PostMapping("/find")
	public String passwordFind(@RequestParam("email") String email, Model model) {
		try {
			sysService.sendPasswordFindMail(email);
		} catch (Exception e) {
		}
		model.addAttribute("mail_login_url", StringUtils.getLoginUrlFromEmail(email));
		return "password/findMail";
	}

	@GetMapping("/find/{uuid}")
	public String passwordUpdateForm(@PathVariable("uuid") String uuid, RedirectAttributes redirectAttributes,
			Model model) {
		String email = sysService.getPasswordFindEmail(uuid);
		if (email == null) {
			redirectAttributes.addAttribute("fail", "");
			return "redirect:/password/find";
		} else {
			model.addAttribute("uuid", uuid);
			return "password/updateForm";
		}
	}

	@PostMapping("/find/{uuid}")
	public String passwordUpdate(@PathVariable("uuid") String uuid, @RequestParam("password") String password,
			RedirectAttributes redirectAttributes) {
		sysService.savePasswordFind(uuid, password);
		redirectAttributes.addAttribute("password", "");
		return "redirect:/login";
	}

}
