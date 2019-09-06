package cn.itcast.service.admin.impl;


import cn.itcast.dao.admin.BookOrderDao;
import cn.itcast.pojo.admin.BookOrder;
import cn.itcast.pojo.admin.RoomType;
import cn.itcast.service.admin.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 预定的业务层接口
 */
@Service("bookOrderService")
public class BookOrderserviceImple implements BookOrderService{
    /*********************************************************************/
    @Autowired
    private BookOrderDao bookOrderDao;
    /********************************************************************/

    /**
     * 分页模糊查询
     * @param queryMap
     * @return
     * @throws Exception
     */
    public List<BookOrder> findList(Map<String, Object> queryMap) throws Exception {
        return bookOrderDao.findList(queryMap);
    }

    /**
     * 查询当前分页下总记录数
     * @param queryMap
     * @return
     * @throws Exception
     */
    public Integer getTotal(Map<String, Object> queryMap) throws Exception {
        return bookOrderDao.getTotal(queryMap);
    }

    /**
     * 根据主键插询
     * @param id
     * @return
     * @throws Exception
     */
    public BookOrder findById(Long id) throws Exception {
        return bookOrderDao.findById(id);
    }

    /**
     * 增加用户数据
     * @param bookOrder
     * @return
     * @throws Exception
     */
    public int add(BookOrder bookOrder) throws Exception {
        return bookOrderDao.add(bookOrder);
    }

    /**
     * 编辑预定信息
     * @param bookOrder
     * @return
     * @throws Exception
     */
    public int edit(BookOrder bookOrder) throws Exception {
        return bookOrderDao.update(bookOrder);
    }



    /**
     * 删除预定信息
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Long id) throws Exception {
        return bookOrderDao.delete(id);
    }
}
