package cn.itcast.dao;

import cn.itcast.pojo.RoomType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 房间类型的持久层接口
 */
@Repository
public interface RoomTypeDao {
    public int add(RoomType roomType);//增加房间类型

    public int edit(RoomType roomType);//编辑房间类型

    public int delete(Long id);//删除房间类型

    public List<RoomType> findList(Map<String, Object> queryMap);//分页查询房间类型

    public Integer getTotal(Map<String, Object> queryMap);//查询当前分页总记录条数

    public List<RoomType> findAll();//查询所有房间类型

    public RoomType findById(Long id);//根据id查询

    public RoomType findByName(String name);//根据房间类型名查询房间类型

    public int updateNum(RoomType roomType);//更新房间类型数量
}
