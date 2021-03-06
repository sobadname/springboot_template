package com.tykj.template.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "sys_permission")
public class Permission extends IdEntity {
	@NotBlank
	public String name;
	@NotBlank
	@Column(unique = true, nullable = false)
	public String url;
	public String icon;
	public int showOrder;
	public int type;
	@NotBlank
	public String idPath;
	@NotBlank
	public Long parentId;
	
	public Permission(){
		
	}
	
	public Permission(long id){
		this.id=id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Permission [name=" + name + ", url=" + url + ", icon=" + icon + ", showOrder=" + showOrder + ", type="
				+ type + ", idPath=" + idPath + ", parentId=" + parentId + "]";
	}

}
