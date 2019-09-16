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
    //分页模糊查询客户信息
    public List<Account> findList(Map<String, Object> queryMap)throws Exception;
    //获取当前分页的总记录数
    public Integer getTotal(Map<String, Object> queryMap)throws Exception;
    //查询所有客户信息
    public List<Account> findAll()throws Exception;
    //根据客户id查询客户信息
    public Account findById(Long id)throws Exception;
    //根据客户信息查询客户信息
    public Account findByName(String name)throws Exception;
    //保存客户信息
    public int add(Account account)throws Exception;
    //编辑客户信息
    public int edit(Account account)throws Exception;
    //删除客户信息
    public int delete(Long id)throws Exception;
}
