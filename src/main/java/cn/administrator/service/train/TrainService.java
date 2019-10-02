package cn.administrator.service.train;

import cn.administrator.pojo.Train;

import java.util.List;

public interface TrainService {
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
    public List<Train> getCouldTrainType(String typeName,String trainName,Integer trainId,Integer status);
}
