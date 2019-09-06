package cn.itcast.pojo.admin;

import java.io.Serializable;

/**
 * 楼层的实体类
 */
public class Floor implements Serializable {
    /*********************私有属性******************************/
    private Long id; //id
    private String name; //名称
    private String remark; //备注
    /***************************set/get*******************************/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
