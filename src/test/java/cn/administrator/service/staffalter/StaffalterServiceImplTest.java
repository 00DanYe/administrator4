package cn.administrator.service.staffalter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@MapperScan("cn.administrator.dao")
@SpringBootTest
public class StaffalterServiceImplTest {
@Resource
StaffalterService staffalterService;
    @Test
    public void addStaffalter() {
    }

    @Test
    public void updateStaffalter() {
    }

    @Test
    public void deleteStaffalter() {
    }

    @Test
    public void getStaffalterById() {

    }

    @Test
    public void getAllStaffalter() {
        if(staffalterService.getAllStaffalter().size()>0){
            System.out.println("======一共有"+staffalterService.getAllStaffalter().size()+"条人事变动消息");
            System.out.println("名字："+staffalterService.getAllStaffalter().get(0).getApName());
        };
    }
}