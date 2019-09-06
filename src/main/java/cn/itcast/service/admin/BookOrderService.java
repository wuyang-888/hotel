package cn.itcast.service.admin;

import cn.itcast.pojo.admin.BookOrder;


import java.util.List;
import java.util.Map;

/**
 * 预定的业务层接口
 */
public interface BookOrderService {
    //分页模糊查询
    public List<BookOrder> findList(Map<String, Object> queryMap)throws Exception;
    //查询当前分页下总记录数
    public Integer getTotal(Map<String, Object> queryMap)throws Exception;
    //根据id查询
    public BookOrder findById(Long id)throws Exception;
    //添加预定信息
    public int add(BookOrder bookOrder)throws Exception;
    //修改预定信息
    public int edit(BookOrder bookOrder)throws Exception;

    //删除预定信息
    public int delete(Long id)throws Exception;
}
