package com.example.demo.model.user;

import com.example.demo.common.BaseService;
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
