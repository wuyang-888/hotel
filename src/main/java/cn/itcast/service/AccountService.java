package cn.itcast.service;
/**
 * 客户service
 */

import cn.itcast.pojo.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AccountService {
    public int add(Account account);//保存客户信息

    public int edit(Account account);//编辑客户信息

    public int delete(Long id);//删除客户信息

    public List<Account> findList(Map<String, Object> queryMap);//客户信息搜索查询

    public Integer getTotal(Map<String, Object> queryMap);//获取当前分页的总记录数

    public Account findById(Long id);//根据客户id查询客户信息

    public Account findByName(String name);//根据客户名称查询客户信息

    public List<Account> findAll();//查询所有客户信息
}
