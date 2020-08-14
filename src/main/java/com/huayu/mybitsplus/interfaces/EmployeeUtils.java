package com.huayu.mybitsplus.interfaces;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huayu.mybitsplus.layulutils.DeptmenuUtils;
import com.huayu.mybitsplus.pojo.Employee;
import com.huayu.mybitsplus.vo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeUtils extends IService<Employee> {
    public List<Emp> queryall(@Param("emp") Employee employee);

    public List<Emp> query(IPage<Employee> page,Employee employee);

    public List<DeptmenuUtils> deptd(Integer fid);

    public Employee selectone(String name);
}
