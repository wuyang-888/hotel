package cn.itcast.service.admin;

import cn.itcast.pojo.admin.Room;

import java.util.List;
import java.util.Map;

/**
 * 房间的业务层接口
 */
public interface RoomService {
    //分页查询房间信息
    public List<Room> findList(Map<String, Object> queryMap)throws Exception;
    //查询当前分页下的总记录数
    public int getTotal(Map<String, Object> queryMap)throws Exception;
    //根据房间id查询房间
    public Room findById(Long id)throws Exception;
    //根据房间编号查询房间信息
    public Room findBySn(String sn)throws Exception;
    //查询所有房间信息
    public List<Room> findAll()throws Exception;
    //增加房间信息
    public int add(Room room)throws Exception;
    //编辑房间信息
    public int edit(Room room)throws Exception;
    //删除房间信息
    public int delete(Long id)throws Exception;
}
