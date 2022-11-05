package com.org.citius.pms.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.citius.pms.user.service.dao.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailId(String userName);

	User findByEmployeeID(String userName);

}