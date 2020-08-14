package com.huayu.mybitsplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.mybitsplus.interfaces.DeptUtils;
import com.huayu.mybitsplus.mapper.DepartmentMapper;
import com.huayu.mybitsplus.mapper.DeptMapper;
import com.huayu.mybitsplus.pojo.Department;
import com.huayu.mybitsplus.pojo.DeptMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptService extends ServiceImpl<DeptMapper, DeptMenu> implements DeptUtils {

}
