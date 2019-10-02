package cn.administrator.service.staffagreement;

import cn.administrator.pojo.Staffagreement;

import java.util.List;

public interface StaffAgreementService {
    /**
     * 得到员工工资信息
     * @return List<Staffagreement>
     */
    public List<Staffagreement> getStaffAgreement(Integer agreementId,String pName);

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
     * @return int
     */
    public int deleteStaffAgreement(Integer agreementId);
}
