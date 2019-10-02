package cn.administrator.controller.staffagreement;

import cn.administrator.pojo.Staffagreement;
import cn.administrator.service.staffagreement.StaffAgreementService;
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
@RequestMapping("/staffAgree")
public class StaffAgreeController {
    @Autowired
    StaffAgreementService staffAgreementService;

    @RequestMapping(value = "/viewAllAgreement.html",method = RequestMethod.GET)
    public String viewAllAgreement(Model model,Integer pageIndex,@RequestParam(required = false) String pName){
        int page = 1;
        if (pageIndex!=null){
            page=pageIndex;
        }
        PageHelper.startPage(page,5);
        List<Staffagreement> staffagreementList = staffAgreementService.getStaffAgreement(0,pName);
        PageInfo pageInfo=new PageInfo(staffagreementList);
        int totalPage = pageInfo.getPages();//总页数
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("page",page);

        model.addAttribute("staffagreementList",staffagreementList);
        return "viewAllAgreement";
    }

    @RequestMapping(value = "/addAgreement.html",method = RequestMethod.GET)
    public String addAgreement(){
        return "addAgreement";
    }

    @RequestMapping(value = "/addSaveAgreement",method = RequestMethod.POST)
    public String addSaveAgreement(@Valid @ModelAttribute("staffagreement") Staffagreement staffagreement,
                                   BindingResult bindingResult,Model model,@RequestParam(required = false) String modify){
        System.out.println(">>>>>>>addSaveAgreement");
        if (bindingResult.hasErrors()){
            model.addAttribute("staffagreement",staffagreement);
        }else {
            int i = 0;
            System.out.println("===========addSaveStaff");
            if (modify!=null&&modify.equals("modify")) {
                System.out.println("===========modify");
                i = staffAgreementService.modifyStaffAgreement(staffagreement);
            }else {
                System.out.println("===========add");
                i = staffAgreementService.addStaffAgreement(staffagreement);
            }
            if (i>0){
                return "redirect:/staffAgree/viewAllAgreement.html";
            }
        }
        return "addAgreement";
    }

    @RequestMapping(value = "/modifyAgreement.html",method = RequestMethod.GET)
    public String modifyAgreement(@RequestParam Integer agreementId,Model model){
        List<Staffagreement> staffagreementList = staffAgreementService.getStaffAgreement(agreementId,"");
        model.addAttribute("staffagreement",staffagreementList.get(0));
        model.addAttribute("modify","modify");
        return "addAgreement";
    }

    @RequestMapping(value = "/deleteAgreement",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteAgreement(@RequestParam Integer agreementId){
        Map<String,Object> result = new HashMap<>();
        int i = staffAgreementService.deleteStaffAgreement(agreementId);
        if (i>0){
            result.put("flag",true);
        }else {
            result.put("flag",false);
        }
        return JSONArray.toJSONString(result);
    }
}
