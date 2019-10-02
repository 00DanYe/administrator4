package cn.administrator.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class Staff implements Serializable {

  private Integer pId;//员工id
  @NotEmpty(message = " * 姓名不能为空")
  private String pName; //员工姓名
  @NotEmpty(message = " * 性别不能为空")
  private String sex;   //员工性别
  @NotEmpty(message = " * 学历不能为空")
  private String degree;  //员工学历
  @NotEmpty(message = " * 部门不能为空")
  private String department;  //部门
  @NotEmpty(message = " * 职位不能为空")
  private String position;  //职位
  @NotEmpty(message = " * 工作状态不能为空")
  private String state; //工作状态
  private String userName;  //登录名
  private String userPassword;  //登录密码

  public Staff() {
  }

}
