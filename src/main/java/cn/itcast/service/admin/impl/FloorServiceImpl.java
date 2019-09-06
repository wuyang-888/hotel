package cn.itcast.service.admin.impl;

import cn.itcast.dao.admin.FloorDao;
import cn.itcast.pojo.admin.Floor;
import cn.itcast.service.admin.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 楼层业务层实现类
 */
@Service("floorService")
public class FloorServiceImpl implements FloorService{
    /*************************************************************************/
    @Autowired
    private FloorDao floorDao;


    /**************************************************************************/
    /**
     * 模糊分页查询
     * @param queryMap
     * @return
     * @throws Exception
     */
    public List<Floor> findList(Map<String,Object> queryMap) throws Exception {
        return floorDao.findList(queryMap);
    }

    /**
     * 查询总记录条数
     * @param queryMap
     * @return
     * @throws Exception
     */
    public Integer getTotal(Map<String,Object> queryMap) throws Exception {
        return floorDao.getTotal(queryMap);
    }

    /**
     * 查询所有楼层信息
     * @return
     * @throws Exception
     */
    public List<Floor> findAll() throws Exception {
        return floorDao.findAll();
    }


    /**
     * 增加楼层信息
     * @param floor
     * @return
     * @throws Exception
     */
    public int add(Floor floor) throws Exception {
        return floorDao.add(floor);
    }

    /**
     * 编辑楼层信息
     * @param floor
     * @return
     * @throws Exception
     */
    public int edit(Floor floor) throws Exception {
        return floorDao.update(floor);
    }

    /**
     * 删除楼层信息
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Long id) throws Exception {
        return floorDao.delete(id);
    }

    /**
     * 根据楼层名字查询楼层信息
     * @param name
     * @return
     * @throws Exception
     */
    public Floor findByName(String name) throws Exception {
        return floorDao.findByName(name);
    }
}
