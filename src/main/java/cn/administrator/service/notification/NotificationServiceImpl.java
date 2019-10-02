package cn.administrator.service.notification;

import cn.administrator.dao.notification.NotificationMapper;
import cn.administrator.pojo.Notification;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
@SpringBootApplication
@MapperScan("cn.administrator.dao")
@CacheConfig(cacheNames = "notification")
public class NotificationServiceImpl implements NotificationService {
    @Resource
    private NotificationMapper notificationMapper;
    @Override
    public int addNotification(Notification notification) {
        return notificationMapper.addNotification(notification);
    }

    @Override
    @CachePut(key = "#notification.notificationId",unless = "#result==null")
    public Notification updateNotification(Notification notification) {
        notificationMapper.updateNotification(notification);
        Notification notification1=notificationMapper.getNotificationById(notification.getNotificationId());
        return notification1;
    }

    @Override
    @CacheEvict(key = "#p0")
    public int deleteNotification(Integer id) {

        return notificationMapper.deleteNotification(id);
    }

    @Override
    @Cacheable(key = "#id",unless = "#result==null")
    public Notification getNotificationById(Integer id) {

        return notificationMapper.getNotificationById(id);
    }

    @Override
    public List<Notification> getAllNotification() {
        return notificationMapper.getAllNotification();
    }
}
