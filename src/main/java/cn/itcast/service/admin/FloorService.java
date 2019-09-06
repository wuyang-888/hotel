package cn.itcast.service.admin;

import cn.itcast.pojo.admin.Floor;

import java.util.List;
import java.util.Map;

/**
 * 楼层业务层接口
 */
public interface FloorService {
    //根据条件分页查询
    public List<Floor> findList(Map<String, Object> queryMap)throws Exception;
    //根据条件查询总记录条数
    public Integer getTotal(Map<String, Object> queryMap)throws Exception;
    //查询所有楼层信息
    public List<Floor> findAll()throws Exception;
    //增加楼层信息
    public int add(Floor floor)throws Exception;
    //编辑楼层信息
    public int edit(Floor floor)throws Exception;
    //删除楼层信息
    public int delete(Long id)throws Exception;
    //根据楼层名称查询楼层信息
    public Floor findByName(String name) throws Exception;
}
