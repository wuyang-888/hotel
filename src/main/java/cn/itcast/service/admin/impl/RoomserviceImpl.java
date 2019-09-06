package cn.itcast.service.admin.impl;

import cn.itcast.dao.admin.RoomDao;
import cn.itcast.pojo.admin.Room;
import cn.itcast.service.admin.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 房间的业务层接口
 */
@Service("/roomService")
public class RoomserviceImpl implements RoomService{
    /***********************************************************/
    @Autowired
    private RoomDao roomDao;


    /*************************************************************/
    /**
     * 模糊查询
     * 分页查询
     * @param queryMap
     * @return
     * @throws Exception
     */
    public List<Room> findList(Map<String, Object> queryMap) throws Exception {
        return roomDao.findList(queryMap);
    }

    /**
     * 模糊查询
     * 当前分页下总记录数
     * @param queryMap
     * @return
     * @throws Exception
     */
    public int getTotal(Map<String, Object> queryMap) throws Exception {
        return roomDao.getTotal(queryMap);
    }

    /**
     * 根据房间Id查询
     * @param id
     * @return
     * @throws Exception
     */
    public Room findById(Long id) throws Exception {
        return roomDao.findById(id);
    }

    /**
     * 根据房间编号查询房间
     * @param sn
     * @return
     * @throws Exception
     */
    public Room findBySn(String sn) throws Exception {
        return roomDao.findBySn(sn);
    }

    /**
     * 查询所有客户信息
     * @return
     * @throws Exception
     */
    public List<Room> findAll() throws Exception {
        return roomDao.findAll();
    }

    /**
     * 增加房间信息
     * @param room
     * @return
     * @throws Exception
     */
    public int add(Room room) throws Exception {
        return roomDao.add(room);
    }

    /**
     * 编辑房间信息
     * @param room
     * @return
     * @throws Exception
     */
    public int edit(Room room) throws Exception {
        return roomDao.update(room);
    }

    /**
     * 删除房间信息
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Long id) throws Exception {
        return roomDao.delete(id);
    }
}
