package com.example.quiz1.controllers;

import com.example.quiz1.entities.User;
import com.example.quiz1.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

        private UserService userService;
        @RequestMapping("/createUser")
        public String createUser(){
            return "CreateUser";
        }

        @RequestMapping("SaveUser")
         public String saveUser(@ModelAttribute("userVue") User userController) {

           User usersave = userService.saveUser(userController);
           return "CreateUser";
        }
        @RequestMapping("/usersList")
         public String usersList(ModelMap modelMap){

            List<User> usersController= userService.getAllUsers();

            modelMap.addAttribute("usersVue", usersController);

            return "UsersList";
        }
        @RequestMapping("/deleteUser")
        public String deleteUser(@RequestParam("id") Long id, ModelMap modelMap){
            userService.deleteUserById(id);
            return usersList(modelMap);

        }

        }



