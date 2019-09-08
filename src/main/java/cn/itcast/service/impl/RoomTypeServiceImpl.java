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

    /**
     * 增加房间类型信息
     *
     * @param roomType
     * @return
     */
    @Override
    public int add(RoomType roomType) {
        return roomTypeDao.add(roomType);
    }

    /**
     * 编辑房间类型信息
     *
     * @param roomType
     * @return
     */
    @Override
    public int edit(RoomType roomType) {
        return roomTypeDao.edit(roomType);
    }

    /**
     * 删除房间类型信息
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return roomTypeDao.delete(id);
    }

    /**
     * 查询所有房间类型
     *
     * @return
     */
    @Override
    public List<RoomType> findAll() {
        return roomTypeDao.findAll();
    }

    /**
     * 根据房间类型名查询房间类型
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public RoomType findByName(String name) throws Exception {
        return roomTypeDao.findByName(name);
    }

    /**
     * 按条件分页查询
     *
     * @param queryMap
     * @return
     * @throws Exception
     */
    @Override
    public List<RoomType> findLsit(Map<String, Object> queryMap) throws Exception {
        return roomTypeDao.findList(queryMap);
    }

    /**
     * 按条件查询当前分页的记录总数
     *
     * @param queryMap
     * @return
     */
    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return roomTypeDao.getTotal(queryMap);
    }

    /**
     * 根据房间id查询房间类型
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public RoomType findById(Long id) throws Exception {
        return roomTypeDao.findById(id);
    }

    /**
     * 修改可预订房间数
     *
     * @param roomType
     * @return
     */
    @Override
    public int updateNum(RoomType roomType) {
        return roomTypeDao.updateNum(roomType);
    }

}
