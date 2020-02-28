package com.example.demo.model.auth.repo;


import com.example.demo.common.base.BaseRepo;
import com.example.demo.common.orm.ConditionMap;
import com.example.demo.common.util.BeanUtil;
import com.example.demo.model.MapperConstant;
import com.example.demo.model.auth.dto.PermPo;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * Created by ZR_a on 2017/8/1.
 */
@Repository
@Table(name = "tb_perm")
public class PermRepo extends BaseRepo {

    @Override
    public void init() {
        ConditionMap conditionMap = new ConditionMap();
        setConditionMap(conditionMap);
    }

    public List<PermPo> findUserPerm(long userId) {
        List<Map<String, Object>> dataList = sqlSessionTemplate.selectList(MapperConstant.findUserPerm, userId);
        return BeanUtil.convertMap2List(dataList, PermPo.class);
    }
}
