package cn.itcast.pojo.admin;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 权限实体类
 *
 *
 */
@Component
public class Authority implements Serializable {
    private Long id;

    private Long roleId;//角色id

    private Long menuId;//菜单id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}
