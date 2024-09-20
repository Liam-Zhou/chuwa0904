package org.fionagu.restdemo.controller;

import org.fionagu.restdemo.Client;
import org.fionagu.restdemo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/*员工接口声明类*/
/*员工controller层*/
@Controller
public class EmployeeController {
    @RequestMapping("/hello")
    @ResponseBody
    public String Hello(){
        return "OK";
    }

    /*获取所有员工信息*/
    @RequestMapping(value = "/employees",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> list(){
        List<Employee> list = Arrays.asList(new Employee(0L,"fifi",20));
        return list;
    }

    /*添加员工数据*/
    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    @ResponseBody
    public Employee add(Employee employee){
        employee.setId(1L);
        return employee;
    }

}



