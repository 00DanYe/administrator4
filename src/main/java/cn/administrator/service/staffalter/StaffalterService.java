package cn.administrator.service.staffalter;

import cn.administrator.pojo.Staffalter;

import java.util.List;

public interface StaffalterService {
    /**
     * 新增通知
     * @param staffalter
     * @return
     */
    public int addStaffalter(Staffalter staffalter);

    /**
     * 更新通知
     * @param staffalter
     * @return
     */
    public Staffalter updateStaffalter(Staffalter staffalter);

    /**
     * 删除通知
     * @param id
     * @return
     */
    public int deleteStaffalter(Integer id);

    /**
     * 通过id得到通知
     * @param id
     * @return
     */
    public Staffalter getStaffalterById(Integer id);

    /**
     * 得到所有通知
     * @return
     */
    public List<Staffalter> getAllStaffalter();
}
