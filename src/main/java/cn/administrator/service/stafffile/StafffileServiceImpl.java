package cn.administrator.service.stafffile;

import cn.administrator.dao.stafffile.StafffileMapper;
import cn.administrator.pojo.Stafffile;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "my")
public class StafffileServiceImpl implements StafffileService {
    @Resource
    StafffileMapper stafffileMapper;

    @Override
//    @Cacheable(key = "#fileId",unless = "#result==null")
    public List<Stafffile> getStafffile(Integer fileId,String pName) {
        return stafffileMapper.getStafffile(fileId,pName);
    }

    @Override
    public int addStafffile(Stafffile stafffile) {
        return stafffileMapper.addStafffile(stafffile);
    }

    @Override
//    @CachePut(key = "#stafffile.fileId",unless = "#result==null")
    public int modifyStafffile(Stafffile stafffile) {
        return stafffileMapper.modifyStafffile(stafffile);
    }

    @Override
//    @CacheEvict(key = "#fileId")
    public int deleteStafffile(Integer fileId) {
        return stafffileMapper.deleteStafffile(fileId);
    }
}
