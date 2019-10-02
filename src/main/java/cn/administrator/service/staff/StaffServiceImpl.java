package cn.administrator.service.staff;

import cn.administrator.dao.staff.StaffMapper;
import cn.administrator.pojo.Staff;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "my")
public class StaffServiceImpl implements StaffService{
    @Resource
    StaffMapper staffMapper;

    @Override
//    @Cacheable(key = "#pId",unless = "#result==null")
    public List<Staff> getStaff(Integer pId,String pName) {
        return staffMapper.getStaff(pId,pName);
    }

    @Override
//    @Cacheable(key="#pName",unless = "#result==null")
    public Staff getStaffByName(String pName) {
        return staffMapper.getStaffByName(pName);
    }

    @Override
    public int addStaff(Staff staff) {
        return staffMapper.addStaff(staff);
    }

    @Override
//    @CachePut(key = "#staff.pId",unless = "#result==null")
    public int modifyStaff(Staff staff) {
        return staffMapper.modifyStaff(staff);
    }

    @Override
//    @CacheEvict(key = "#pId")
    public int deleteStaff(Integer pId) {
        return staffMapper.deleteStaff(pId);
    }
}
