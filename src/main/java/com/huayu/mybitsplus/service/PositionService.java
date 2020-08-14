package com.huayu.mybitsplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.mybitsplus.interfaces.PositionUtils;
import com.huayu.mybitsplus.mapper.PositionMapper;
import com.huayu.mybitsplus.pojo.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PositionService extends ServiceImpl<PositionMapper,Position> implements PositionUtils {
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<Position> queryone1(@Param("s") String s) {
        return positionMapper.queryone1(s);
    }

}
