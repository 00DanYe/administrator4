package cn.administrator.controller.staff;

import cn.administrator.pojo.Staff;
import cn.administrator.service.staff.StaffService;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @RequestMapping(value = "/viewAllStaff.html",method = RequestMethod.GET)
    public String viewAllStaff(Model model,Integer pageIndex,@RequestParam(required = false) String pName){
        int page = 1;
        if (pageIndex!=null){
            page=pageIndex;
        }
        PageHelper.startPage(page,5);
        List<Staff> staffList = staffService.getStaff(0,pName);
        PageInfo pageInfo=new PageInfo(staffList);
        int totalPage = pageInfo.getPages();//总页数
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("page",page);

        model.addAttribute("staffList",staffList);
        return "viewAllStaff";
    }

    @RequestMapping(value = "/addStaff.html",method = RequestMethod.GET)
    public String addStaff(){
        return "addStaff";
    }

    @RequestMapping(value = "/addSaveStaff",method = RequestMethod.POST)
    public String addSaveStaff(@Valid @ModelAttribute("staff") Staff staff, BindingResult bindingResult,
                               Model model,@RequestParam(required = false) String modify){
        if (bindingResult.hasErrors()){
            model.addAttribute("staff",staff);
        }else {
            int i = 0;
            System.out.println("===========addSaveStaff");
            if (modify!=null&&modify.equals("modify")) {
                System.out.println("===========modify");
                i = staffService.modifyStaff(staff);
            }else {
                System.out.println("===========add");
                i = staffService.addStaff(staff);
            }
            if (i>0){
                return "redirect:/staff/viewAllStaff.html";
            }
        }
        return "addStaff";
    }

    @RequestMapping(value = "/modifyStaff.html",method = RequestMethod.GET)
    public String modifyStaff(@RequestParam Integer pId,Model model){
        List<Staff> staffList = staffService.getStaff(pId,"");
        model.addAttribute("staff",staffList.get(0));
        model.addAttribute("modify","modify");
        return "addStaff";
    }

    @RequestMapping(value = "/deleteStaff",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteStaff(@RequestParam Integer pId){
        Map<String,Object> result = new HashMap<>();
        int i = staffService.deleteStaff(pId);
        if (i>0){
            result.put("flag",true);
        }else {
            result.put("flag",false);
        }
        return JSONArray.toJSONString(result);
    }
}
