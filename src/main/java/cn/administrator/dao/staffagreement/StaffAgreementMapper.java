package cn.administrator.dao.staffagreement;

import cn.administrator.pojo.Staffagreement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffAgreementMapper {
    /**
     * 得到员工工资信息
     * @return List<Staffagreement>
     */
    public List<Staffagreement> getStaffAgreement(@Param("agreementId") Integer agreementId,
                                                  @Param("pName") String pName);

    /**
     * 新增一条员工工资信息
     * @return int
     */
    public int addStaffAgreement(Staffagreement staffagreement);

    /**
     * 修改员工工资信息
     * @param staffagreement
     * @return int
     */
    public int modifyStaffAgreement(Staffagreement staffagreement);

    /**
     * 删除员工工资信息
     * @param agreementId
     * @return
     */
    public int deleteStaffAgreement(Integer agreementId);
}
