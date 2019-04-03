package com.atguigu.cache.service;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
/*缓存公共配置*/
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要相同的数据，直接从缓存中获取，不需要在调用方法；
     * CacheManager：管理多个Cache组件的，对缓存的真正的操作在Cache组件中，每一个缓存组件有自己唯一的名字；
     * 几个属性：
     * cacheNames/value：指定缓存组件的名字；
     * <p>
     * key:缓存数据时用的key;默认使用方法参数的值
     * 编写SpEl: #id :参数id #a0 #p0 #root.arg[0
     * key = " #root.methodName+'['+#id+']'   得到getEmp[1]
     * <p>
     * keyGenenerator:key的生成器，可以自己指定key的id
     * <p>
     * cacheMananger:指定缓存管理器，或者cacheResolver指定获取解析器
     * <p>
     * condition:指定符合条件下才可以缓存；
     * unless:指定条件下不缓存
     * <p>
     * sync:是否使用异步模式
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "emp", key = " #id ")
    public Employee getEmpById(int id) {
        System.out.println("获取id为" + id + "的员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut:即调用方法，又更新缓存数据 修改了数据库的某个数据，同时更新缓存
     * 运行时机
     * 1、先调用目标方法
     * 2、将目标方法的返回结果缓存起来
     * 测试步骤
     * 1、先查询一个员工
     * 2、然后更新一个员工
     * 3、在查询
     */
    @CachePut(cacheNames = "emp", key = "#employee.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("更新一个emp对象：" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * @CacheEvict:缓存清除 key：指定要删除的数据
     * allEntries = true 删除缓存中的所有数据 此时不需要在指定key
     * beforeInvocation = false  缓存的删除是否在方法执行之前执行
     * 默认代表是在方法执行之后执行
     * <p>
     * beforeInvocation = true
     * 代表清楚缓存操作是在方法执行之前执行，无论是否出异常， 都清楚缓存
     * <p>
     * 测试步骤：
     * 1、拆线呢两次员工测试缓存
     * 2、然后删除，重新查询
     * <p>
     * 属性：
     * allEntries = true 删除缓存中的所有数据 此时不需要在指定key
     */
    @CacheEvict(cacheNames = "emp",/* key = "#id",*/allEntries = true)
    public void deleteEmp(int id) {
        System.out.println("删除一个员工");
    }

@Caching(
        cacheable = {
                @Cacheable(cacheNames = "emp", key = "#lastName")
        },
        put = {
                @CachePut(cacheNames = "emp" ,key = "#result.id"),
                @CachePut(cacheNames = "emp" ,key = "#result.email")
        }


)
    public Employee getEmpByLastName(String lastName) {
        System.out.println("跟据lastName查询");
        return employeeMapper.getEmpByLastName(lastName);
    }
}
