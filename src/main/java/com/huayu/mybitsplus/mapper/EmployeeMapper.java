package com.huayu.mybitsplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huayu.mybitsplus.pojo.Employee;
import com.huayu.mybitsplus.vo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {
    List<Emp> queryall(Employee employee);

    List<Emp> query(IPage<Employee> page,@Param("emp") Employee employee);

    Employee selectone(String name);
}