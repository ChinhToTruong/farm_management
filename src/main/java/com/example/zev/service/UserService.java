package com.example.zev.service;

import com.example.zev.entity.User;
import com.example.zev.repository.SearchRepository;
import com.example.zev.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudServiceImpl<User> {

    public UserService(UserRepository repository, SearchRepository<User> searchRepository, UserRepository userRepository) {
        super(repository, searchRepository);
    }
}
