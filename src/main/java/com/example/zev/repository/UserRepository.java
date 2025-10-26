package com.example.zev.repository;

import com.example.zev.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User> {

    Optional<User> findByEmail(String email);
}
