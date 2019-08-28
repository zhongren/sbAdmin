package com.example.demo.model.auth.service;


import com.example.demo.model.auth.dto.UserDto;
import com.example.demo.model.auth.dto.PermPo;
import com.example.demo.model.auth.repo.PermRepo;
import com.example.demo.model.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by zhongr on 2017/8/28.
 */
@Service
public class SysService {
    @Autowired
    private PermRepo permRepo;
    @Autowired
    private UserRepo userRepo;
   // @Autowired
  //  private MenuRepo menuRepo;

    /**
     * 获取用户权限集合
     *
     * @param userId
     * @return
     */
    public List<PermPo> findPerm(Integer userId) {
        List<PermPo> permPoList = permRepo.findUserPerm(userId);
        return permPoList;
    }
    public UserDto findByUsername(String username) {
        UserDto userUserDto =userRepo.find("username",username, UserDto.class);
        return userUserDto;
    }
    /**
     * 获取用户菜单集合
     *
     * @param userId
     * @return

    public List<MenuPo> findUserMenu(long userId) {
        List<MenuPo> menuList = new ArrayList<>();
        Map<Long,MenuPo> menuPoMap=new HashMap<>();
        List<MenuPo> subMenuPoList = menuRepo.findUserMenu(userId);
        List<MenuPo> menuPoList = menuRepo.findListBy("parent_id", 0, MenuPo.class);
        if (CollectionUtil.isNotEmpty(subMenuPoList)) {
            for(MenuPo subMenu:subMenuPoList){
                menuPoMap.put(subMenu.getId(),subMenu);
                if (CollectionUtil.isNotEmpty(menuPoList)) {
                    for(MenuPo menu:menuPoList){
                        if(subMenu.getParentId().equals(menu.getId())){
                            if(!menuPoMap.containsKey(menu.getId())){
                                menuPoMap.put(menu.getId(),menu);
                            }
                        }

                    }
                }
            }
        }
        if(menuPoMap.size()>0){
            for (Map.Entry<Long, MenuPo> entry : menuPoMap.entrySet()) {
                menuList.add(entry.getValue());
            }
        }
        return menuList;
    }
     */
}
