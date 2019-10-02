package cn.administrator.controller.inform;

import cn.administrator.pojo.Notification;
import cn.administrator.service.notification.NotificationService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
@Controller
@RequestMapping("/inform")
public class InformController {
    Logger logger= (Logger) Logger.getLogger(String.valueOf(InformController.class));
    @Autowired
    NotificationService notificationService;
    /**
     * 进入发布通知页面
     * @return
     */
    @RequestMapping(value = "/informSend.html",method = RequestMethod.GET)
    public String intoInformSend(){

        return "informSend";
    }

    /**
     * 发布通知
     * @param notification
     * @param bindingResult
     * @return
     */

    @RequestMapping(value = "/informSendSave",method = RequestMethod.POST)
    @ResponseBody
    public Object informSend(@Valid @ModelAttribute("notification") Notification notification,
                             BindingResult bindingResult){
        logger.info("进入控制器==========》》》》");
        HashMap<String, Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            logger.info("==========JSR  303  报错==========");
            List<FieldError> errors = bindingResult.getFieldErrors();
            result.put("errors",errors);
        }else{
            if(notificationService.addNotification(notification)>0){
                logger.info("添加通知成功！！");
                result.put("flag",true);
            }else{
                result.put("flag",true);
            }
        }
        return JSONArray.toJSONString(result);
    }

    /**
     * 进入历史通知页面
     * @return
     */
    @RequestMapping(value = "/informHistroy.html",method = RequestMethod.GET)
    public String intoInformHistroy(Model model){
        List<Notification> notificationList= notificationService.getAllNotification();
        model.addAttribute(notificationList);
        return "informHistory";
    }

    /**
     * 删除通过
     * @param id
     * @return
     */
    @RequestMapping(value = "/delNotification",method = RequestMethod.POST)
    @ResponseBody
    public Object delNotification(
            @RequestParam(value = "notiId",required = false)Integer id){
         HashMap<String, Object> result = new HashMap<>();
         logger.info("进入控制器===============");
         long start=System.currentTimeMillis();
         if(notificationService.deleteNotification(id)>0){
             logger.info("删除成功！！===================");
             result.put("flag",true);
         }else{
             logger.info("删除失败！！！！====");
             result.put("flag",false);
         }
         long end=System.currentTimeMillis();
         long time=start-end;
         logger.info("通过id删除一个通知用时："+time);
         return JSONArray.toJSONString(result);
    }

    /**
     * 查看通知
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/informView",method = RequestMethod.GET)
    public String informView(Model model,
                             @RequestParam(value = "notId")Integer id){
        logger.info("进入控制器了========");
        long start=System.currentTimeMillis();
        Notification notification=notificationService.getNotificationById(id);
        long end=System.currentTimeMillis();
        long time=start-end;
        logger.info("===================》》》"+notification.getInformTitle()+"取数据的用时："+time);
        model.addAttribute("notification",notification);
        return "informView";
    }

    /**
     * 编辑修改通知
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateInform",method = RequestMethod.GET)
    public String updateInform(Model model,@RequestParam(value = "notifiId")Integer id){
        logger.info("进入修改通知页面的控制器了========");
        Notification notification=notificationService.getNotificationById(id);
        model.addAttribute("notification",notification);
        return "informUpdate";
    }

    /**
     * 修改提交保存
     * @param notification
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/updateInformSave",method = RequestMethod.POST)
    @ResponseBody
    public Object updateInformSave(@Valid @ModelAttribute("notification") Notification notification,
//                                   @RequestParam(value = "notificationId")Integer id,
                                   BindingResult bindingResult){
        logger.info("++++++++进入提交修改的控制器==========》》》》");
        HashMap<String, Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            logger.info("==========JSR  303  报错==========");
            List<FieldError> errors = bindingResult.getFieldErrors();
            result.put("errors",errors);
        }else{
//            notification.setInformDate(notification.getInformDate());
            long start=System.currentTimeMillis();
            if(notificationService.updateNotification(notification)!=null){
                logger.info("++++++修改通知成功！！+++++++++++++++===================");
                result.put("flag",true);
            }else{
                result.put("flag",false);
                logger.info("==================修改失败------------------------");
            }
            long end=System.currentTimeMillis();
            long time=start-end;
            logger.info("===------------==修改一个通知用时："+time);
        }
        return JSONArray.toJSONString(result);
    }
}
