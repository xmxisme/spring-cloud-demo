package com.xmxisme.uaa.controller;

import com.xmxisme.uaa.config.JwtTokenParser;
import com.xmxisme.uaa.mapper.UsersMapper;
import com.xmxisme.uaa.param.LoginParam;
import com.xmxisme.uaa.pojo.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
public class OAuth2Controller {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private UsersMapper usersMapper;


    @PostMapping("/baseLogin")
    public String baseLogin(@RequestBody LoginParam loginParam) {
        System.out.println(loginParam.getUsername() + ":" + loginParam.getPassword());
        Users usersByUsername = usersMapper.getUsersByUsername(loginParam.getUsername());
        if (usersByUsername == null) {
            return "用户不存在";
        }
        if (!usersByUsername.getPassword().equals(loginParam.getPassword())) {
            return "用户名或者密码错误";
        }

        return JwtTokenParser.generateToken(usersByUsername);
    }


    @GetMapping("/hello")
    public String showHelloPage(Authentication authentication, Model model) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            OAuth2AuthorizedClient authorizedClient =
                    this.authorizedClientService.loadAuthorizedClient(
                            oauth2Token.getAuthorizedClientRegistrationId(),
                            oauth2Token.getPrincipal().getName());

            if (authorizedClient != null) {
                String accessToken = authorizedClient.getAccessToken().getTokenValue();
                model.addAttribute("accessToken", accessToken);
                System.out.println("Access Token: " + accessToken);
            }
        }

        Users users = new Users();
        users.setId(0L);
        users.setPassword("");
        users.setRoleName("EDITOR");

        return "登录成功，token：" + JwtTokenParser.generateToken(users);
    }

}