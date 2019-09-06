package cn.itcast.service;
/**
 * 房型service
 */

import cn.itcast.pojo.RoomType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoomTypeService {
    public int add(RoomType roomType);

    public int edit(RoomType roomType);

    public int delete(Long id);

    public List<RoomType> findList(Map<String, Object> queryMap);

    public List<RoomType> findAll();

    public Integer getTotal(Map<String, Object> queryMap);

    public RoomType find(Long id);

    public int updateNum(RoomType roomType);
}
