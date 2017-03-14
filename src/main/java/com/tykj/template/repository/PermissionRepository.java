package com.tykj.template.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tykj.template.domain.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long>{

	List<Permission> getPermissionByParentId(long parent_id);
}
