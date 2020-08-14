package com.huayu.mybitsplus.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huayu.mybitsplus.pojo.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionUtils extends IService<Position> {
    public List<Position> queryone1(@Param("s") String s);
}
