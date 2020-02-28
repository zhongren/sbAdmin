package com.example.demo.config.shiro;

import com.example.demo.common.util.AppCtx;
import com.example.demo.model.auth.dto.UserLoginDto;
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

public class ShiroRealm extends AuthorizingRealm {
    /**
     * 权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserLoginDto userLoginDto = (UserLoginDto) principalCollection.getPrimaryPrincipal();
        return getAuthrizationInfo(userLoginDto);
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
        UserLoginDto userLoginDto = AppCtx.getBean(SysService.class).findByUsername(username);
        if (userLoginDto == null) {
            //用户不存在
            throw new UnknownAccountException();
        }
        if (userLoginDto.getStatus().equals(UserStatusEnum.UNACTIVE.getCode())) {
            //用户被禁用
            throw new DisabledAccountException();
        }
        Set<Integer> roleSet = new HashSet<>();
        List<UserRoleRelPo> userRoleRelPoList = AppCtx.getBean(UserRoleRelService.class).findByUserId(userLoginDto.getId());
        if (userRoleRelPoList != null && userRoleRelPoList.size() > 0) {
            for (UserRoleRelPo userRoleRelPo : userRoleRelPoList) {
                roleSet.add(userRoleRelPo.getRoleId());
                userLoginDto.setRole(roleSet);
            }
        }

        getAuthrizationInfo(userLoginDto);
        return new SimpleAuthenticationInfo(userLoginDto, userLoginDto.getPasswd(), getName());
    }

    private AuthorizationInfo getAuthrizationInfo(UserLoginDto userLoginDto) {
        SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
        if (userLoginDto.getPerm() != null) {
            authorization.setStringPermissions(userLoginDto.getPerm());
        } else {
            Set<String> permSet = findUserPerm(userLoginDto.getId());
            authorization.setStringPermissions(permSet);
            userLoginDto.setPerm(authorization.getStringPermissions());
        }
        return authorization;
    }

    private Set<String> findUserPerm(Integer userId) {
        SysService sysService = AppCtx.getBean(SysService.class);
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
