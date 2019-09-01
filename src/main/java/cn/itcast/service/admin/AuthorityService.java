package cn.itcast.service.admin;

import cn.itcast.pojo.admin.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限业务层接口
 *
 *
 */
@Service
public interface AuthorityService {
    public int add(Authority authority);

    public int deleteByRoleId(Long roleId);

    public List<Authority> findListByRoleId(Long roleId);
}
