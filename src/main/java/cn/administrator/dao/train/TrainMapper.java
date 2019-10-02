package cn.administrator.dao.train;

import cn.administrator.pojo.Train;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrainMapper {
    /**
     * 添加培训
     * @param train
     * @return
     */
    public int add(Train train);

    /**
     * 得到公司可以培训的类别
     * @return
     */
    public List<Train> getCouldTrainType(@Param("typeName")String typeName,
                                         @Param("trainName")String trainName,
                                         @Param("trainId")Integer trainId,
                                         @Param("status")Integer status);

}
