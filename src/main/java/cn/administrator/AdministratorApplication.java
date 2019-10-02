package cn.administrator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("cn.administrator.dao")
@EnableCaching //程序入口开启使用缓存@EnableCaching 等价于 <cache:annotation-driven/> 。能够在服务类方法上标注@Cacheable
public class AdministratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdministratorApplication.class, args);
    }
}