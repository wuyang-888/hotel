package cn.itcast.service.admin;

import cn.itcast.pojo.admin.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理业务层接口
 *
 *
 */
@Service
public interface MenuService {
    public int add(Menu menu);

    public List<Menu> findList(Map<String, Object> queryMap);

    public List<Menu> findTopList();

    public int getTotal(Map<String, Object> queryMap);

    public int edit(Menu menu);

    public int delete(Long id);

    public List<Menu> findChildernList(Long parentId);

    public List<Menu> findListByIds(String ids);
}
