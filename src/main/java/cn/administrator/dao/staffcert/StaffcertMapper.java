package cn.administrator.dao.staffcert;

import cn.administrator.pojo.Staffcert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffcertMapper {
    /**
     * 模糊查询
     */
    public List<Staffcert> getStaffcert(@Param("name") String name,
                                        @Param("cerName")String cerName,
                                        @Param("gradeId")Integer gradeId);
    /**
     *根据trainId删除记录
     * @param trainId
     * @return
     */
    public int delGradeById(Integer trainId);

    /**
     * 添加
     * @param staffcert
     * @return
     */
    public int add(Staffcert staffcert);

    /**
     * 更新修改
     * @param staffcert
     * @return
     */
    public int update(Staffcert staffcert);
}
