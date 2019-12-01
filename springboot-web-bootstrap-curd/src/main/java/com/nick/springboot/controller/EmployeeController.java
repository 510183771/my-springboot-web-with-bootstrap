package com.nick.springboot.controller;

import com.nick.springboot.dao.DepartmentDao;
import com.nick.springboot.dao.EmployeeDao;
import com.nick.springboot.entities.Department;
import com.nick.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping(value = "/emps")
    public String getEmployees(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);

        return "emp/list";
    }

    @GetMapping(value = "/emp")
    public String toAddPage(Model model){
        Collection<Department> department = departmentDao.getDepartments();
        model.addAttribute("depts",department);
        return "emp/add";
    }

    @PostMapping(value ="/emp")
    public String addEmployee(Employee employee){
        //System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping(value = "/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "emp/add";
    }

    @PutMapping(value = "/emp")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @PostMapping(value = "/emp/{id}") // 应该用DeleteMapping比较好，但是不是知道为何不生效
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
