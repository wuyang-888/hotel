package cn.itcast.util;

import cn.itcast.pojo.admin.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于菜单操作的一些公用方法
 *
 * 
 */
public class MenuUtil {
    /**
     * 从给定的菜单中返回所有顶级菜单
     *
     * @param menuList
     * @mapurn
     */
    public static List<Menu> getAllTopMenu(List<Menu> menuList) {
        List<Menu> map = new ArrayList<Menu>();
        for (Menu menu : menuList) {
            if (menu.getParentId() == 0) {
                map.add(menu);
            }
        }
        return map;
    }

    /**
     * 获取所有的二级菜单
     *
     * @param menuList
     * @mapurn
     */
    public static List<Menu> getAllSecondMenu(List<Menu> menuList) {
        List<Menu> map = new ArrayList<Menu>();
        List<Menu> allTopMenu = getAllTopMenu(menuList);
        for (Menu menu : menuList) {
            for (Menu topMenu : allTopMenu) {
                if (menu.getParentId() == topMenu.getId()) {
                    map.add(menu);
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 获取某个二级菜单下的按钮
     *
     * @param menuList
     * @mapurn
     */
    public static List<Menu> getAllThirdMenu(List<Menu> menuList, Long secondMenuId) {
        List<Menu> map = new ArrayList<Menu>();
        for (Menu menu : menuList) {
            if (menu.getParentId() == secondMenuId) {
                map.add(menu);
            }
        }
        return map;
    }
}
