package cn.itcast.dao;

import cn.itcast.pojo.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户的业务层接口
 */
@Repository
public interface AccountDao {
    //模糊分页查询
    public List<Account> findList(Map<String, Object> queryMap)throws Exception;
    //查询当前分页下的总记录数
    public Integer getTotal(Map<String, Object> queryMap)throws Exception;
    //查询所有
    public List<Account> findAll()throws Exception;
    //根据id查询客户信息
    public Account findById(Long id)throws Exception;
    //根据客户姓名查询客户信息
    public Account findByName(String name)throws Exception;
    //保存数据
    public int add(Account account)throws Exception;
    //修改数据
    public int update(Account account)throws Exception;
    //删除数据
    public int delete(Long id)throws Exception;
}
