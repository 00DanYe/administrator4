package cn.administrator;

import cn.administrator.pojo.Administrator;
import cn.administrator.pojo.Staffcert;
import cn.administrator.pojo.Traingrade;
import cn.administrator.pojo.Traintype;
import cn.administrator.service.administrator.AdministratorService;
import cn.administrator.service.staffcert.StaffcertService;
import cn.administrator.service.traingrade.TraingradeService;
import cn.administrator.service.traintype.TraintypeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministratorApplicationTests {
    @Autowired
    TraintypeService traintypeService;

//    @Test
  /*  public void contextLoads() {
        long start = System.currentTimeMillis();
    Traintype traintype=traintypeService.getTraintypeByName("管理培训");
        long end = System.currentTimeMillis();
        long time = end-start;
        log.error("------------"+traintype.getTypeName()+ ",取出数据用时：" +time + "------------------------");

    }*/

}
