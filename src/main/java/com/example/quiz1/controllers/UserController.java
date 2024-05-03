package com.example.quiz1.controllers;

import com.example.quiz1.entities.User;
import com.example.quiz1.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
        @Autowired
        private UserService userService;
        @RequestMapping("/createUser")
        public String createUser(){
            return "CreateUser";
        }

        @RequestMapping("saveUser")
         public String saveUser(@Valid User user, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) return "CreateUser";
           User saveUser = userService.saveUser(user);
           return "CreateUser";
        }
        @RequestMapping("/usersList")
         public String usersList(ModelMap modelMap,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "4") int size
                                 ){

            Page<User> users= userService.getAllUsersByPage(page, size);

            modelMap.addAttribute("users", users);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("pages", new int[users.getTotalPages()]);
            return "UsersList";
        }
        @RequestMapping("/deleteUser")
        public String deleteUser(@RequestParam("id") Long id, ModelMap modelMap,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "4") int size
        ){
            userService.deleteUserById(id);
            Page<User> users= userService.getAllUsersByPage(page, size);

            modelMap.addAttribute("users", users);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("pages", new int[users.getTotalPages()]);
            return "UsersList";


        }
        @RequestMapping("/editUser")
        public String editUser(@RequestParam("id") Long id, ModelMap modelMap){
           User userController = userService.getUserById(id);
           modelMap.addAttribute("userView", userController);
         return "EditUser";
        }
        @RequestMapping("/updateUser")
        public String updateUser(@ModelAttribute("userVue") User userController){
            userService.updateUser(userController);
            return createUser();
        }
        }



