package cn.itcast.dao.admin;

import cn.itcast.pojo.admin.Authority;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久层接口
 *
 *
 */
@Repository
public interface AuthorityDao {
	public int add(Authority authority);
	public int deleteByRoleId(Long roleId);
	public List<Authority> findListByRoleId(Long roleId);
}
