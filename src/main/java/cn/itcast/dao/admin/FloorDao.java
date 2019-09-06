package cn.itcast.dao.admin;

import cn.itcast.pojo.admin.Floor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 楼层的持久层接口
 */
@Repository
public interface FloorDao {
    //分页查询
    public List<Floor> findList(Map<String, Object> queryMap)throws Exception;
    //根据信息查询总记录条数
    public Integer getTotal(Map<String, Object> queryMap)throws Exception;
    //查询所有楼层信息
    public List<Floor> findAll()throws Exception;
    //增加楼层信息
    public int add(Floor floor)throws Exception;
    //修改楼层信息
    public int update(Floor floor)throws Exception;
    //删除楼层信息
    public int delete(Long id)throws Exception;
    //根据楼层名字查询楼层信息
    public Floor findByName(String name)throws Exception;

}
