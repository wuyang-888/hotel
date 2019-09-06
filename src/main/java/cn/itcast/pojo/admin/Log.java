package cn.itcast.pojo.admin;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志实体类
 *
 *
 */
@Component
public class Log implements Serializable {
    private Long id;

    private String content;//日志内容

    private Date createTime;//创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
