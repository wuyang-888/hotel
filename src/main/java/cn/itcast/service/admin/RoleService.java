package cn.itcast.service.admin;

import cn.itcast.pojo.admin.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色业务层接口
 *
 *
 */
@Service
public interface RoleService {
    public int add(Role role);

    public int edit(Role role);

    public int delete(Long id);

    public List<Role> findList(Map<String, Object> queryMap);

    public int getTotal(Map<String, Object> queryMap);

    public Role find(Long id);
}
