package cn.administrator.dao.administrator;

import cn.administrator.pojo.Administrator;

public interface AdministratorMapper {
    /**
     * 通过名字得到用户
     * @return Administrator
     */
    public Administrator getAdministratorByUserName(String userName);
}
