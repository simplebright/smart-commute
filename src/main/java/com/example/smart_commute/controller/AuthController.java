package com.example.smart_commute.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_commute.config.JwtUtil;
import com.example.smart_commute.dto.LoginRequest;
import com.example.smart_commute.entity.User;
import com.example.smart_commute.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired 
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginRequest req){
        User u=userService.findByUsername(req.getUsername());
        if(u==null)
            throw new RuntimeException("user not exist");
        if(!userService.checkPassword(u, req.getPassword()))
            throw new RuntimeException("password is wrong"); 
        String token =jwtUtil.generateToken(u.getUsername(),u.getRoles());
        Map<String,Object> r=new HashMap<>();
        r.put("token",token);
        r.put("tokenType","Bearer");
        return r;
    } 
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody LoginRequest req){
        System.err.println("进入");
        User existing = userService.findByUsername(req.getUsername());
        if (existing != null) throw new RuntimeException("用户已存在");
        User u = userService.createUser(req.getUsername(), req.getPassword(), "USER");
        Map<String,Object> r = new HashMap<>();
        r.put("id", u.getId());
        r.put("username", u.getUsername());
        return r;
    }
}
