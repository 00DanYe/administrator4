package cn.administrator.service.traintype;

import cn.administrator.dao.train.TrainMapper;
import cn.administrator.dao.traintype.TraintypeMapper;
import cn.administrator.pojo.Traintype;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
@MapperScan("cn.administrator.dao")
@CacheConfig(cacheNames = "my")
public class TraintypeServiceImpl implements TraintypeService{
    @Resource
    TraintypeMapper traintypeMapper;
    @Override
    public int add(Traintype traintype) {
        return traintypeMapper.add(traintype);
    }

    @Override
    public List<Traintype> getAllTraintype() {
        return traintypeMapper.getAllTraintype();
    }

    @Override
    @Cacheable(key = "#typeName",unless = "#result==null")
    public Traintype getTraintypeByName(String typeName) {
        return traintypeMapper.getTraintypeByName(typeName);
    }

    @Override
    @Cacheable(key = "#typeCode",unless = "#result==null")
    public Traintype getTraintypeByCode(String typeCode) {
        return traintypeMapper.getTraintypeByCode(typeCode);
    }
}
