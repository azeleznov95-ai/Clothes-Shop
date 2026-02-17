package com.example.clothesshop.repository;

import com.example.clothesshop.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findUserByLogin(String Login);


    Optional<Users> findUserById(Long id);

    Long id(Long id);
}
