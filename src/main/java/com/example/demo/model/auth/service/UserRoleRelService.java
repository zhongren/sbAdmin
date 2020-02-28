package com.example.demo.model.auth.service;


import com.example.demo.common.base.BaseService;
import com.example.demo.model.auth.dto.UserRoleRelPo;
import com.example.demo.model.auth.repo.UserRoleRelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongr on 2017/7/31.
 */
@Service
public class UserRoleRelService extends BaseService {
    @Autowired
    private UserRoleRelRepo userRoleRelRepo;

    @Override
    public void init() {
        setBaseRepo(userRoleRelRepo);
    }

    public List<UserRoleRelPo> findByUserId(Integer userId) {
        return findList("userId", userId.toString(), UserRoleRelPo.class);
    }
}
