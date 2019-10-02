package cn.administrator.controller.changeInfo;

import cn.administrator.pojo.Staff;
import cn.administrator.pojo.Staffalter;
import cn.administrator.service.staff.StaffService;
import cn.administrator.service.staffalter.StaffalterService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/inform")
public class HrChangeInfoController {
    @Autowired
    StaffalterService staffalterService;
    @Autowired
    StaffService staffService;
    Logger logger= (Logger) Logger.getLogger(String.valueOf(HrChangeInfoController.class));
    /**
     * 进入人事变动消息录入页面
     * @return
     */
    @RequestMapping(value = "/hrChangeInfoAdd.html",method = RequestMethod.GET)
    public String hrChangeInfoAdd(){
        return "hrChangeInfoAdd";
    }

    /**
     * 人事变动录入提交方法
     * @param staffalter
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = "/changeInfoAddSave",method = RequestMethod.POST)
    @ResponseBody
    public Object changeInfoAddSave(@Valid @ModelAttribute("staffalter")Staffalter staffalter,
                                    BindingResult bindingResult, Model model){
        HashMap<String, Object> result = new HashMap<>();
        logger.info("进入控制器===============");
        if(bindingResult.hasErrors()){
            logger.info("-----JSR 303 报错了-------");
            List<FieldError> errors=bindingResult.getFieldErrors();
            model.addAttribute("errors",errors);
            result.put("errors",errors);
        }else {
            if (staffalterService.addStaffalter(staffalter) > 0) {
                logger.info("添加成功！！===================");
                result.put("flag", true);
            } else {
                logger.info("添加失败！！！！====");
                result.put("flag", false);
            }
        }
        return JSONArray.toJSONString(result);
    }

    /**
     * 进入人事变动消息查看列表页面
     * @return
     */
    @RequestMapping(value = "/hrChangeInfoShow.html",method = RequestMethod.GET)
    public String hrChangeInfoShow(Model model){
        List<Staffalter> staffalters= staffalterService.getAllStaffalter();
        model.addAttribute("staffalters",staffalters);
        return "hrChangeInfoShow";
    }

    /**
     * 进入详情页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/hrChangeInfoView.html",method = RequestMethod.GET)
    public String hrChangeInfoView(Model model,
              @RequestParam(value = "alterid",required = false)Integer id){
        Staffalter staffalter=staffalterService.getStaffalterById(id);
        model.addAttribute("staffalter",staffalter);
        return "hrChangeInfoView";
    }

    @RequestMapping(value = "/deleteAlter",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteAlter(
            @RequestParam(value = "altid",required = false)Integer id){
        HashMap<String, Object> result = new HashMap<>();
        logger.info("进入控制器===============");
        if(staffalterService.deleteStaffalter(id)>0){
            logger.info("删除成功！！===================");
            result.put("flag",true);
        }else{
            logger.info("删除失败！！！！====");
            result.put("flag",false);
        }
        return JSONArray.toJSONString(result);
    }

    /**
     * 通过名称判断员工是否存在
     * @param apName
     * @return
     */
    @RequestMapping(value = "/exisApName",method = RequestMethod.POST)
    @ResponseBody
    public Object exisApName(@RequestParam(value = "apName")String apName){
        HashMap<String, Object> result = new HashMap<>();
        logger.info("进入控制器===============");
        Staff staff=staffService.getStaffByName(apName);
        if(staff!=null){
            logger.info("此员工存在！！===================");
            result.put("position",staff.getPosition());
            result.put("flag",true);

        }else{
            logger.info("此员工不存在！！！！====");
            result.put("flag",false);
        }
        return JSONArray.toJSONString(result);
    }
}
