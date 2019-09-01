package cn.itcast.service.admin;

import cn.itcast.entity.admin.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限service接口
 *
 * @author llq
 */
@Service
public interface AuthorityService {
    public int add(Authority authority);

    public int deleteByRoleId(Long roleId);

    public List<Authority> findListByRoleId(Long roleId);
}
