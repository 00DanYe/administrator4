package cn.administrator.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Administrator implements Serializable {

  private Integer adminId; //管理员id
  private String adminPassword; //管理员密码
  private String adminUsername;    //登录名

  public Administrator() {
  }

}
