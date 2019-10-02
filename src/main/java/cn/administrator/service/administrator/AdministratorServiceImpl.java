package cn.administrator.service.administrator;

import cn.administrator.dao.administrator.AdministratorMapper;
import cn.administrator.pojo.Administrator;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = "my")
public class AdministratorServiceImpl  implements AdministratorService{
    @Resource
    AdministratorMapper administratorMapper;

    @Override
    @Cacheable(key="#userName",unless = "#result==null")
    public Administrator getAdministratorByUserName(String userName) {
        return administratorMapper.getAdministratorByUserName(userName);
    }
}
