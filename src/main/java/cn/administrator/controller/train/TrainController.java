package cn.administrator.controller.train;

import cn.administrator.pojo.*;
import cn.administrator.service.staff.StaffService;
import cn.administrator.service.staffcert.StaffcertService;
import cn.administrator.service.train.TrainService;
import cn.administrator.service.traingrade.TraingradeService;
import cn.administrator.service.traintype.TraintypeService;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/train")
public class TrainController {
    @Autowired
    private  TrainService trainService;
    @Autowired
    private TraintypeService traintypeService;
    @Autowired
    private TraingradeService traingradeService;
    @Autowired
    private StaffcertService staffcertService;
    @Autowired
    private StaffService staffService;
    /**
     * 进入查看公司培训类别
     * @param model
     * @return
     */
    @GetMapping(value = "/lookTrainType.html")
    public String lookTrainType(Model model){
        //得到所有的类别
        List<Traintype> traintypeList=traintypeService.getAllTraintype();
        model.addAttribute("traintypeList",traintypeList);
        return "lookTrainType";
    }

    @GetMapping(value = "/traininfo.html/{trainId}")
    public String traininfo(Model model,@PathVariable(value = "trainId")Integer trainId){
        System.out.println("trainId==================="+trainId);
        List<Train> trains= trainService.getCouldTrainType("","",trainId,0);
        System.out.println(trains.get(0).getTraintype().getTypeInfo()+"======TypeInfo========");
        model.addAttribute("trains",trains);
        return "traininfo";
    }
    @GetMapping(value = "/addTrainCategory.html")
    public String addTrainCategory(Model model){
        //得到所有的类别
        List<Traintype> traintypeList=traintypeService.getAllTraintype();
        model.addAttribute("traintypeList",traintypeList);
        return "addTrainCategory";
    }

    /**
     * 员工培训记录
     * @return
     */
   @GetMapping(value = "/TrainRecords.html")
    public String trainRecords(Model model,String name,Integer page){
       Integer p=1;
       if(page!=null){
           p=page;
       }
       PageHelper.startPage(p,5);
       List<Traingrade> traingrades=traingradeService.getAllTraingrade(name,0);
       PageInfo pageInfo=new PageInfo(traingrades);
       Integer pageNumber=pageInfo.getPages();
       model.addAttribute("pageNumber",pageNumber);
       model.addAttribute("page",p);
       model.addAttribute("traingrades",traingrades);
        return "TrainRecords";
    }
    @GetMapping(value = "/editTrainRecords.html/{gradeId}")
    public String editTrainRecords(Model model,@PathVariable(value = "gradeId") Integer gradeId){
        List<Traingrade> traingrades=traingradeService.getAllTraingrade("",gradeId);
        if(traingrades.get(0).getGetCer().equals("yes")){
            List<Staffcert> staffcert=staffcertService.getStaffcert("","",traingrades.get(0).getGradeId());
            model.addAttribute("cerName",staffcert.get(0).getCerName());
        }
        model.addAttribute("traingrades",traingrades);

        model.addAttribute("gradeId",gradeId);
        return "addTrainRecords";
    }
    @GetMapping(value = "/addTrainRecords.html")
    public String addTrainRecords(Model model){
        return "addTrainRecords";
    }

    /**
     * 添加项目
     * @return
     */
    @GetMapping(value = "/addTrain.html")
    public String addTrain(Model model){
        //得到所有的类别
        List<Traintype> traintypeList=traintypeService.getAllTraintype();
        model.addAttribute("traintypeList",traintypeList);
        return "addTrain";
    }

    @PostMapping(value = "/addTrain.do")
    public String addTrain(Model model,@Valid @ModelAttribute("train")Train train,
                           BindingResult errors ){

        if (errors.hasErrors()) {
            List<FieldError> fieldErrors = errors.getFieldErrors();
            model.addAttribute("train",train);
            model.addAttribute("fieldErrors",fieldErrors);
            return "addTrain";
        }else {
            if(trainService.add(train)>0){
                return "redirect:/train/lookTrainType.html";
            }
            model.addAttribute("train",train);
            model.addAttribute("error","添加失败！");
            return "addTrain";
        }

    }
    /**
     * 员工证书管理
     * @return
     */
    @GetMapping(value = "/StaffCert.html")
    public String StaffCert(Model model,Integer page,String name){
        Integer p=1;
        if(page!=null){
            p=page;
        }
        PageHelper.startPage(p,5);
        List<Staffcert> staffcerts= staffcertService.getStaffcert(name,"",0);
        PageInfo pageInfo=new PageInfo(staffcerts);
        Integer pageNumber=pageInfo.getPages();
        model.addAttribute("page",p);
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("staffcerts",staffcerts);
        return "StaffCert";
    }

    @PostMapping(value = "addTrainRecords.do")
    public String addTrainRecords(@RequestBody @Valid Traingrade traingrade,
                                  String trainName,
                                  String pName,
                                  String getCer,
                                  BindingResult errors,
                                  Model model){
        log.info("进入addTrainRecords.do================================"+traingrade.getGrade());
        List<Train> trains=trainService.getCouldTrainType("",trainName,0,0);
        Staff staff=staffService.getStaffByName(pName);
        traingrade.setTrainId(trains.get(0).getTrainId());
        traingrade.setPId(staff.getPId());
        model.addAttribute("trainName",trainName);
        model.addAttribute("pName",pName);
        model.addAttribute("subject",traingrade.getSubject());
        model.addAttribute("grade",traingrade.getGrade());
        model.addAttribute("cerName",traingrade.getGetCer());
        if(errors.hasErrors()){
            List<FieldError> fieldErrors = errors.getFieldErrors();
           model.addAttribute("fieldErrors",fieldErrors);
            return "addTrainRecords";
        }else {
            log.info("addTrainRecords====================================");
            if(0<=traingrade.getGrade() && traingrade.getGrade()<=100){
                log.info("Grade================"+traingrade.getGrade());
                if(traingradeService.add(traingrade)>0){
                    log.info("添加成功");
                    if(traingrade.getGetCer()!=null){
                        PageHelper.orderBy("grade_id desc");
                        List<Traingrade> traingrades= traingradeService.getAllTraingrade("",0);
                        Staffcert staffcert=new Staffcert();
                        staffcert.setCerDate(new Date());
                        staffcert.setCerName(traingrade.getGetCer());
                        staffcert.setGradeId(traingrades.get(0).getGradeId());
                        staffcertService.add(staffcert);
                    }
                    return "redirect:/train/TrainRecords.html";
                }else {
                    return "addTrainRecords";
                }
            }  else {
                model.addAttribute("error","成绩在1-100之间");
                return "addTrainRecords";
            }

        }

    }
    @PostMapping(value = "editTrainRecords.do")
    public String editTrainRecords(@RequestBody @Valid Traingrade traingrade,
                                  String trainName,
                                  String pName,
                                  String getCer,
                                  BindingResult errors,
                                  Model model){
        log.info("editTrainRecords.do================================"+traingrade.getGrade());
        List<Train> trains=trainService.getCouldTrainType("",trainName,0,0);
        Staff staff=staffService.getStaffByName(pName);
        traingrade.setTrainId(trains.get(0).getTrainId());
        traingrade.setPId(staff.getPId());
        model.addAttribute("trainName",trainName);
        model.addAttribute("pName",pName);
        model.addAttribute("subject",traingrade.getSubject());
        model.addAttribute("grade",traingrade.getGrade());
        model.addAttribute("cerName",traingrade.getGetCer());
        model.addAttribute("gradeId",traingrade.getGradeId());
        if(errors.hasErrors()){
            List<FieldError> fieldErrors = errors.getFieldErrors();
            model.addAttribute("fieldErrors",fieldErrors);
            return "addTrainRecords";
        }else {
            log.info("editTrainRecords====================================");
            if(0<=traingrade.getGrade() && traingrade.getGrade()<=100){
                log.info("Grade================"+traingrade.getGrade());
                if(traingradeService.update(traingrade)!=null){
                    log.info("修改成功");
                    if(traingrade.getGetCer()!=null){
                       List<Staffcert> staffcertList= staffcertService.getStaffcert("","",traingrade.getGradeId());
                       if(staffcertList.size()>0){
                           log.info("修改+证书表==============");
                           Staffcert staffcert=new Staffcert();
                           staffcert.setCerName(traingrade.getGetCer());
                           staffcert.setGradeId(traingrade.getGradeId());
                           staffcertService.update(staffcert);
                       }else {
                           log.info("添加+证书表==============");
                           Staffcert staffcert=new Staffcert();
                           staffcert.setCerDate(new Date());
                           staffcert.setCerName(traingrade.getGetCer());
                           staffcert.setGradeId(traingrade.getGradeId());
                           staffcertService.add(staffcert);
                       }
                    }
                    return "redirect:/train/TrainRecords.html";
                }else {
                    return "addTrainRecords";
                }
            }  else {
                model.addAttribute("error","成绩在1-100之间");
                return "addTrainRecords";
            }

        }

    }
}
