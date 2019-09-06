package cn.itcast.dao.admin;

import cn.itcast.pojo.admin.RoomType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 房间类型的持久层接口
 */
@Repository
public interface RoomTypeDao {
    //分页查询房间类型
    public List<RoomType> findList(Map<String, Object> queryMap)throws Exception;
    //查询当前分页总记录条数
    public Integer getTotal(Map<String, Object> queryMap)throws Exception;
    //查询所有房间类型
    public List<RoomType> findAll()throws Exception;
    //根据id查询
    public RoomType findById(Long id)throws Exception;
    //根据名字查询
    public RoomType findByName(String name)throws Exception;
    //更新房间类型数量
    public int updateNum(RoomType roomType)throws Exception;
    //增加房间类型
    public int add(RoomType roomType)throws Exception;
    //修改房间类型
    public int update(RoomType roomType)throws Exception;
    //删除房间类型
    public int delete(Long id)throws Exception;


}
