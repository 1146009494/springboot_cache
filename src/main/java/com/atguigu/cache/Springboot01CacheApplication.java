package com.atguigu.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 *一、 搭建基本环境
 * 1、导入数据库文件，创建department和Employee表
 * 2、创建javabean封装数据
 * 3、整合mybatis操作数据库
 * 		1、配置数据源信息
 * 		2、使用注解版mybatis
 * 			1)、mapperScan指定需要扫描的mapper接口所在的包
 *
 *二、快速体验缓存
 * 		步骤：
 * 			1、开启基于注解的缓存 @EnableCaching
 *			2、标注缓存注解即可
 *				@Cacheable
 *				@CacheEvict
 *				@CachePut
 *默认使用ConcurrentMapCacheManager==ConcurrentMapCache,将数据保存在ConcurrentMap<Object,Object>
 *开发中使用缓存中间件：redis，memcached,ehcache
 * 三、整合redis作为缓存
 * Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件
 * 1、安装redis
 * 2、引入redis starter
 * 3、配置redis
 * 4、测试缓存
 */
@MapperScan("com.atguigu.cache.mapper")
@SpringBootApplication
@EnableCaching
public class Springboot01CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01CacheApplication.class, args);
	}

}
