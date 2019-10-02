package cn.administrator.service.traingrade;

import cn.administrator.dao.traingrade.TraingradeMapper;
import cn.administrator.pojo.Traingrade;
import cn.administrator.service.traintype.TraintypeService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
@Service
@MapperScan("cn.administrator.dao")
@CacheConfig(cacheNames = "my")
public class TraingradeServiceImpl implements TraingradeService {
    @Resource
    TraingradeMapper traingradeMapper;
    @Override
    public int add(Traingrade traingrade) {
        return traingradeMapper.add(traingrade);
    }

    @Override
    public List<Traingrade> getAllTraingrade(String name, Integer gradeId) {
        return traingradeMapper.getAllTraingrade(name,gradeId);
    }

    @Override
    @CacheEvict(key = "#p0")
    public int delGradeById(Integer gradeId) {

        return traingradeMapper.delGradeById(gradeId);
    }

    @Override
    @CachePut(key = "#traingrade.gradeId",unless = "#result==null")
    public Traingrade update(Traingrade traingrade) {
        traingradeMapper.update(traingrade);
       List<Traingrade> traingrades= traingradeMapper.getAllTraingrade("",traingrade.getGradeId());

       return traingrades.get(0);
    }
}
