package com.example.demo.model.auth.controller;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.dto.ResultDto;
import com.example.demo.common.exception.exception.AuthException;
import com.example.demo.common.exception.exception.BaseException;
import com.example.demo.common.exception.enums.AuthEnum;
import com.example.demo.common.exception.enums.BusinessEnum;
import com.example.demo.model.auth.dto.LoginParam;
import com.example.demo.model.auth.dto.UserLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZR_a on 2017/12/2.
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController extends BaseController {


    private final long DEFAULT_SESSION_TIMEOUT = 3600 * 1000;




    @RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto login(@RequestBody LoginParam loginParam) {
        String account = loginParam.getUsername();
        String password = loginParam.getPasswd();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            subject.login(token);
            String host=subject.getSession().getHost();
            UserLoginDto user = (UserLoginDto) subject.getPrincipal();
            user.setSid(subject.getSession().getId());
            //remember(true);
            System.out.println(host+"用户权限:"+user.getPerm());
            return ResultDto.success( "登陆成功",user);
        } catch (UnknownAccountException e) {
            throw new AuthException(AuthEnum.UNKNOWN_ACCOUNT);
        } catch (DisabledAccountException e) {
            throw new AuthException(AuthEnum.DISABLED_ACCOUNT);
        } catch (IncorrectCredentialsException e) {
            throw new AuthException(AuthEnum.WRONG_PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BusinessEnum.SERVICE_ERROR);
        }

    }

    @RequestMapping(value = "/logout",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDto doLogout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        System.out.println("logout");
        return ResultDto.success();
    }

    @RequestMapping(value = "noAuth")
    public String noAuth() {
        /*
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            throw new AuthException(AuthEnum.WRONG_PASSWORD);
        }
       */
        throw new AuthException(AuthEnum.UNAUTHORIZED);
    }

    @RequestMapping(value = "noLogin")
    public String noLogin() {
        throw new AuthException(AuthEnum.UNLOGIN);
    }

    private void remember(boolean isRemember) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setTimeout(DEFAULT_SESSION_TIMEOUT);
    }
}
