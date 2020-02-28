package com.example.demo.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class ShiroCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //sha-256
        //String encryPassword= EncryUtil.encryPassword(String.valueOf(token.getPassword()));

        //只比较是否相同
        String encryPassword = String.valueOf(token.getPassword());
        token.setPassword(encryPassword.toCharArray());
        //判断密码相同
        return super.doCredentialsMatch(token, authenticationInfo);
    }
}
