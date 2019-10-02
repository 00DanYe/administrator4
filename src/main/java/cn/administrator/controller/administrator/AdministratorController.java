package cn.administrator.controller.administrator;

import cn.administrator.pojo.Administrator;
import cn.administrator.service.administrator.AdministratorService;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;

    @RequestMapping(value = "/login.html",method = RequestMethod.GET)
    public String login(){
        log.info("登录--------------------");
        return "login";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(String adminUsername, String adminPassword, HttpSession session){
        Map<String ,Object> result = new HashMap<>();
       log.info("登录状态========================");
        Administrator administrator = administratorService.getAdministratorByUserName(adminUsername);
        if (administrator!=null && !administrator.equals("")){
            if (adminPassword.equals(administrator.getAdminPassword())){
                session.setAttribute("admin",administrator);
                result.put("flag",true);
            }else {
                result.put("flag",false);
                result.put("errors","密码不正确!");
                log.info("密码不正确--------------------");
            }
        }else {
            result.put("flag",false);
            result.put("errors","账号不存在!");
            log.info("账号不存在--------------------");
        }
        return JSONArray.toJSONString(result);
    }
    @RequestMapping(value = "/index.html",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "login.out")
    public String loginOut(HttpSession session){
        session.removeAttribute("admin");
        return "login";
    }

}