package cn.itcast.service.impl;

import cn.itcast.dao.RoomTypeDao;
import cn.itcast.pojo.RoomType;
import cn.itcast.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeDao roomTypeDao;

    @Override
    public int add(RoomType roomType) {
        return roomTypeDao.add(roomType);
    }

    @Override
    public int edit(RoomType roomType) {
        return roomTypeDao.edit(roomType);
    }

    @Override
    public int delete(Long id) {
        return roomTypeDao.delete(id);
    }

    @Override
    public List<RoomType> findList(Map<String, Object> queryMap) {
        return roomTypeDao.findList(queryMap);
    }

    @Override
    public List<RoomType> findAll() {
        return roomTypeDao.findAll();
    }

    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return roomTypeDao.getTotal(queryMap);
    }

    @Override
    public RoomType find(Long id) {
        return roomTypeDao.find(id);
    }

    @Override
    public int updateNum(RoomType roomType) {
        return roomTypeDao.updateNum(roomType);
    }

}
