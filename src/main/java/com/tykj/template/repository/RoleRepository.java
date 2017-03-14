package com.tykj.template.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tykj.template.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
