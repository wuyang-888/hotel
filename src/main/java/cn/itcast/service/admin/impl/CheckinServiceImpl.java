package cn.itcast.service.admin.impl;

import cn.itcast.dao.admin.CheckinDao;
import cn.itcast.pojo.admin.Checkin;
import cn.itcast.service.admin.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 入住信息的业务层实现类
 */
@Service("checkinService")
public class CheckinServiceImpl implements CheckinService{
    /********************************************************/
    @Autowired
    private CheckinDao checkinDao;
    /************************************************************/

    /**
     * 分页条件查询
     * @param queryMap
     * @return
     * @throws Exception
     */
    public List<Checkin> findList(Map<String, Object> queryMap) throws Exception {
        return checkinDao.findList(queryMap);
    }

    /**
     * 查询当前分页下总记录数
     * @param queryMap
     * @return
     * @throws Exception
     */
    public Integer getTotal(Map<String, Object> queryMap) throws Exception {
        return checkinDao.getTotal(queryMap);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    public Checkin findById(Long id) throws Exception {
        return checkinDao.findById(id);
    }

    /**
     * 添加入住信息
     * @param checkin
     * @return
     * @throws Exception
     */
    public int add(Checkin checkin) throws Exception {
        return checkinDao.add(checkin);
    }

    /**
     * 编辑入住信息
     * @param checkin
     * @return
     * @throws Exception
     */
    public int edit(Checkin checkin) throws Exception {
        return checkinDao.update(checkin);
    }

    /**
     * 删除入住信息
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Long id) throws Exception {
        return checkinDao.delete(id);
    }

    /**
     * 按月获取数据
     * @return
     * @throws Exception
     */
    public List<Map> getStatsByMonth() throws Exception {
        return checkinDao.getStatsByMonth();
    }

    //按日获取数据
    public List<Map> getStatsByDay() throws Exception {
        return checkinDao.getStatsByDay();
    }
}
