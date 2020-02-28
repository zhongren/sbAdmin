package com.example.demo.model.auth.service;


import com.example.demo.common.base.BaseService;
import com.example.demo.model.auth.dto.RolePo;
import com.example.demo.model.auth.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongr on 2017/7/31.
 */
@Service
public class RoleService extends BaseService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void init() {
        setBaseRepo(roleRepo);
    }

    public RolePo findByName(String name) {
        return find("name", name, RolePo.class);
    }
}
