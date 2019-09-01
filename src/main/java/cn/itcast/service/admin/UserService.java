package cn.itcast.service.admin;

import cn.itcast.pojo.admin.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户业务层接口
 *
 *
 */
@Service
public interface UserService {
    public User findByUsername(String username);

    public int add(User user);

    public int edit(User user);

    public int editPassword(User user);

    public int delete(String ids);

    public List<User> findList(Map<String, Object> queryMap);

    public int getTotal(Map<String, Object> queryMap);
}
