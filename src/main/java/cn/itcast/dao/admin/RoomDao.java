package cn.itcast.dao.admin;

import cn.itcast.pojo.admin.Room;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 房间的持久层接口
 */
@Repository
public interface RoomDao {
    //分页模糊查询房间
    public List<Room> findList(Map<String, Object> queryMap)throws Exception;
    //查询指定分页条件下的总记录数
    public int getTotal(Map<String, Object> queryMap)throws Exception;
    //根据id查询房间
    public Room findById(Long id)throws Exception;
    //根据编号查询房间信息
    public Room findBySn(String sn)throws Exception;
    //查询所有
    public List<Room> findAll()throws Exception;
    //增加房间信息
    public int add(Room room)throws Exception;
    //修改房间信息
    public int update(Room room)throws Exception;
    //删除房间信息
    public int delete(Long id)throws Exception;
}
