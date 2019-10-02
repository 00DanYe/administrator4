package cn.administrator.service.stafffile;

import cn.administrator.pojo.Stafffile;

import java.util.List;

public interface StafffileService {
    /**
     * 得到所有员工档案信息
     * @param fileId 员工ID
     * @return List<Stafffile>
     */
    public List<Stafffile> getStafffile(Integer fileId,String pName);

    /**
     * 新增一条员工档案信息
     * @param stafffile
     * @return int
     */
    public int addStafffile(Stafffile stafffile);

    /**
     * 修改员工档案信息
     * @param stafffile
     * @return int
     */
    public int modifyStafffile(Stafffile stafffile);

    /**
     * 删除员工共档案信息
     * @param fileId
     * @return
     */
    public int deleteStafffile(Integer fileId);
}