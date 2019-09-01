package cn.itcast.service.admin.impl;
/**
 * 角色role的实现类
 */

import cn.itcast.dao.admin.RoleDao;
import cn.itcast.entity.admin.Role;
import cn.itcast.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public int add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public int edit(Role role) {
        return roleDao.edit(role);
    }

    @Override
    public int delete(Long id) {
        return roleDao.delete(id);
    }

    @Override
    public List<Role> findList(Map<String, Object> queryMap) {
        return roleDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return roleDao.getTotal(queryMap);
    }

    @Override
    public Role find(Long id) {
        return roleDao.find(id);
    }

}
