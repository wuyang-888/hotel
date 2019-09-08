package cn.itcast.service.impl;

import cn.itcast.dao.AccountDao;
import cn.itcast.pojo.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    /**
     * 保存客户信息
     * @param account
     * @return
     */
    @Override
    public int add(Account account) {
        return accountDao.add(account);
    }

    /**
     * 编辑客户信息
     * @param account
     * @return
     */
    @Override
    public int edit(Account account) {
        return accountDao.edit(account);
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return accountDao.delete(id);
    }

    /**
     * 客户信息搜索查询
     * @param queryMap
     * @return
     */
    @Override
    public List<Account> findList(Map<String, Object> queryMap) {
        return accountDao.findList(queryMap);
    }

    /**
     * 获取当前分页的总记录数
     * @param queryMap
     * @return
     */
    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return accountDao.getTotal(queryMap);
    }

    /**
     * 根据id查询客户信息
     * @param id
     * @return
     */
    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    /**
     * 根据姓名查询客户信息
     * @param name
     * @return
     */
    @Override
    public Account findByName(String name) {
        return accountDao.findByName(name);
    }

    /**
     * 查询所有客户
     * @return
     */
    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

}
