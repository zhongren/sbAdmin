package com.example.demo.model.auth.repo;


import com.example.demo.common.base.BaseRepo;
import com.example.demo.common.orm.Condition;
import com.example.demo.common.orm.ConditionMap;
import com.example.demo.common.orm.Op;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by ZR_a on 2017/8/1.
 */
@Repository
@Table(name = "tb_user_role_map")
public class UserRoleRelRepo extends BaseRepo {

    @Override
    public void init() {
        ConditionMap criterionMap = new ConditionMap();
        criterionMap.put("id", new Condition("id", Op.EQ));
        criterionMap.put("userId", new Condition("user_id", Op.EQ));
        criterionMap.put("roleId", new Condition("role_id", Op.EQ));
        setConditionMap(criterionMap);
    }
}
