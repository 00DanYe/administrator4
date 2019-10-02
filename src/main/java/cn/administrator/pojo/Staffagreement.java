package cn.administrator.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class Staffagreement implements Serializable {

  private Integer agreementId;  //员工协议id
  @NotNull(message = "员工编号不能为空")
  private Integer pId;    //员工id
  @NotNull(message = "开始时间不能为空")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date agreementBtime;  //协议生效时间
  @NotNull(message = "结束时间不能为空")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date agreementEtime;  //协议截止日期
  @NotEmpty(message = "职位不能为空")
  private String position;  //职位
  @NotEmpty(message = "工资不能为空")
  private String agreementContent;//意向协议书
  private Staff staff;

  public Staffagreement() {
  }

}
