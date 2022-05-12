package com.teddy.springbootmall.controller;

import com.teddy.springbootmall.model.User;
import com.teddy.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity<String> login(HttpSession session,
                                        @RequestBody User user){

        User userByName = userService.getUserByName(user);
        if(userByName == null){
            System.out.println("自己輸入的帳號是: "+user.getName());
            System.out.println("自己輸入的密碼是: "+user.getPassword());

            throw new IllegalArgumentException("登入失敗，找不到帳號");
        }
        if(!userByName.getPassword().equals(user.getPassword())){
            System.out.println(user.getPassword());
            System.out.println("自己輸入的是: "+user.getPassword());

            throw new IllegalArgumentException("登入失敗，密碼錯誤");
        }

        session.setAttribute("userId", userByName.getId());
        Integer atr = (Integer) session.getAttribute("userId");
        System.out.println("目前 Session 裡面的 uesrId 為: "+atr);

        return ResponseEntity.status(HttpStatus.OK)
                .body("登入成功！ 歡迎會員 "+userByName.getName()+" 回來");
    }

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user){

        Integer userId = userService.createUser(user);
        User userById = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(userById);
    }

    @GetMapping("/test_session")
    public String test(HttpSession session){

        return "Session中的 userId 為 "+session.getAttribute("userId");
    }

    @GetMapping("/user/sign_out")
    public String signout(HttpSession session){

        session.removeAttribute("userId");
        return "登出成功";
    }
}
