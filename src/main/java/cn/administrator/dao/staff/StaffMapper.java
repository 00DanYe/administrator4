package cn.administrator.dao.staff;

import cn.administrator.pojo.Staff;

import java.util.List;

public interface StaffMapper {
    /**
     * 得到员工信息
     * @return List<Staff>
     */
    public List<Staff> getStaff(Integer pId,String pName);

    /**
     * 根据员工姓名得到员工信息
     * @return List<Staff>
     */
    public Staff getStaffByName(String pName);

    /**
     * 新增一条员工信息
     * @return int
     */
    public int addStaff(Staff staff);

    /**
     * 修改员工信息
     * @param staff
     * @return int
     */
    public int modifyStaff(Staff staff);

    /**
     * 删除员工信息
     * @param pId
     * @return
     */
    public int deleteStaff(Integer pId);
}
