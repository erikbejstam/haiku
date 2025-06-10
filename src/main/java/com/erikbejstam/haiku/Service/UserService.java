package com.erikbejstam.haiku.Service;

import com.erikbejstam.haiku.Model.User;
import com.erikbejstam.haiku.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public Optional<User> findUser(Long id) {
        return repo.findById(id);
    }




}
