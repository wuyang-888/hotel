package cn.itcast.service.admin.impl;
/**
 * 菜单管理实现类
 */

import cn.itcast.dao.admin.MenuDao;
import cn.itcast.entity.admin.Menu;
import cn.itcast.service.admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public int add(Menu menu) {
        return menuDao.add(menu);
    }

    @Override
    public List<Menu> findList(Map<String, Object> queryMap) {
        return menuDao.findList(queryMap);
    }

    @Override
    public List<Menu> findTopList() {
        return menuDao.findTopList();
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return menuDao.getTotal(queryMap);
    }

    @Override
    public int edit(Menu menu) {
        return menuDao.edit(menu);
    }

    @Override
    public int delete(Long id) {
        return menuDao.delete(id);
    }

    @Override
    public List<Menu> findChildernList(Long parentId) {
        return menuDao.findChildernList(parentId);
    }

    @Override
    public List<Menu> findListByIds(String ids) {
        return menuDao.findListByIds(ids);
    }

}
