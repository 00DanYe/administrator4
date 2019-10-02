package cn.administrator.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 人事变动
 */
@Data
public class Staffalter implements Serializable {

  private Integer alterId;  //id
  private Integer apId;  //职员id
//    你跟我说找不到！！！瞎吗？？？这么大一个pName  String型啊
//  @NotEmpty(message = "**职员名称不能为空**")
  private String apName; //职员名称

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Future(message = "**不能选择过去的时间")
  private Date alterTime; //变动时间
  private String alterBstate; //变动前职位
  @NotEmpty(message = "**变动职位不能为空")
  private String alterEstate; //变动后职位
  @NotNull(message = "**变动类型不能为空**")
  private Integer alterType; //变动类型(1、调岗 2、升职 3、降职)
  @NotEmpty(message = "**调动说明不能为空**")
  private String alterContent;  //  调动说明
  private Staff staff;    //职工表

  public Staffalter() {
  }

}
