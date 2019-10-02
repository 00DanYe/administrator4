package cn.administrator.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 事务通知
 */
@Data
public class Notification implements Serializable {

  private Integer notificationId;//通知id
  @NotEmpty(message = "*通知内容不能为空！")
  private String notificationContent;//通知内容
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date informDate;//通知时间
  @NotEmpty(message = "*通知标题不能为空！")
  @Size(min = 5,max = 10,message = "*标题长度不得超过10个字，不得少于5个字")
  private String informTitle;//通知标题

}
