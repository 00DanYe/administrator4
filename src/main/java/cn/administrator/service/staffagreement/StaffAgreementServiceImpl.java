package cn.administrator.service.staffagreement;

import cn.administrator.dao.staffagreement.StaffAgreementMapper;
import cn.administrator.pojo.Staffagreement;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "my")
public class StaffAgreementServiceImpl implements StaffAgreementService {
    @Resource
    StaffAgreementMapper staffAgreementMapper;

    @Override
//    @Cacheable(key = "#agreementId",unless = "#result==null")
    public List<Staffagreement> getStaffAgreement(Integer agreementId,String pName) {
        return staffAgreementMapper.getStaffAgreement(agreementId,pName);
    }

    @Override
    public int addStaffAgreement(Staffagreement staffagreement) {
        return staffAgreementMapper.addStaffAgreement(staffagreement);
    }

    @Override
//    @CachePut(key = "#staffagreement.agreementId",unless = "#result==null")
    public int modifyStaffAgreement(Staffagreement staffagreement) {
        return staffAgreementMapper.modifyStaffAgreement(staffagreement);
    }

    @Override
//    @CacheEvict(key = "agreementId")
    public int deleteStaffAgreement(Integer agreementId) {
        return staffAgreementMapper.deleteStaffAgreement(agreementId);
    }
}
