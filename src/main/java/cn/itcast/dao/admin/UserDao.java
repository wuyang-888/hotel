package cn.itcast.dao.admin;

import cn.itcast.pojo.admin.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *用户持久层接口
 *
 *
 */
@Repository
public interface UserDao {
    public User findByUsername(String username);

    public int add(User user);

    public int edit(User user);

    public int editPassword(User user);

    public int delete(String ids);

    public List<User> findList(Map<String, Object> queryMap);

    public int getTotal(Map<String, Object> queryMap);
}
