package com.tykj.template.web.sys;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tykj.template.domain.Role;
import com.tykj.template.service.sys.SysService;


@Controller
@RequestMapping(value = "/sys")
public class RoleController {

	@Autowired
	private SysService sysService;

	@RequestMapping(value = "/role/list", method = RequestMethod.GET)
	public String roleList(Model model) {
		List<Role> roles = sysService.getAllRole();
		model.addAttribute("roles", roles);
		return "sys/roleList";
	}

	@GetMapping("/role/enable/{rolename}")
	public String enableRole(@PathVariable("rolename") String rolename, RedirectAttributes redirectAttributes) {
		sysService.enableRole(rolename);
		redirectAttributes.addAttribute("enable", "");
		return "redirect:/sys/role/list";
	}

	@GetMapping("/role/disable/{rolename}")
	public String disableRole(@PathVariable("rolename") String rolename, RedirectAttributes redirectAttributes) {
		sysService.disableRole(rolename);
		redirectAttributes.addAttribute("disable", "");
		return "redirect:/sys/role/list";
	}

	@PostMapping("/role/create")
	public String createRoleForm(@RequestParam("rolename") String rolename, RedirectAttributes redirectAttributes) {
		sysService.saveRole(rolename);
		redirectAttributes.addAttribute("role", "");
		return "redirect:/sys/role/list";
	}

	@GetMapping("/role/update/{rolename}")
	public String updateRoleForm(@PathVariable("rolename") String rolename, Model model) {
		Role role = sysService.getRoleByName(rolename);
		model.addAttribute("role", role);
		model.addAttribute("perms", sysService.getAllPermissionDto(role.getPermissionList()));
		return "sys/roleForm";
	}

	@PostMapping("/role/save")
	public String saveRolePerm(@RequestParam("id") long id,
			@RequestParam(value = "perms", required = false) long[] perms, RedirectAttributes redirectAttributes)
			throws IOException {
		sysService.saveRolePerms(id, perms);
		redirectAttributes.addAttribute("perm", "");
		return "redirect:/sys/role/list";
	}

	@GetMapping("/checkRolename")
	@ResponseBody
	public boolean checkUsername(@RequestParam("rolename") String rolename) {
		return sysService.getRoleByName(rolename) == null;
	}

}
