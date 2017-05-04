package com.tykj.template.web.sys;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tykj.template.domain.User;
import com.tykj.template.service.sys.SysService;

@Controller
public class RegisterController {

	@Autowired
	private SysService sysService;

	@GetMapping("/register")
	public String passwordFindForm() {
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid User user, @RequestParam("user_profile_file") MultipartFile user_profile_file,
			RedirectAttributes redirectAttributes) throws IOException {
		sysService.saveUser(user, user_profile_file);
		redirectAttributes.addAttribute("register", "");
		return "redirect:/login";
	}

}
