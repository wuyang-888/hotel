package cn.itcast.dao;

import cn.itcast.pojo.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户dao
 */
@Repository
public interface AccountDao {
    public int add(Account account);//添加客户

    public int edit(Account account);//编辑客户信息

    public int delete(Long id);//删除客户

    public List<Account> findList(Map<String, Object> queryMap);//分页查询用户

    public Integer getTotal(Map<String, Object> queryMap);//查询当前分页总记录条数

    public Account findById(Long id);//根据id查询用户

    public Account findByName(String name);//通过用户名查询

    public List<Account> findAll();//查询所有用户
}
