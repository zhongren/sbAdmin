package com.example.demo.model.user.service;

import com.example.demo.common.base.BaseService;
import com.example.demo.model.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public void init() {
        setBaseRepo(userRepo);
    }


}
