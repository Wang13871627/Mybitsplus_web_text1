package com.huayu.mybitsplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.huayu.mybitsplus.mapper.DepartmentMapper;
import com.huayu.mybitsplus.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService extends ServiceImpl<DepartmentMapper,Department> {

}
