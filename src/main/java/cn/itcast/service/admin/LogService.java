package cn.itcast.service.admin;

import cn.itcast.pojo.admin.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 日志业务层接口
 *
 *
 */
@Service
public interface LogService {
    public int add(Log log);

    public int add(String content);

    public List<Log> findList(Map<String, Object> queryMap);

    public int getTotal(Map<String, Object> queryMap);

    public int delete(String ids);
}
