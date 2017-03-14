package com.tykj.template.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tykj.template.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	User findByLoginName(String loginName);
	
	User findByEmail(String email);
}
