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
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/train")
public class TrainControllerAjax {
    @Autowired
    private TrainService trainService;
    @Autowired
    private TraintypeService traintypeService;
    @Autowired
    private TraingradeService traingradeService;
    @Autowired
    private StaffcertService staffcertService;
    @Autowired
    private StaffService staffService;
    /**
     * ajax请求，采用模糊查询，分页插件
     * 请求可以培训的项目
     * @param
     * @param
     * @return
     */
    @GetMapping(value = "/lookTrainType.do")
    public Object allTrainType(String typeName,String trainName,Integer status,Integer page){
        HashMap<String,Object> result=new HashMap<>();
        Integer pageNum=1;//当前页面
        if(page!=null){
            pageNum=page;
        }
        System.out.println(pageNum+"pageNum===========================");
        Integer pageSize=5;
        PageHelper.startPage(pageNum,pageSize);
        PageHelper.orderBy("train_id desc");//根据train_id 倒序排列
        log.info("trainName+======================="+trainName);
        List<Train> trains= trainService.getCouldTrainType(typeName,trainName,0,status);
        PageInfo pageInfo=new PageInfo(trains);
        Integer numberPage=pageInfo.getPages();
        Integer totalNumber=pageInfo.getPageNum();
        result.put("pageNum",pageNum);
        result.put("totalNumber",totalNumber);//总条数
        result.put("numberPage",numberPage);//总页面
        result.put("trains",trains);
        return JSONArray.toJSONString(result);
    }
    /**
     * 根据类型的编号拿到类型详情
     * @param typeCode
     * @return
     */
    @GetMapping(value = "/typedetail")
    public Object addTrainCategory(String typeCode){
        //根据类型的编号拿到类型详情
        long start = System.currentTimeMillis();
        Traintype traintype=traintypeService.getTraintypeByCode(typeCode);
        long end = System.currentTimeMillis();
        long time = end-start;
        log.error("------------"+traintype.getTypeName()+ ",取出数据用时：" +time + "------------------------");
        return JSONArray.toJSONString(traintype);
    }
    @GetMapping(value = "/typeName")
    public Object typeNameBlur(String typeName){
        HashMap<String,Object> result=new HashMap<>();
        Traintype traintype=traintypeService.getTraintypeByName(typeName);
        if(traintype==null){
            result.put("flag",false);
        }else {
            result.put("flag",true);
        }
        return JSONArray.toJSONString(result);
    }
    @PostMapping(value = "/addTrainCategory.do")
    public Object addTrainCategorydo(@Valid @ModelAttribute("traintype") Traintype traintype,
                                     BindingResult errrors) {
        HashMap<String, Object> result = new HashMap<>();
        if (errrors.hasErrors()) {
            List<FieldError> fieldErrors = errrors.getFieldErrors();

            result.put("errors", fieldErrors);
        } else {
            int i = traintypeService.add(traintype);
            if (i > 0) {
                result.put("flag", true);
            } else {
                result.put("flag", false);

            }
        }
        return JSONArray.toJSONString(result);
    }
    @GetMapping(value = "/TrainRecords")
    public String trainRecords(String name){
        List<Traingrade> traingrades=traingradeService.getAllTraingrade(name,0);
        return JSONArray.toJSONString(traingrades);
    }
    @GetMapping(value = "/trainName")
    public String addTrain(String trainName){
        boolean flag=false;
        log.info("trainName============"+trainName);
        List<Train> train=trainService.getCouldTrainType("",trainName,0,0);
        if(train.size()<1){
            flag=true;
        }
        return JSONArray.toJSONString(flag);
    }
    @GetMapping(value = "/getAllTraintype")
    public Object getTrain(){
        //得到所有的类别
        List<Traintype> traintypeList=traintypeService.getAllTraintype();
        return JSONArray.toJSONString(traintypeList);
    }
    @GetMapping(value = "/getAllTrain")
    public Object getAllTrain(){
        //得到所有的项目
        List<Train> trainList=trainService.getCouldTrainType("","",0,0);
        return JSONArray.toJSONString(trainList);
    }
    /**
     * 删除
     * @return
     */
    @DeleteMapping(value = "/delgrade")
    public String delgrade(Integer gradeId){

        List<Staffcert> staffcerts= staffcertService.getStaffcert("","",gradeId);
        if(staffcerts.size()>0){
            staffcertService.delGradeById(gradeId);
        }
        if(traingradeService.delGradeById(gradeId)>0){
            return "删除成功！";
        }
        return "删除失败！";
    }
    @GetMapping(value = "/pNameBlur.html")
    public Object pNameBlur(String pName){
        HashMap<String,Object> result=new HashMap<>();
        Staff staff= staffService.getStaffByName(pName);
        if(staff!=null){
            result.put("flag",true);
        }else {
            result.put("flag",false);
        }
        return JSONArray.toJSONString(result);
    }
    @GetMapping(value = "/trainNameBlur.html")
    public Object trainNameBlur(String trainName){
        HashMap<String,Object> result=new HashMap<>();
        List<Train> trains=trainService.getCouldTrainType("",trainName,0,0);
        if(trains.size()>0){
            result.put("flag",true);
        }else {
            result.put("flag",false);
        }
        return JSONArray.toJSONString(result);
    }
}
