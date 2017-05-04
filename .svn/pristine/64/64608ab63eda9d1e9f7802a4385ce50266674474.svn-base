package com.tykj.template.web.sys;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tykj.template.domain.User;
import com.tykj.template.dto.RoleDto;
import com.tykj.template.service.sys.SysService;

@Controller
@RequestMapping(value = "/sys")
public class UserController {

	@Autowired
	private SysService sysService;

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String userList(Model model) {
		List<User> users = sysService.getAllUser();
		model.addAttribute("users", users);
		return "sys/userList";
	}

	@GetMapping("/user/enable/{username}")
	public String enableUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
		sysService.enableUser(username);
		redirectAttributes.addAttribute("enable", "");
		return "redirect:/sys/user/list";
	}

	@GetMapping("/user/disable/{username}")
	public String disableUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
		sysService.disableUser(username);
		redirectAttributes.addAttribute("disable", "");
		return "redirect:/sys/user/list";
	}

	@GetMapping("/checkUsername")
	@ResponseBody
	public boolean checkUsername(@RequestParam("loginName") String username) {
		return sysService.getUserByUserName(username) == null;
	}

	@GetMapping("/checkEmail")
	@ResponseBody
	public boolean checkEmail(@RequestParam("email") String email) {
		return sysService.getUserByEmail(email) == null;
	}

	@GetMapping("/checkEmailNot")
	@ResponseBody
	public boolean checkEmailNot(@RequestParam("email") String email) {
		return sysService.getUserByEmail(email) != null;
	}

	@GetMapping("/user/roleListWithSelectedUser")
	@ResponseBody
	public List<RoleDto> roleListWithSelectedUser(@RequestParam("userid") long userid) {
		return sysService.getRoleListWithSelectedUser(userid);
	}

	@PostMapping("/user/role")
	public String userRole(@RequestParam(value = "selectedRole", required = false) long[] selectedRole,
			@RequestParam("userid") long userid, RedirectAttributes redirectAttributes) {
		sysService.saveUserRole(userid, selectedRole);
		redirectAttributes.addAttribute("role", "");
		return "redirect:/sys/user/list";
	}

	@GetMapping("/user/create")
	public String createUserForm(Model model) {
		model.addAttribute("user", new User());
		return "sys/userForm";
	}

	@GetMapping("/user/update/{username}")
	public String updateUserForm(@PathVariable("username") String username, Model model) {
		User user = sysService.getUserByUserName(username);
		user.setPlainPassword(SysService.PLAIN_PASSWORD);
		model.addAttribute("user", user);
		return "sys/userForm";
	}

	@PostMapping("/user/save")
	public String saveUser(@Valid User user, @RequestParam("user_profile_file") MultipartFile user_profile_file,
			RedirectAttributes redirectAttributes) throws IOException {
		sysService.saveUser(user, user_profile_file);
		redirectAttributes.addAttribute("save", "");
		return "redirect:/sys/user/list";
	}

}
