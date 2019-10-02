package cn.administrator.pojo;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class Train implements Serializable {
    @NotEmpty(message = "项目名不能为空！")
  private String trainName; //培训的项目名
  private Integer trainId;  //培训id
    @NotEmpty(message="请选择培训类型")
  private String typeCode;    //培训编号
    @NotEmpty(message="请输入培训开始时间")
  private String trainBtime; //培训开始的时间
    @NotEmpty(message = "请输入培训结束时间")
  private String trainEtime;  //培训结束时间
    @NotEmpty(message="请输入培训费")
  private String trainExpense;  //培训费
    @NotEmpty(message="培训地址不能为空")
  private String trainLocation; //培训的地点
    @NotEmpty(message="培训主持人不能为空")
  private String trainHost;   //培训主持
    @NotEmpty(message="请输入培训老师姓名")
  private String trainMaster;   //培训老师
  private Traintype traintype;//培训类型
  public Train() {
  }

}
