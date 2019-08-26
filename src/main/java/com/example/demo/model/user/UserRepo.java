package com.example.demo.model.user;

import com.example.demo.common.BaseRepo;
import com.example.demo.common.BaseService;
import com.example.demo.common.orm.ConditionMap;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Table;

@Repository
@Table(name = "tb_user")
public class UserRepo extends BaseRepo {
    @Override
    public void init() {
        ConditionMap conditionMap =new ConditionMap();
        setConditionMap(conditionMap);
    }
}
