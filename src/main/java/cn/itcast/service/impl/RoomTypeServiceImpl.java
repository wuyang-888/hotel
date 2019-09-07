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

    /***************************************************************/
    @Autowired
    private RoomTypeDao roomTypeDao;
    /************************************************************/


    /**根据条件模糊查询
     * 分页查询
     *
     * @param queryMap
     * @return
     * @throws Exception
     */
    public List<RoomType> findLsit(Map<String, Object> queryMap) throws Exception {
        return roomTypeDao.findList(queryMap);
    }

    /**
     * 获取当前查询条件下的总记录数
     * @param queryMap
     * @return
     * @throws Exception
     */
    public int getTotal(Map<String, Object> queryMap) throws Exception {
        return roomTypeDao.getTotal(queryMap);
    }

    /**
     * 根据房间类型id查询该房间类型
     * @param id
     * @return
     * @throws Exception
     */
    public RoomType findById(Long id) throws Exception {
        return roomTypeDao.findById(id);
    }

    /**
     * 查询所有房间类型
     * @return
     * @throws Exception
     */
    public List<RoomType> findAll() throws Exception {
        return roomTypeDao.findAll();
    }

    /**
     * 根据房间类型名查询房间类型
     * @param name
     * @return
     * @throws Exception
     */
    public RoomType findByName(String name) throws Exception {
        return roomTypeDao.findByName(name);
    }

    /**
     * 增加房间类型信息
     * @param roomType
     * @return
     * @throws Exception
     */
    public int add(RoomType roomType) throws Exception {
        return roomTypeDao.add(roomType);
    }

    /**
     * 编辑房间类型信息
     * @param roomType
     * @return
     * @throws Exception
     */
    public int edit(RoomType roomType) throws Exception {
        return roomTypeDao.update(roomType);
    }

    /**
     * 修改可预定数
     * @param roomType
     * @return
     * @throws Exception
     */
    public int updateNum(RoomType roomType) throws Exception {
        return roomTypeDao.updateNum(roomType);
    }

    /**
     * 删除房间类型信息
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Long id) throws Exception {
        return roomTypeDao.delete(id);
    }

}
