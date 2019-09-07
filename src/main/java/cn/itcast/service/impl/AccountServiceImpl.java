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


        /***************************************************/
        @Autowired
        private AccountDao accountDao;
        /*********************************************************/

        /**
         * 模糊查询
         * @param queryMap
         * @return
         * @throws Exception
         */
        public List<Account> findList(Map<String, Object> queryMap) throws Exception {
            return accountDao.findList(queryMap);

        }

        /**
         * 获取当前分页的总记录数
         * @param queryMap
         * @return
         * @throws Exception
         */
        public Integer getTotal(Map<String, Object> queryMap) throws Exception {
            return accountDao.getTotal(queryMap);
        }

        /**
         * 查询所有客户信息
         * @return
         * @throws Exception
         */
        public List<Account> findAll() throws Exception {
            return accountDao.findAll();
        }

        /**
         * 根据客户id查询客户信息
         * @param id
         * @return
         * @throws Exception
         */
        public Account findById(Long id) throws Exception {
            return accountDao.findById(id);
        }

        /**
         * 根据客户姓名查询客户信息
         * @param name
         * @return
         * @throws Exception
         */
        public Account findByName(String name) throws Exception {
            return accountDao.findByName(name);
        }

        /**
         * 添加客户信息
         * @param account
         * @return
         * @throws Exception
         */
        public int add(Account account) throws Exception {
            return accountDao.add(account);
        }

        /**
         * 编辑客户信息
         * @param account
         * @return
         * @throws Exception
         */
        public int edit(Account account) throws Exception {
            return accountDao.update(account);
        }

        /**
         * 删除客户信息
         * @param id
         * @return
         * @throws Exception
         */
        public int delete(Long id) throws Exception {
            return accountDao.delete(id);
        }
}
