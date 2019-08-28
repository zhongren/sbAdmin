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
@Table(name = "tb_role")
public class RoleRepo extends BaseRepo {

    @Override
    public void init() {
        ConditionMap conditionMap=new ConditionMap();
        conditionMap.put("id",new Condition("id", Op.EQ));
        conditionMap.put("name",new Condition("name", Op.LIKE));
        setConditionMap(conditionMap);
    }
}
