package cn.administrator.controller.traintype;

import cn.administrator.service.traintype.TraintypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("trainType")
public class TraintypeController {
    @Autowired
    TraintypeService traintypeService;

}
