package com.example.demo.config.shiro;

import com.example.demo.common.util.ApplicationContextUtil;
import com.example.demo.model.auth.dto.UserDto;
import com.example.demo.model.auth.dto.PermPo;
import com.example.demo.model.auth.dto.UserRoleRelPo;
import com.example.demo.model.auth.enums.UserStatusEnum;
import com.example.demo.model.auth.service.SysService;
import com.example.demo.model.auth.service.UserRoleRelService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    /**
     * 权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserDto userDto = (UserDto) principalCollection.getPrimaryPrincipal();
        return getAuthrizationInfo(userDto);
    }

    /**
     * 登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        UserDto userDto = ApplicationContextUtil.getBean(SysService.class).findByUsername(username);
        if (userDto == null) {
            //用户不存在
            throw new UnknownAccountException();
        }
        if (userDto.getStatus().equals(UserStatusEnum.UNACTIVE.getCode())) {
            //用户被禁用
            throw new DisabledAccountException();
        }
        Set<Integer> roleSet=new HashSet<>();
        List<UserRoleRelPo> userRoleRelPoList=ApplicationContextUtil.getBean(UserRoleRelService.class).findByUserId(userDto.getId());
        if(userRoleRelPoList!=null&&userRoleRelPoList.size()>0){
            for(UserRoleRelPo userRoleRelPo:userRoleRelPoList){
                roleSet.add(userRoleRelPo.getRoleId());
                userDto.setRole(roleSet);
            }
        }

        getAuthrizationInfo(userDto);
        return new SimpleAuthenticationInfo(userDto, userDto.getPasswd(), getName());
    }

    private AuthorizationInfo getAuthrizationInfo(UserDto userDto) {
        SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
        if (userDto.getPerm() != null) {
            authorization.setStringPermissions(userDto.getPerm());
        } else {
            Set<String> permSet = findUserPerm(userDto.getId());
            authorization.setStringPermissions(permSet);
            userDto.setPerm(authorization.getStringPermissions());
        }
        return authorization;
    }
    private Set<String> findUserPerm(Integer  userId) {
        SysService sysService = ApplicationContextUtil.getBean(SysService.class);
        List<PermPo> permPoList = sysService.findPerm(userId);
        Set<String> userPerms = new HashSet<>();
        if (permPoList != null && !permPoList.isEmpty()) {
            for (PermPo permPo : permPoList) {
                String perm = permPo.getPerm();
                //String menuUrl = permPo.getMenuUrl();
                // String perm = menuUrl + ":" + permUrl;
                userPerms.add(perm);
            }
        }
        return userPerms;
    }
}
