package com.example.zev.repository;

import com.example.zev.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User> {
}
