package com.example.demo.model.user;

import com.example.demo.common.BaseRepo;
import com.example.demo.common.BaseService;
import com.example.demo.common.orm.Condition;
import com.example.demo.common.orm.ConditionMap;
import com.example.demo.common.orm.Op;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Table;

@Repository
@Table(name = "tb_user")
public class UserRepo extends BaseRepo {
    @Override
    public void init() {
        ConditionMap conditionMap =new ConditionMap();
        conditionMap.put("username",new Condition("username", Op.LIKE));
        conditionMap.put("realName",new Condition("real_name", Op.LIKE));
        setConditionMap(conditionMap);
    }
}
