package com.example.demo.model.auth.service;


import com.example.demo.common.base.BaseService;
import com.example.demo.model.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongr on 2017/7/31.
 */
@Service
public class RolePermRelService extends BaseService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public void init() {
        setBaseRepo(userRepo);
    }
    /*
    public UserDto findByUserName(String userName) {
        return find("userName", userName, UserDto.class);
    }

     */
}
