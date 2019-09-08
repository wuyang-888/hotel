package cn.itcast.service;
/**
 * 房间类型的业务层接口
 */

import cn.itcast.pojo.RoomType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoomTypeService {
    //按条件分页查询
    public List<RoomType> findLsit(Map<String, Object> queryMap)throws Exception;
    //按条件查询当前分页的记录总数
    public int getTotal(Map<String, Object> queryMap)throws Exception;
    //根据房间id查询房间类型
    public RoomType findById(Long id)throws Exception;
    //查询所有房间类型
    public List<RoomType> findAll()throws Exception;
    //根据房间类型名查询房间类型
    public RoomType findByName(String name)throws Exception;
    //增加房间类型信息
    public int add(RoomType roomType)throws Exception;
    //编辑房间类型信息
    public int edit(RoomType roomType)throws Exception;
    //修改可预订房间数
    public int updateNum(RoomType roomType)throws Exception;
    //删除房间类型信息
    public int delete(Long id)throws Exception;

}
