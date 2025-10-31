package com.example.zev.repository;

import com.example.zev.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);
}
