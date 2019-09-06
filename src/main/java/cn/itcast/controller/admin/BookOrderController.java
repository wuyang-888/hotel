package cn.itcast.controller.admin;

import cn.itcast.pojo.admin.Account;
import cn.itcast.pojo.admin.BookOrder;
import cn.itcast.pojo.admin.Page;
import cn.itcast.pojo.admin.RoomType;
import cn.itcast.service.admin.AccountService;
import cn.itcast.service.admin.BookOrderService;
import cn.itcast.service.admin.RoomTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预定管理的控制器
 */
@RequestMapping("/admin/book_order")
@Controller
public class BookOrderController {
    /**********************************************************************/
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BookOrderService bookOrderService;
    /*******************************************************************/

    /**
     * 跳转到预定管理界面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv) throws Exception {
        //根据业务层查询方法查询房间信息和客户信息
        List<RoomType> roomTypeList = roomTypeService.findAll();
        List<Account> accountList = accountService.findAll();
        mv.addObject("roomTypeList", roomTypeList);
        mv.addObject("accountList", accountList);
        mv.setViewName("book_order/list");
        return mv;
    }

    /**
     * 分页查询预定信息
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(Page page,
                                    @RequestParam(name = "name", defaultValue = "") String name,
                                    @RequestParam(name = "accountId", defaultValue = "") Long accountId,
                                    @RequestParam(name = "roomTypeId", defaultValue = "") Long roomTypeId,
                                    @RequestParam(name = "idCard", defaultValue = "") String idCard,
                                    @RequestParam(name = "mobile", defaultValue = "") String mobile,
                                    @RequestParam(name = "status", required = false) Integer status) throws Exception {
        //查询条件集合
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //创建数据存储集合,用于保存将要返回的数据
        Map<String, Object> map = new HashMap<String, Object>();
        queryMap.put("name", name);
        queryMap.put("status", status);
        queryMap.put("accountId", accountId);
        queryMap.put("roomTypeId", roomTypeId);
        queryMap.put("idCard", idCard);
        queryMap.put("mobile", mobile);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        //调用业务层方法查询数据
        List<BookOrder> bookOrderList = bookOrderService.findList(queryMap);
        Integer total = bookOrderService.getTotal(queryMap);
        map.put("rows", bookOrderList);
        map.put("total", total);
        return map;

    }

    /**
     * 添加预定信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(BookOrder bookOrder) throws Exception {
        //创建数据存储集合，用来存储返回数据
        Map<String, String> map = new HashMap<String, String>();
        //判断是否填写预定信息
        if (bookOrder == null) {
            map.put("type", "error");
            map.put("msg", "请填写正确的预定信息!");
            return map;
        }
        //判断客户是否为空
        if (bookOrder.getAccountId() == null) {
            map.put("type", "error");
            map.put("msg", "客户不能为空!");
            return map;
        }
        //判断房型是否为空
        if (bookOrder.getRoomTypeId() == null) {
            map.put("type", "error");
            map.put("msg", "房型不能为空!");
            return map;
        }

        //判断是否填写联系人名称
        if (StringUtils.isEmpty(bookOrder.getName())) {
            map.put("type", "error");
            map.put("msg", "联系人名称不能为空!");
            return map;
        }
        //判断是否已填写手机号
        if (StringUtils.isEmpty(bookOrder.getMobile())) {
            map.put("type", "error");
            map.put("msg", "手机号不能为空!");
            return map;
        }
        //判断是否已填写身份证号
        if (StringUtils.isEmpty(bookOrder.getIdCard())) {
            map.put("type", "error");
            map.put("msg", "身份证号不能为空!");
            return map;
        }
        //判断是否已填写到达时间
        if (StringUtils.isEmpty(bookOrder.getArriveDate())) {
            map.put("type", "error");
            map.put("msg", "到达时间不能为空!");
            return map;
        }

        //判断是否已填写离店时间
        if (StringUtils.isEmpty(bookOrder.getLeaveDate())) {
            map.put("type", "error");
            map.put("msg", "离店时间不能为空!");
            return map;
        }

        bookOrder.setCreateTime(new Date());

        //判断是否添加成功
        if (bookOrderService.add(bookOrder) <= 0) {
            map.put("type", "error");
            map.put("msg", "添加失败，请联系管理员!");
            return map;
        }

        //添加成功后修改该房性的预定数
        RoomType roomType = roomTypeService.findById(bookOrder.getRoomTypeId());
        if (roomType != null) {
            //设置该房型预定数加一
            roomType.setBookNum(roomType.getBookNum() + 1);
            //设置该房型可用数减一
            roomType.setAvilableNum(roomType.getAvilableNum() - 1);
            roomTypeService.updateNum(roomType);
            //如果可用房间数为0，则该房型房间已满
            if (roomType.getAvilableNum() == 0) {
                roomType.setStatus(0);
                roomTypeService.edit(roomType);
            }
        }

        //添加成功
        map.put("type", "success");
        map.put("msg", "添加成功!");
        return map;
    }

    /**
     * 编辑预定信息
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(BookOrder bookOrder)throws Exception{
        //创建返回数据存储集合
        Map<String, String> map = new HashMap<String, String>();
        //判断预定信息是否为空
        if(bookOrder == null){
            map.put("type", "error");
            map.put("msg", "请填写预定信息!");
            return map;
        }
        //判断是否有客户
        if(bookOrder.getAccountId()==null){
            map.put("type", "error");
            map.put("msg", "客户不能为空!");
            return map;
        }
        //判断是否有房型
        if(bookOrder.getRoomTypeId()==null){
            map.put("type", "error");
            map.put("msg", "房型不能为空!");
            return map;
        }
        //判断该联系人名称是否为空
        if(StringUtils.isEmpty(bookOrder.getName())){
            map.put("type","error");
            map.put("msg", "预定联系人名称不能为空!");
            return map;
        }
        //判断该联系人手机号是否为空
        if(StringUtils.isEmpty(bookOrder.getMobile())){
            map.put("type","error");
            map.put("msg", "预定联系人手机号不能为空!");
            return map;
        }

        //判断该联系人身份证号是否为空
        if(StringUtils.isEmpty(bookOrder.getIdCard())){
            map.put("type","error");
            map.put("msg", "预定联系人身份证号不能为空!");
            return map;
        }

        //判断是否已填写到达时间
        if (StringUtils.isEmpty(bookOrder.getArriveDate())) {
            map.put("type", "error");
            map.put("msg", "到达时间不能为空!");
            return map;
        }

        //判断是否已填写离店时间
        if (StringUtils.isEmpty(bookOrder.getLeaveDate())) {
            map.put("type", "error");
            map.put("msg", "离店时间不能为空!");
            return map;
        }
        //判断所选数据是否已经存在
        BookOrder byId = bookOrderService.findById(bookOrder.getId());
        if (byId == null) {
            map.put("type", "error");
            map.put("msg", "请选择正确数据进行编辑!");
            return map;
        }



        //判断是否修该成功
        if(bookOrderService.edit(bookOrder) <= 0){
            map.put("type", "error");
            map.put("msg", "修改失败，请联系管理员!");
            return map;
        }

        //判断房型是否发生变化
        if(byId.getRoomTypeId().longValue() != bookOrder.getRoomTypeId().longValue()){
            //房型发生了变化
            //首先恢复原来房型的预定数及可用数
            RoomType oldRoomType = roomTypeService.findById(byId.getRoomTypeId());
            oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
            oldRoomType.setBookNum(oldRoomType.getBookNum() - 1);
            roomTypeService.updateNum(oldRoomType);
            if(oldRoomType.getStatus() == 0){
                //旧的房间原来是满房，现在不满房的话，恢复状态
                if(oldRoomType.getAvilableNum() > 0){
                    //设置成状态可用
                    oldRoomType.setStatus(1);
                    roomTypeService.edit(oldRoomType);
                }
            }
            //修改新的房型的可用数和预定数
            RoomType newRoomType = roomTypeService.findById(bookOrder.getRoomTypeId());
            newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
            newRoomType.setBookNum(newRoomType.getBookNum() + 1);
            roomTypeService.updateNum(newRoomType);
            if(newRoomType.getAvilableNum() <= 0){
                //没有可用房间数
                newRoomType.setStatus(0);//设置成满房
                roomTypeService.edit(newRoomType);
            }
        }
        //修改成功
        map.put("type", "success");
        map.put("msg", "修改成功!");
        return map;
    }

}
