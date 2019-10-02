package cn.administrator.service.administrator;

        import cn.administrator.pojo.Administrator;

public interface AdministratorService {
    /**
     * 通过名字得到用户
     * @return Administrator
     */
    public Administrator getAdministratorByUserName(String userName);
}
