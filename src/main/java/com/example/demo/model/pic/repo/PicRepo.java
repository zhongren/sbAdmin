package com.example.demo.model.pic.repo;

import com.example.demo.common.base.BaseRepo;
import com.example.demo.common.orm.Condition;
import com.example.demo.common.orm.ConditionMap;
import com.example.demo.common.orm.Op;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "tb_pic")
public class PicRepo extends BaseRepo {
    @Override
    public void init() {
        ConditionMap conditionMap = new ConditionMap();
        conditionMap.put("fileName", new Condition("file_name", Op.LIKE));
        conditionMap.put("type", new Condition("type", Op.EQ));
        setConditionMap(conditionMap);
    }
}
