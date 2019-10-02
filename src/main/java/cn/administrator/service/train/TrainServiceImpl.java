package cn.administrator.service.train;

import cn.administrator.dao.train.TrainMapper;
import cn.administrator.pojo.Train;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
@Service
@MapperScan("cn.administrator.dao")
@CacheConfig(cacheNames = "my")   //使用 Redis 缓存时，没有指定缓存名，将会报错：no cache could be resolved for at least one cache should be provided per cache operation
public class TrainServiceImpl implements TrainService{
    @Resource
    TrainMapper trainMapper;
    @Override
    public int add(Train train) {
        return trainMapper.add(train);
    }

    @Override
    public List<Train> getCouldTrainType(String typeName, String trainName,Integer trainId,Integer status) {
        return trainMapper.getCouldTrainType(typeName,trainName,trainId,status);
    }


}
