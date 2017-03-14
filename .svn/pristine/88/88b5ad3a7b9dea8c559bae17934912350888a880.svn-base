package com.tykj.template.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.tykj.template.domain.Role;

public class RoleDto extends Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean check;

	public RoleDto(Role role) {
		BeanUtils.copyProperties(role, this);
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

}
