package cn.administrator.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class Traintype implements Serializable {
    @NotEmpty(message = "培训类型名称不能为空！")
  private String typeName;  //培训类型名称
    @NotEmpty(message = "培训类编号不能为空！")
  private String typeCode;    //培训类型编号
    @NotEmpty(message = "培训内容不能为空！")
  private String typeInfo;    //培训类型简介

  public Traintype() {
  }
}
