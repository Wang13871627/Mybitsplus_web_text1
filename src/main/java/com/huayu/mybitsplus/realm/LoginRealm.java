package com.huayu.mybitsplus.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huayu.mybitsplus.interfaces.EmployeeUtils;
import com.huayu.mybitsplus.pojo.Employee;
import com.huayu.mybitsplus.vo.Emp;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 身份认证
 *
 * @author Administrator
 */
public class LoginRealm extends AuthorizingRealm {
    @Autowired
    private EmployeeUtils employeeService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", username);
        /*根据名称查询是否为合法用户*/
        Employee employee = employeeService.selectone(username);
        if (employee == null) {
            throw new UnknownAccountException("此用户不存在");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        simpleAuthenticationInfo = new SimpleAuthenticationInfo(employee.getName(), employee.getPassword(), getName());
        return simpleAuthenticationInfo;
    }
}
