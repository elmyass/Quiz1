package com.example.quiz1.services;

import com.example.quiz1.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
   void deleteUserById(Long id);
   void deleteAllUsers();
   User getUserById(Long id);
   List<User> getAllUsers();
   Page<User> getAllUsersByPage(int page, int size);

}
