package com.gisdev.crmshm.repository;

import com.gisdev.crmshm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameAndPasswordAndRole(String username, String password, String role);
}
