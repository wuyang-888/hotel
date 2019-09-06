package cn.itcast.service;
/**
 * 预定订单service
 */

import cn.itcast.pojo.BookOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookOrderService {
    public int add(BookOrder bookOrder);

    public int edit(BookOrder bookOrder);

    public int delete(Long id);

    public List<BookOrder> findList(Map<String, Object> queryMap);

    public Integer getTotal(Map<String, Object> queryMap);

    public BookOrder find(Long id);
}
