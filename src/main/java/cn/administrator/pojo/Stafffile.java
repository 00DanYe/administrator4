package cn.administrator.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Stafffile implements Serializable {

  private Integer fileId; //档案id
  @NotNull(message = "员工编号不能为空")
  private Integer pId;    //职员id
  @NotEmpty(message = "档案名称不能为空")
  private String fileName;  //档案名称
  @NotEmpty(message = "档案摘要不能为空")
  private String fileAbstract;  //档案摘要
  @NotEmpty(message = "奖惩记录不能为空")
  private String rewardNote;  //奖惩记录
  @NotEmpty(message = "工作经验不能为空")
  private String experience;  //工作经验
  private Staff staff;


  public Stafffile() {
  }

}
