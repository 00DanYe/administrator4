package cn.administrator.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Staffcert implements Serializable {

  private Integer cerId;  //  证书id
  private Integer gradeId;  //成绩id
  private String cerName; //证书名称
  private Date cerDate; //证书时间
  private Staff staff;
  private Traingrade traingrade;
  private Train train;

  public Staffcert() {
  }
}
