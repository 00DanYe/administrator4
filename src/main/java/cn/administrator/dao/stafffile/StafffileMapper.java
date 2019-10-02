package cn.administrator.dao.stafffile;

import cn.administrator.pojo.Stafffile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StafffileMapper {
    /**
     * 得到员工档案信息
     * @param fileId 员工ID
     * @return List<Stafffile>
     */
    public List<Stafffile> getStafffile(@Param("fileId") Integer fileId,@Param("pName") String pName);

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
