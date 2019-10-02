package cn.administrator.controller.stafffile;

import cn.administrator.pojo.Staff;
import cn.administrator.pojo.Stafffile;
import cn.administrator.service.staff.StaffService;
import cn.administrator.service.stafffile.StafffileService;
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
@RequestMapping("/stafffile")
public class StafffileController {
    @Autowired
    StafffileService stafffileService;
    @Autowired
    StaffService staffService;

    @RequestMapping(value = "/viewAllStafffile.html",method = RequestMethod.GET)
    public String viewAllStafffile(Model model,Integer pageIndex, @RequestParam(required = false) String pName){
        int page = 1;
        if (pageIndex!=null){
            page=pageIndex;
        }
        PageHelper.startPage(page,5);
        List<Stafffile> stafffileList = stafffileService.getStafffile(0,pName);
        PageInfo pageInfo=new PageInfo(stafffileList);
        int totalPage = pageInfo.getPages();//总页数
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("page",page);

        model.addAttribute("stafffileList",stafffileList);
        return "viewAllStafffile";
    }

    @RequestMapping(value = "/addStafffile.html",method = RequestMethod.GET)
    public String addStafffile(){
        return "addStafffile";
    }

    @RequestMapping(value = "/addSaveStafffile",method = RequestMethod.POST)
    public String addSaveStafffile(@Valid @ModelAttribute("stafffile") Stafffile stafffile, BindingResult bindingResult,
                                   Model model,@RequestParam(required = false) String modify){
        if (bindingResult.hasErrors()){
            model.addAttribute("stafffile",stafffile);
        }else {
            int i = 0;
            System.out.println("===========addSaveStaff");
            if (modify!=null&&modify.equals("modify")) {
                System.out.println("===========modify");
                i = stafffileService.modifyStafffile(stafffile);
            }else {
                List<Staff> staffList = staffService.getStaff(stafffile.getPId(),"");
                if (staffList.size()==0){
                    model.addAttribute("error","员工编号不存在！");
                    model.addAttribute("stafffile",stafffile);
                } else {
                    System.out.println("===========add");
                    i = stafffileService.addStafffile(stafffile);
                }
            }
            if (i>0){
                return "redirect:/stafffile/viewAllStafffile.html";
            }
        }
        return "addStafffile";
    }

    @RequestMapping(value = "/modifyStafffile.html",method = RequestMethod.GET)
    public String modifyStafffile(@RequestParam Integer fileId,Model model){
        List<Stafffile> stafffileList = stafffileService.getStafffile(fileId,"");
        model.addAttribute("stafffile",stafffileList.get(0));
        model.addAttribute("modify","modify");
        return "addStafffile";
    }

    @RequestMapping(value = "/viewStafffile.html",method = RequestMethod.GET)
    public String viewStafffile(@RequestParam Integer fileId,Model model){
        List<Stafffile> stafffileList = stafffileService.getStafffile(fileId,"");
        model.addAttribute("stafffile",stafffileList.get(0));
        return "viewStafffile";
    }

    @RequestMapping(value = "/deleteStafffile",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteStafffile(@RequestParam Integer fileId){
        Map<String,Object> result = new HashMap<>();
        int i = stafffileService.deleteStafffile(fileId);
        if (i>0){
            result.put("flag",true);
        }else {
            result.put("flag",false);
        }
        return JSONArray.toJSONString(result);
    }
}
