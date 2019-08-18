package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    public List<SpecGroup> queryGroupByCid(Long cid) {
        SpecGroup group=new SpecGroup();
        group.setCid(cid);
        List<SpecGroup> list = specGroupMapper.select(group);//根据对象的非空字段进行查询
        if(CollectionUtils.isEmpty(list)){
            //没查到,抛出异常
            throw  new LyException(ExceptionEnums.SPEC_GROUP_NOT_FOND);
        }
        return list;
    }

    public List<SpecParam> queryGroupByGid(Long gid) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        List<SpecParam> list = specParamMapper.select(param);
        if (CollectionUtils.isEmpty(list)){
            //没找到
            throw new LyException(ExceptionEnums.SPEC_PARAM_NOT_FOND);
        }
        return list;
    }
}
