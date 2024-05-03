package com.example.quiz1.services;
import com.example.quiz1.entities.User;
import com.example.quiz1.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceempl implements UserService {
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {

         userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {

        userRepository.deleteAll();

    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).get();
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllUsersByPage(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }
}
