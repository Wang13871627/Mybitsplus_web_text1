package com.huayu.mybitsplus.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.util.StringUtil;
import com.huayu.mybitsplus.interfaces.EmployeeUtils;
import com.huayu.mybitsplus.interfaces.PositionUtils;
import com.huayu.mybitsplus.layulutils.DeptmenuUtils;
import com.huayu.mybitsplus.layulutils.EmpUtils;
import com.huayu.mybitsplus.layulutils.PicUtils;
import com.huayu.mybitsplus.pojo.Department;
import com.huayu.mybitsplus.pojo.DeptMenu;
import com.huayu.mybitsplus.pojo.Employee;
import com.huayu.mybitsplus.pojo.Position;
import com.huayu.mybitsplus.vo.Emp;
import com.mysql.jdbc.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/employee")
public class EmployeeWeb {
    @Autowired
    private EmployeeUtils employeeService;
    @Autowired
    private IService<Department> departmentService;
    @Autowired
    private PositionUtils positionService;
    @Autowired
    private IService<DeptMenu> deptMenuIService;

    @RequestMapping("/emps.do")
    public String queryall(Employee employee, Model model) {
        List<Emp> list = employeeService.queryall(employee);
        for (Emp employee1 : list) {
            String d = "";
            List<Position> list2 = positionService.queryone1(employee1.getPostid());
            for (Position position : list2) {
                d += position.getPname() + ",";
            }
            d = d.substring(0, d.lastIndexOf(","));
            employee1.setPostid(d);
        }
        model.addAttribute("list", list);
        model.addAttribute("list1", departmentService.list());
        model.addAttribute("list2", positionService.list());
        return "/employee";
    }

    @RequestMapping("/empa.do")
    public String add(Model model) {
        model.addAttribute("list", departmentService.list());
        model.addAttribute("list1", positionService.list());
        return "/employeeadd";
    }

    @PostMapping("/emp1.do")
    public String queryone(@RequestParam("eid") int id, Model model) {
        Employee employee = employeeService.getById(id);
        List<Position> listp = positionService.queryone1(employee.getPostid());
        String s = "";
        for (Position p : listp) {
            s += p.getPname() + ",";
        }
        s = s.substring(0, s.lastIndexOf(","));
        employee.setPostid(s);
        model.addAttribute("emp", employee);
        model.addAttribute("list", departmentService.list());
        model.addAttribute("list1", positionService.list());
        List<String> list2 = new ArrayList<>();
        list2.add("看书");
        list2.add("打篮球");
        list2.add("跑步");
        list2.add("打游戏");
        model.addAttribute("list2", list2);
        return "/employeeupdate";
    }

    @PostMapping("emp.do")
    public String save(@RequestParam("up") MultipartFile idcardup, @RequestParam("down") MultipartFile idcarddown, Employee employee, HttpServletRequest request) {
        try {
            // 图片上传
            // 设置图片名称，不能重复，可以使用uuid
            String picName = UUID.randomUUID().toString();
            String picName1 = UUID.randomUUID().toString() + "1";
            // 获取文件名
            String oriName = idcardup.getOriginalFilename();
            String oriName1 = idcarddown.getOriginalFilename();

            // 获取图片后缀
            String extName = oriName.substring(oriName.lastIndexOf("."));
            String extName1 = oriName1.substring(oriName1.lastIndexOf("."));

            String path = request.getServletContext().getRealPath("/");
            String path1 = new File(path).getParent();
            // 判断，该路径是否存在
            File file = new File(path1 + "/upload");
            if (!file.exists()) {
                // 创建该文件夹
                file.mkdirs();
            }
            // 开始上传
            idcardup.transferTo(new File(file, picName + extName));
            idcarddown.transferTo(new File(file, picName1 + extName1));
            String str = "/upload/" + picName + extName;
            String str1 = "/upload/" + picName1 + extName1;
            employee.setIdcardup(str);
            employee.setIdcarddown(str1);
            employeeService.save(employee);
            return "redirect:/employee/emps.do";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/employee/empa.do";
    }

    @DeleteMapping("/emp.do")
    public String delete(@RequestParam("eid") int id) {
        employeeService.removeById(id);
        return "redirect:/employee/emps.do";
    }

    @PutMapping("/emp.do")
    public String update(@RequestParam("up") MultipartFile idcardup, @RequestParam("down") MultipartFile idcarddown, Employee employee, HttpServletRequest request) {
        int eid = employee.getEid();
        try {
            String picName = UUID.randomUUID().toString();
            String picName1 = UUID.randomUUID().toString() + "1";
            // 获取文件名
            String oriName = idcardup.getOriginalFilename();
            String oriName1 = idcarddown.getOriginalFilename();

            if (oriName.equals("") && oriName1.equals("")) {
                Employee employee1 = employeeService.getById(eid);
                employee.setIdcardup(employee1.getIdcardup());
                employee.setIdcarddown(employee1.getIdcarddown());
            } else if (oriName.equals("") && !oriName1.equals("")) {
                Employee employee1 = employeeService.getById(eid);
                employee.setIdcardup(employee1.getIdcardup());

                String extName1 = oriName1.substring(oriName1.lastIndexOf("."));

                String path = request.getServletContext().getRealPath("/");
                String path1 = new File(path).getParent();
                File file = new File(path1 + "/upload");
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 开始上传
                idcarddown.transferTo(new File(file, picName1 + extName1));
                String str1 = "/upload/" + picName1 + extName1;
                employee.setIdcarddown(str1);
            } else if (!oriName.equals("") && oriName1.equals("")) {
                Employee employee1 = employeeService.getById(eid);
                employee.setIdcarddown(employee1.getIdcarddown());

                String extName = oriName.substring(oriName.lastIndexOf("."));

                String path = request.getServletContext().getRealPath("/");
                String path1 = new File(path).getParent();
                File file = new File(path1 + "/upload");
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 开始上传
                idcardup.transferTo(new File(file, picName + extName));
                String str = "/upload/" + picName + extName;
                employee.setIdcardup(str);
            } else {
                // 获取图片后缀
                String extName = oriName.substring(oriName.lastIndexOf("."));
                String extName1 = oriName1.substring(oriName1.lastIndexOf("."));

                String path = request.getServletContext().getRealPath("/");
                String path1 = new File(path).getParent();
                File file = new File(path1 + "/upload");
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 开始上传
                idcardup.transferTo(new File(file, picName + extName));
                idcarddown.transferTo(new File(file, picName1 + extName1));
                String str = "/upload/" + picName + extName;
                String str1 = "/upload/" + picName1 + extName1;
                employee.setIdcardup(str);
                employee.setIdcarddown(str1);
            }
            employeeService.updateById(employee);
            return "redirect:/employee/emps.do";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/employee/emp1/" + eid + ".do";
    }

    @RequestMapping("/tb.do")
    @ResponseBody
    public EmpUtils querytable(int page, int limit, Employee employee) {

        Page<Employee> page1 = new Page<>(page, limit);
        List<Emp> list = employeeService.query(page1, employee);
        for (Emp employee1 : list) {
            String d = "";
            List<Position> list2 = positionService.queryone1(employee1.getPostid());
            for (Position position : list2) {
                d += position.getPname() + ",";
            }
            d = d.substring(0, d.lastIndexOf(","));
            employee1.setPostid(d);
        }
        EmpUtils empUtils = new EmpUtils();
        //0表示成功 1表示失败
        empUtils.setCode(0);
        empUtils.setMsg("查询成功");
        //数据总条数
        empUtils.setCount(Integer.valueOf(String.valueOf(page1.getTotal())));
        //数据
        empUtils.setData(list);
        return empUtils;
    }

    @RequestMapping("/add.do")
    @ResponseBody
    public List add() {
        return positionService.list();
    }

    @RequestMapping("/add1.do")
    @ResponseBody
    public List add1() {
        return departmentService.list();
    }

    @RequestMapping("/pic.do")
    @ResponseBody
    public PicUtils pic(@RequestParam("file") MultipartFile pictureFile, HttpServletRequest request) {
        PicUtils picUtils = new PicUtils();
        try {
            String picName = UUID.randomUUID().toString();
            String oriName = pictureFile.getOriginalFilename();
            String extName = oriName.substring(oriName.lastIndexOf("."));
            String path = request.getServletContext().getRealPath("/upload");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            pictureFile.transferTo(new File(path, picName + extName));
            picUtils.setCode("1");
            picUtils.setMsg("成功");
            picUtils.setData("/upload/" + picName + extName);
        } catch (IOException e) {
            picUtils.setCode("0");
            picUtils.setMsg("失败");
        }
        return picUtils;
    }

    @RequestMapping("/pic1.do")
    @ResponseBody
    public PicUtils pic1(@RequestParam("file") MultipartFile pictureFile, HttpServletRequest request) {
        PicUtils picUtils = new PicUtils();
        try {
            String picName = UUID.randomUUID().toString();
            String oriName = pictureFile.getOriginalFilename();
            String extName = oriName.substring(oriName.lastIndexOf("."));
            String path = request.getServletContext().getRealPath("/upload");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            pictureFile.transferTo(new File(path, picName + extName));
            picUtils.setCode("1");
            picUtils.setMsg("成功");
            picUtils.setData("/upload/" + picName + extName);
        } catch (IOException e) {
            picUtils.setCode("0");
            picUtils.setMsg("失败");
        }
        return picUtils;
    }

    @RequestMapping("/add2.do")
    @ResponseBody
    public void add2(Employee employee) {
        employeeService.save(employee);
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public PicUtils delete(Integer eid) {
        PicUtils picUtils = new PicUtils();
        try {
            employeeService.removeById(eid);
            picUtils.setCode("1");
            picUtils.setMsg("删除成功");
        } catch (Exception e) {
            picUtils.setMsg("删除失败");
        }
        return picUtils;
    }

    @RequestMapping("/update.do")
    public String update(Integer eid, Model model) {
        Employee employee = employeeService.getById(eid);
        List<Position> listp = positionService.queryone1(employee.getPostid());
        String s = "";
        for (Position p : listp) {
            s += p.getPname() + ",";
        }
        s = s.substring(0, s.lastIndexOf(","));
        employee.setPostid(s);
        model.addAttribute("emp", employee);
        model.addAttribute("list", positionService.list());
        model.addAttribute("list1", departmentService.list());
        return "/layulview/update.html";
    }

    @RequestMapping("/update1.do")
    @ResponseBody
    public void update1(Employee employee) {
        employeeService.updateById(employee);
    }

    @RequestMapping("/delete1.do")
    @ResponseBody
    public PicUtils delete1(String eids) {
        PicUtils picUtils = new PicUtils();
        try {
            String[] str = eids.split(",");
            for (int i = 0; i < str.length; i++) {
                employeeService.removeById(str[i]);
            }
            picUtils.setCode("1");
            picUtils.setMsg("删除成功");
        } catch (Exception e) {
            picUtils.setMsg("删除失败");
        }
        return picUtils;
    }


    @RequestMapping("/dept.do")
    @ResponseBody
    public List dept() {
        int fid=-1;
        return employeeService.deptd(fid);
    }

    @RequestMapping("/tree.do")
    @ResponseBody
    public EmpUtils tree(Integer did,int page,int limit,Employee employee) {
        Page<Employee> page1=new Page<>(page,limit);
        EmpUtils empUtils=new EmpUtils();
        List<Emp> list=employeeService.query(page1,employee);
        if(list.size()>0){
            empUtils.setCode(0);
            empUtils.setMsg("查询成功");
            empUtils.setCount(Integer.valueOf(String.valueOf(page1.getTotal())));
            empUtils.setData(list);
        }else{
            empUtils.setCode(1);
            empUtils.setMsg("无数据");
        }
        return empUtils;
    }

    @RequestMapping("/login.do")
    public String login(String name,String password,Model model) {
        System.out.println(name+"-----------"+password);
        /*用于存储登录的用户名和密码*/
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(name,password);
        /*获取当前登录用户信息*/
        Subject subject=SecurityUtils.getSubject();
        /*自动去调用Realm中的doGetAuthenticationInfo()身份认证*/
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            System.out.println("用户不存在");
            model.addAttribute("user","用户不存在");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码输入错误");
            model.addAttribute("pwd","密码输入错误");
        }
        return "redirect:/layulview/list.html";
    }

}
