package cn.administrator.service.traintype;

import cn.administrator.pojo.Traintype;

import java.util.List;

public interface TraintypeService {
    /**
     * 添加培训类型
     * @param traintype
     * @return
     */
    public int add(Traintype traintype);

    /**
     *得到所有的培训类型
     * @return List<Traintype>
     */
    public List<Traintype> getAllTraintype();
    /**
     * 根据种类名称得到Traintype
     * @param typeName
     * @return Traintype
     */
    public Traintype getTraintypeByName(String typeName);
    /**
     * 根据种类编号得到Traintype
     * @param typeCode
     * @return Traintype
     */
    public Traintype getTraintypeByCode(String typeCode);
}
