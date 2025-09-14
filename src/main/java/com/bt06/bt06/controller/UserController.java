package com.bt06.bt06.controller;

import com.bt06.bt06.entity.User;
import com.bt06.bt06.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("admin/user")
public class UserController {
    private final UserService userService;
    @GetMapping("")
    public String getUser(Model model, @RequestParam(value = "q", required = false) String keyword){
        List<User> users;
        if(keyword != null && !keyword.trim().isEmpty()) {
            users = userService.searchByUserName(keyword);
            model.addAttribute("q", keyword);
        }else{
            users = userService.getAllUsers();
        }
        model.addAttribute("users",users);
        return "user/user";
    }
    @GetMapping("add")
    public String getAddUser(Model model){
        return "user/add";
    }
    @PostMapping("add")
    public String postAddUser(Model model, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password){
        User oldUser = userService.getUserByUsername(username);
        if (oldUser == null) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            userService.saveUser(user);
            return "redirect:/admin/user";
        }
        else{
            // Gửi thông báo lỗi cho form add.html
            model.addAttribute("errorMessage", "Tài khoản '" + username + "' đã tồn tại!");
            return "user/add";
        }
    }
    @GetMapping("edit/{id}")
    public String getEditUser(Model model, @PathVariable long id){
        User user = userService.getUserByid(id);
        model.addAttribute("user",user);
        return "user/edit";
    }
    @PostMapping("edit/{id}")
    public String postEditUser(Model model,@PathVariable long id, @RequestParam("newPassword") String newPassword){
        User user = userService.getUserByid(id);
        user.setPassword(newPassword);
        userService.saveUser(user);
        return "redirect:/admin/user";
    }
    @GetMapping("delete/{id}")
    public String getDeleteUser(Model model, @PathVariable long id){
        User user = userService.getUserByid(id);
        userService.deleteUser(user);
        return "redirect:/admin/user";
    }
}