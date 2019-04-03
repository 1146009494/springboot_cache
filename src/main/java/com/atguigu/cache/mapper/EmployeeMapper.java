package com.atguigu.cache.mapper;

import com.atguigu.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("Select * FROM employee WHERE id=#{id}")
     Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
     void updateEmp(Employee employee);

    @Delete("delete from employee where id=#{id}")
     void deleteEmpById(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId}) ")
     void insertEmp(Employee employee);

    @Select("Select * FROM employee WHERE lastName=#{lastName}")
    Employee getEmpByLastName(String lastName);
}
