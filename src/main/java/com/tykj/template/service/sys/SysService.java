package com.tykj.template.service.sys;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tykj.template.domain.Role;
import com.tykj.template.domain.User;
import com.tykj.template.dto.RoleDto;
import com.tykj.template.repository.RoleRepository;
import com.tykj.template.repository.UserRepository;
import com.tykj.template.security.utils.SecurityUtils;
import com.tykj.template.service.mail.MailService;
import com.tykj.template.service.redis.RedisService;
import com.tykj.template.utils.BeanMapper;
import com.tykj.template.utils.Constants;
import com.tykj.template.utils.StringUtils;

@Service
public class SysService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private RedisService redisService;

	public List<Role> getAllRole() {
		return BeanMapper.mapList(roleRepository.findAll(), Role.class);
	}

	public List<User> getAllUser() {
		return BeanMapper.mapList(userRepository.findAll(), User.class);
	}

	public User getUserByUserName(String username) {
		return userRepository.findByLoginName(username);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User getUserById(long userid) {
		return userRepository.findOne(userid);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void register(User user, MultipartFile user_profile_file) throws IOException {
		user.setId(null);
		user.setPassword(SecurityUtils.encoderPassword(user.getPlainPassword()));
		user.setStatus(1);
		user.setCreateTime(new Date());
		user.setRoleList(getDefaultRoleList());
		user.setUserProfile(saveUserProfile(user_profile_file));
		saveUser(user);
	}

	private String saveUserProfile(MultipartFile user_profile_file) throws IOException {
		if (user_profile_file == null || user_profile_file.getSize() == 0) {
			return null;
		}
		String tempName = System.currentTimeMillis()
				+ StringUtils.getFileNameExt(user_profile_file.getOriginalFilename());
		String saveUrl = Constants.getUserProfilePath() + File.separator + tempName;
		File tempFile = new File(saveUrl);
		user_profile_file.transferTo(tempFile);
		return saveUrl;
	}

	public void sendPasswordFindMail(String email) throws Exception {
		User user = getUserByEmail(email);
		String uuid = StringUtils.getUUID();
		mailService.sendPasswordFindMail(email, user.getName(), Constants.MAIL_PASSWORD_FIND_LINKURL_PRI + "/" + uuid);
		redisService.setPasswordFindMail(uuid, email, 60 * 60 * 48);
	}

	public String getPasswordFindEmail(String uuid) {
		return redisService.getPasswordFindMail(uuid);
	}

	public void savePasswordFind(String uuid, String password) {
		String email = getPasswordFindEmail(uuid);
		User user = getUserByEmail(email);
		user.setPassword(SecurityUtils.encoderPassword(password));
		saveUser(user);
	}

	@PreAuthorize("hasAuthority('管理员')")
	public void enableUser(String username) {
		User user = getUserByUserName(username);
		if (user != null) {
			user.setStatus(Constants.USER_STATUS_ENABLE);
		}
		saveUser(user);
	}

	@PreAuthorize("hasAuthority('管理员')")
	public void disableUser(String username) {
		User user = getUserByUserName(username);
		if (user != null) {
			user.setStatus(Constants.USER_STATUS_DISABLE);
		}
		saveUser(user);
	}

	private List<Role> getDefaultRoleList() {
		List<Role> list = new ArrayList<Role>();
		list.add(new Role(Constants.ROLE_DEFAULT_ID));
		return list;
	}

	public List<RoleDto> getRoleListWithSelectedUser(long userid) {
		List<RoleDto> roleDto = new ArrayList<RoleDto>();
		User user = getUserById(userid);
		List<Role> userRoles = user.getRoleList();
		List<Role> roles = getAllRole();
		for (Role role : roles) {
			RoleDto dto = new RoleDto(role);
			for (Role userRole : userRoles) {
				if (role.getId().longValue() == userRole.getId().longValue()) {
					dto.setCheck(true);
					break;
				}
			}
			roleDto.add(dto);
		}
		return roleDto;
	}

	@PreAuthorize("authentication.name == 'user'")
	public void saveUserRole(long userid, long[] roles) {
		User user = getUserById(userid);
		List<Role> roleList = new ArrayList<Role>();
		if (roles != null) {
			for (long roleid : roles) {
				roleList.add(new Role(roleid));
			}
		}
		user.setRoleList(roleList);
		saveUser(user);
	}
}