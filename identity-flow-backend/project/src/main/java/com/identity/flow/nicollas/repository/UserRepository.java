package com.identity.flow.nicollas.repository;

import com.identity.flow.nicollas.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
