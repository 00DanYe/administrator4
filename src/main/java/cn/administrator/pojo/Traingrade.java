package cn.administrator.pojo;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class Traingrade implements Serializable {

  private Integer gradeId;   //等级id
/*    @NotNull(message = "培训项目不能为空！")*/
  private Integer trainId;  //培训项目id
/*    @NotNull(message = "员工名称不能为空！")*/
  private Integer pId;  //职员id
    @NotNull(message = "请输入员工培训成绩！")
  private Integer grade;  //成绩
    @NotEmpty(message = "请输入员工培训的课程！")
  private String subject; //科目
  private String getCer;  //得到的证书
  private Train train;
  private Traintype traintype;
  private Staff staff;

  public Traingrade() {
  }

}
