package cn.itcast.dao.admin;

import cn.itcast.pojo.admin.Checkin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 入住的持久层接口
 */
@Repository
public interface CheckinDao {
    //分页模糊查询
    public List<Checkin> findList(Map<String, Object> queryMap)throws Exception;
    //查询当前分页下总记录数
    public Integer getTotal(Map<String, Object> queryMap)throws Exception;
    //根据id查询
    public Checkin findById(Long id)throws Exception;
    //添加信息
    public int add(Checkin checkin)throws Exception;
    //修改信息
    public int update(Checkin checkin)throws Exception;
    //删除信息
    public int delete(Long id)throws Exception;
    //获取月数据
    public List<Map> getStatsByMonth()throws Exception;
    //获取日数据
    public List<Map> getStatsByDay()throws Exception;
}
