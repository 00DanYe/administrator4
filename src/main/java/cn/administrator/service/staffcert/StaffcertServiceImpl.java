package cn.administrator.service.staffcert;

import cn.administrator.dao.staffcert.StaffcertMapper;
import cn.administrator.pojo.Staffcert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
@MapperScan("cn.administrator.dao")
@CacheConfig(cacheNames = "my")
public class StaffcertServiceImpl implements StaffcertService {
    @Resource
    private StaffcertMapper staffcertMapper;

    @Override
    public List<Staffcert> getStaffcert(String name, String cerName, Integer gradeId) {
        return staffcertMapper.getStaffcert(name,cerName,gradeId);
    }

    @Override
    @CacheEvict
    public int delGradeById(Integer trainId) {
        return staffcertMapper.delGradeById(trainId);
    }

    @Override
    public int add(Staffcert staffcert) {

        return staffcertMapper.add(staffcert);
    }

    @Override
    @CachePut(key = "#staffcert.gradeId",unless = "#result==null")
    public Staffcert update(Staffcert staffcert) {
        staffcertMapper.update(staffcert);
       List<Staffcert> staffcerts= staffcertMapper.getStaffcert("","",staffcert.getGradeId());
        return staffcerts.get(0);
    }
}
