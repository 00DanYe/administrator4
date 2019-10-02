package cn.administrator.service.notification;

import cn.administrator.pojo.Notification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@MapperScan("cn.administrator.dao")
@SpringBootTest
public class NotificationServiceImplTest {
    @Resource
    NotificationService notificationService;
    @Test
    public void addNotification() {
       /* Notification notification=new Notification();
        notification.setInformTitle("全员加薪");
        notification.setInformDate(new Date());
        if(notificationService.addNotification(notification)>0){
            System.out.println("添加成功++++++++++》》》》》标题是："+notification.getInformTitle());
        };*/
    }

    @Test
    public void updateNotification() {
        Notification notification= notificationService.getNotificationById(8);
        notification.setInformTitle("22222");
        notification.setInformDate(new Date());
        if(notificationService.updateNotification(notification)!=null){
            System.out.println("修改成功++++++++++》》》》》标题是："+notification.getInformTitle());
        };
    }

    @Test
    public void deleteNotification() {

       /* if(notificationService.deleteNotification(27)>0){
            System.out.println("删除成功！！！！！！！！！！！");
        }*/
    }

    @Test
    public void getNotificationById() {
        Notification notification= notificationService.getNotificationById(2);
        if(notification!=null){
            System.out.println("通告0000标题是："+notification.getInformTitle());
        }
    }

    @Test
    public void getAllNotification() {
        List<Notification> notificationList=notificationService.getAllNotification();
        if(notificationList.size()>0){
            System.out.println("得到了所有通知"+notificationList.size()+"条");
        };
    }
}