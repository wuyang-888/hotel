package cn.itcast.service.admin.impl;

import cn.itcast.dao.admin.UserDao;
import cn.itcast.pojo.admin.User;
import cn.itcast.service.admin.UserService;
import cn.itcast.util.Md5Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * user用户serviceimpl
 *
 * @author llq
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public int add(User user) {
        //密码加密
        user.setPassword(Md5Class.stringToMd5(user.getPassword()));
        return userDao.add(user);
    }

    @Override
    public int edit(User user) {
        return userDao.edit(user);
    }

    @Override
    public int delete(String ids) {
        return userDao.delete(ids);
    }

    @Override
    public List<User> findList(Map<String, Object> queryMap) {
        return userDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return userDao.getTotal(queryMap);
    }

    @Override
    public int editPassword(User user) {
        return userDao.editPassword(user);
    }

}
