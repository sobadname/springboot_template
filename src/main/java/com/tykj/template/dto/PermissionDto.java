package com.tykj.template.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.tykj.template.domain.Permission;

public class PermissionDto extends Permission implements Serializable, Comparable<Permission> {

	private static final long serialVersionUID = 1L;

	private List<PermissionDto> children = new ArrayList<PermissionDto>();
	private PermissionDto parent;
	private boolean active;
	private boolean check;

	public PermissionDto(Permission permission) {
		BeanUtils.copyProperties(permission, this);
	}

	public List<PermissionDto> getChildren() {
		return children;
	}

	public void setChildren(List<PermissionDto> children) {
		this.children = children;
	}

	public PermissionDto getParent() {
		return parent;
	}

	public void setParent(PermissionDto parent) {
		this.parent = parent;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "PermissionDto [children=" + children + ", active=" + active + ", name=" + name + ", url=" + url
				+ ", icon=" + icon + ", showOrder=" + showOrder + ", type=" + type + ", idPath=" + idPath
				+ ", parentId=" + parentId + ", id=" + id + "]";
	}

	@Override
	public int compareTo(Permission perm) {
		int thisLen = this.getIdPath().split(",").length;
		int otherLen = perm.getIdPath().split(",").length;
		if (thisLen > otherLen) {
			return 1;
		} else if (thisLen < otherLen) {
			return -1;
		}
		if (this.getShowOrder() > perm.getShowOrder()) {
			return 1;
		} else if (this.getShowOrder() < perm.getShowOrder()) {
			return -1;
		}
		return 0;
	}

}
