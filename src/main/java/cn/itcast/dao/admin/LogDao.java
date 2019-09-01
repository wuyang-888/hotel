package cn.itcast.dao.admin;

import cn.itcast.pojo.admin.Log;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统日志持久层接口
 *
 *
 */
@Repository
public interface LogDao {
	public int add(Log log);
	public List<Log> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
}
