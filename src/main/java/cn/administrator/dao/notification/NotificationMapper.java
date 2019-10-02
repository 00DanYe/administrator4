package cn.administrator.dao.notification;

import cn.administrator.pojo.Notification;

import java.util.List;

public interface NotificationMapper {
    /**
     * 新增通知
     * @param notification
     * @return
     */
    public int addNotification(Notification notification);

    /**
     * 更新通知
     * @param notification
     * @return
     */
    public int updateNotification(Notification notification);

    /**
     * 删除通知
     * @param id
     * @return
     */
    public int deleteNotification(Integer id);

    /**
     * 通过id得到通知
     * @param id
     * @return
     */
    public Notification getNotificationById(Integer id);

    /**
     * 得到所有通知
     * @return
     */
    public List<Notification> getAllNotification();
}
