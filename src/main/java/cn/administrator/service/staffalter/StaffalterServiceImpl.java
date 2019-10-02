package cn.administrator.service.staffalter;

import cn.administrator.dao.staffalter.StaffalterMapper;
import cn.administrator.pojo.Staffalter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
@SpringBootApplication
@MapperScan("cn.administrator.dao")
@CacheConfig(cacheNames = "staffalter")
public class StaffalterServiceImpl implements StaffalterService {
    @Resource
    private StaffalterMapper staffalterMapper;
    @Override
    public int addStaffalter(Staffalter staffalter) {
        return staffalterMapper.addStaffalter(staffalter);
    }

    @Override
    @CachePut(key = "#staffalter.alterId",unless = "#result==null")
    public Staffalter updateStaffalter(Staffalter staffalter) {
        staffalterMapper.updateStaffalter(staffalter);
        Staffalter staffalter1=staffalterMapper.getStaffalterById(staffalter.getAlterId());
        return staffalter1;
    }

    @Override
    @CacheEvict(key = "#p0")
    public int deleteStaffalter(Integer id) {
        return staffalterMapper.deleteStaffalter(id);
    }

    @Override
    @Cacheable(key = "#id",unless = "#result==null")
    public Staffalter getStaffalterById(Integer id) {
        return staffalterMapper.getStaffalterById(id);
    }

    @Override
    public List<Staffalter> getAllStaffalter() {
        return staffalterMapper.getAllStaffalter();
    }
}
