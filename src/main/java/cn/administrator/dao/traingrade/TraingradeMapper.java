package cn.administrator.dao.traingrade;

import cn.administrator.pojo.Traingrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TraingradeMapper {
    /**
     * 添加培训记录
     * @param traingrade
     * @return
     */
    public int add(Traingrade traingrade);

    /**
     * 模糊查询
     * 按条件查询
     * @return
     */
    public List<Traingrade> getAllTraingrade(@Param("name")String name,
                                             @Param("gradeId")Integer gradeId);
    /**
     *根据trainId删除成绩
     * @param gradeId
     * @return
     */
    public int delGradeById(Integer gradeId);

    /**
     * 更新修改
     * @param traingrade
     * @return
     */
    public int update(Traingrade traingrade);
}
