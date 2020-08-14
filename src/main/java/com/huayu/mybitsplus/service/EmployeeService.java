package com.huayu.mybitsplus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.mybitsplus.interfaces.EmployeeUtils;
import com.huayu.mybitsplus.layulutils.DeptmenuUtils;
import com.huayu.mybitsplus.mapper.EmployeeMapper;
import com.huayu.mybitsplus.pojo.DeptMenu;
import com.huayu.mybitsplus.pojo.Employee;
import com.huayu.mybitsplus.vo.Emp;
import com.huayu.mybitsplus.web.EmployeeWeb;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeUtils {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private IService<DeptMenu> deptMenuIService;

    @Override
    public List<Emp> queryall(@Param("emp") Employee employee) {
        return employeeMapper.queryall(employee);
    }

    @Override
    public List<Emp> query(IPage<Employee> page,Employee employee) {
        return employeeMapper.query(page,employee);
    }

    /**
     * 递归查询上下级部门封装到List<DeptmenuUtils>
     * @param fid 父部门
     * @return
     */
    @Override
    public List<DeptmenuUtils> deptd(Integer fid) {
        List<DeptmenuUtils> list = new ArrayList();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("fid", fid);
        List<DeptMenu> list1 = deptMenuIService.list(queryWrapper);
        for (DeptMenu deptMenu :list1) {
            DeptmenuUtils deptmenuUtils = new DeptmenuUtils();
            deptmenuUtils.setId(deptMenu.getId());
            deptmenuUtils.setTitle(deptMenu.getDname());
            deptmenuUtils.setChildren(deptd(deptMenu.getId()));
            list.add(deptmenuUtils);
        }
        return list;
    }

    @Override
    public Employee selectone(String name) {
        return employeeMapper.selectone(name);
    }

}
