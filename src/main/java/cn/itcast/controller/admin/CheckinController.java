package cn.itcast.controller.admin;

import cn.itcast.pojo.BookOrder;
import cn.itcast.pojo.RoomType;
import cn.itcast.pojo.admin.Checkin;
import cn.itcast.pojo.admin.Page;
import cn.itcast.pojo.admin.Room;
import cn.itcast.service.BookOrderService;
import cn.itcast.service.RoomTypeService;
import cn.itcast.service.admin.CheckinService;
import cn.itcast.service.admin.RoomService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 入住管理控制器
 */
@RequestMapping("/admin/checkin")
@Controller
public class CheckinController {

    /********************************************************************/
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookOrderService bookOrderService;
    @Autowired
    private CheckinService checkinService;
    /********************************************************************/

    /**
     * 跳转到入住管理界面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv) throws Exception {
        //调用业务层方法查询所有房间类型和房间
        List<RoomType> roomTypeList = roomTypeService.findAll();
        List<Room> roomList = roomService.findAll();

        //将查询到1的数据存储到model中
        mv.addObject("roomTypeList", roomTypeList);
        mv.addObject("roomList", roomList);
        mv.setViewName("checkin/list");
        return mv;
    }

    /**
     * 分页查询入住信息
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(Page page,
                                    @RequestParam(name = "name", defaultValue = "") String name,
                                    @RequestParam(name = "roomId", defaultValue = "") Long roomId,
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
        queryMap.put("roomId", roomId);
        queryMap.put("roomTypeId", roomTypeId);
        queryMap.put("idCard", idCard);
        queryMap.put("mobile", mobile);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        //调用业务层方法查询数据
        List<Checkin> checkinList = checkinService.findList(queryMap);
        Integer total = checkinService.getTotal(queryMap);
        map.put("rows", checkinList);
        map.put("total", total);
        return map;

    }

    /**
     * 添加入住信息
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(Checkin checkin,
                                  @RequestParam(name="bookOrderId",required=false) Long bookOrderId)throws Exception{
        //创建返回数据存储集合
        Map<String, String> map = new HashMap<String, String>();
        //判断是否有入住信息
        if (checkin == null) {
            map.put("type", "error");
            map.put("msg", "请填写入住信息");
            return map;
        }
        //判断是否选择房间
        if (checkin.getRoomId()== null) {
            map.put("type", "error");
            map.put("msg", "房间不能为空");
            return map;
        }
        //判断是否填写房型
        if (checkin.getRoomTypeId() == null) {
            map.put("type", "error");
            map.put("msg", "房型不能为空");
            return map;
        }
        //判断是否填写联系人姓名
        if(StringUtils.isEmpty(checkin.getName())){
            map.put("type", "error");
            map.put("msg", "入住联系人名称不能为空!");
            return map;
        }
        //判断是否填写联系人手机号
        if(StringUtils.isEmpty(checkin.getMobile())){
            map.put("type", "error");
            map.put("msg", "入住联系人手机号不能为空!");
            return map;
        }
        //判断是否填写联系人身份证号
        if(StringUtils.isEmpty(checkin.getIdCard())){
            map.put("type", "error");
            map.put("msg", "入住联系人身份证号不能为空!");
            return map;
        }
        //判断是否填写到达时间
        if(StringUtils.isEmpty(checkin.getArriveDate())){
            map.put("type", "error");
            map.put("msg", "到达时间不能为空!");
            return map;
        }
        //判断是否填写离店时间
        if(StringUtils.isEmpty(checkin.getLeaveDate())){
            map.put("type", "error");
            map.put("msg", "离店时间不能为空!");
            return map;
        }

        //入住订单创建时间
        checkin.setCreateTime(new Date());
        //判断添加是否成功
        if(checkinService.add(checkin)<=0){
            map.put("type", "success");
            map.put("msg", "添加失败，请联系管理员!");
            return map;
        }
        RoomType roomType = roomTypeService.findById(checkin.getRoomTypeId());
        //判断本次添加用户是否是已预定
        if(bookOrderId!=null){
            //从预定来的订单
            BookOrder bookOrder = bookOrderService.findById(bookOrderId);
            //预定状态改为已入住
            bookOrder.setStatus(1);
            bookOrderService.edit(bookOrder);

        }else{
            //本次用户非已预定
            //可住房间数减一
            roomType.setAvilableNum(roomType.getAvilableNum()-1);
        }
        //入住成功后修改房型信息
        if(roomType != null){
            roomType.setLivedNum(roomType.getLivedNum() + 1);//入住数加1
            roomType.setBookNum(roomType.getRoomNum()-1);
            roomTypeService.updateNum(roomType);
            //修改预定数
            if (checkin.getBookOrderId() != null) {
                roomType.setBookNum(roomType.getBookNum() - 1);
            }
            //如果可用的房间数为0，则设置该房型状态已满
            if(roomType.getAvilableNum() == 0){
                roomType.setStatus(0);
                roomTypeService.edit(roomType);
            }
        }
        //获取当前入住的房间
        Room room = roomService.findById(checkin.getRoomId());
        //判断该房间时候存在
        if(room!=null){
            //把房间状态改为已入住
            room.setStatus(1);
            roomService.edit(room);
        }
        map.put("type", "success");
        map.put("msg", "添加成功");
        return map;

    }

    /**
     * 退房操作
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> checkout(Long checkId) throws Exception {
        //创建返回数据存储集合
        Map<String, String> map = new HashMap<String, String>();
        //判断是否选择数据
        if (checkId == null) {
            map.put("type", "error");
            map.put("msg", "请选择数据");
            return map;
        }
        //判断是否存在这个数据
        Checkin checkin = checkinService.findById(checkId);
        if (checkin == null) {
            map.put("type", "error");
            map.put("msg", "不存在该数据,请重新选择");
            return map;
        }
        checkin.setStatus(1);
        if (checkinService.edit(checkin) < 0) {
            map.put("type", "error");
            map.put("msg", "退房失败，请联系管理员");
            return map;
        }
        //退房成功
        //更新房间信息
        Room room = roomService.findById(checkin.getRoomId());
        if (room != null) {
            room.setStatus(2);
            roomService.edit(room);
        }
        //修改房型信息
        RoomType roomType = roomTypeService.findById(checkin.getRoomTypeId());
        if (roomType != null) {
            //修改可入住房间数
            roomType.setAvilableNum(roomType.getAvilableNum() + 1);
            if (roomType.getAvilableNum() > roomType.getRoomNum()) {
                roomType.setAvilableNum(roomType.getRoomNum());
            }
            //修改已入住房间数
            roomType.setLivedNum(roomType.getLivedNum() - 1);
            if (roomType.getStatus() == 0) {
                roomType.setStatus(1);
            }

            roomTypeService.updateNum(roomType);
            roomTypeService.edit(roomType);
        }
        //判断是否来自预定
        if (checkin.getBookOrderId() != null) {
            BookOrder bookOrder = bookOrderService.findById(checkin.getBookOrderId());
            bookOrder.setStatus(2);
            bookOrderService.edit(bookOrder);

        }
        map.put("type", "success");
        map.put("msg", "退房成功");
        return map;
    }

    /**
     * 编辑入住信息
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Checkin checkin) throws Exception {
        //创建返回数据存储集合
        Map<String, String> map = new HashMap<String, String>();
        //判断是否有入住信息
        if (checkin == null) {
            map.put("type", "error");
            map.put("msg", "请填写入住信息");
            return map;
        }
        //判断是否选择房间
        if (checkin.getRoomId()== null) {
            map.put("type", "error");
            map.put("msg", "房间不能为空");
            return map;
        }
        //判断是否填写房型
        if (checkin.getRoomTypeId() == null) {
            map.put("type", "error");
            map.put("msg", "房型不能为空");
            return map;
        }
        //判断是否填写联系人姓名
        if(StringUtils.isEmpty(checkin.getName())){
            map.put("type", "error");
            map.put("msg", "入住联系人名称不能为空!");
            return map;
        }
        //判断是否填写联系人手机号
        if(StringUtils.isEmpty(checkin.getMobile())){
            map.put("type", "error");
            map.put("msg", "入住联系人手机号不能为空!");
            return map;
        }
        //判断是否填写联系人身份证号
        if(StringUtils.isEmpty(checkin.getIdCard())){
            map.put("type", "error");
            map.put("msg", "入住联系人身份证号不能为空!");
            return map;
        }
        //判断是否填写到达时间
        if(StringUtils.isEmpty(checkin.getArriveDate())){
            map.put("type", "error");
            map.put("msg", "到达时间不能为空!");
            return map;
        }
        //判断是否填写离店时间
        if(StringUtils.isEmpty(checkin.getLeaveDate())){
            map.put("type", "error");
            map.put("msg", "离店时间不能为空!");
            return map;
        }
        //判断所选数据是否存在
        Checkin existCheckin = checkinService.findById(checkin.getId());
        if(existCheckin == null){
            map.put("type", "error");
            map.put("msg", "请选择正确的入住信息进行编辑!");
            return map;
        }
        //判断是否编辑成功
        if(checkinService.edit(checkin) <= 0){
            map.put("type", "error");
            map.put("msg", "编辑失败，请联系管理员!");
            return map;
        }
        //编辑成功
        //判断房型和房间是否发生变化
        //修改前房型
        RoomType oldRoomType = roomTypeService.findById(existCheckin.getRoomTypeId());
        //修改后房型
        RoomType newRoomType = roomTypeService.findById(checkin.getRoomTypeId());
        //判断房型是否发生变化
        if(oldRoomType.getId().longValue()!=newRoomType.getId().longValue()){
            //说明房型发生了变化，原来的房型入住数恢复，新的房型入住数增加
            oldRoomType.setLivedNum(oldRoomType.getLivedNum() - 1);
            newRoomType.setLivedNum(newRoomType.getLivedNum() + 1);
            //房间的可用数量发生变化
                oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
                newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);

        }
        //更新发生相应变化的房型
        roomTypeService.updateNum(oldRoomType);
        roomTypeService.updateNum(newRoomType);
        //判断房间是否发生变化
        if(checkin.getRoomId().longValue()!=existCheckin.getRoomId().longValue()){
            //表示房间发生了变化
            //修改前的房间
            Room oldRoom = roomService.findById(existCheckin.getRoomId());
            //修改后的房间
            Room newRoom = roomService.findById(checkin.getRoomId());
            oldRoom.setStatus(0);//原来的房间可入住
            newRoom.setStatus(1);//现在的房间已入住
            roomService.edit(newRoom);
            roomService.edit(oldRoom);
        }
        //修改成功
        map.put("type", "success");
        map.put("msg", "编辑成功");
        return map;
    }

    /**
     * 根据房间类型获取房间
     */
    @RequestMapping(value="/load_room_list",method=RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>>load_room_list(Long roomTypeId) throws Exception {
        //创建返回数据集合
        List<Map<String,Object>>mapList=new ArrayList<Map<String,Object>>();
        //创建查询条件集合
        Map<String,Object> queryMap=new HashMap<String,Object>();
        //将查询条件添加到集合当中
        queryMap.put("roomTypeId", roomTypeId);
        queryMap.put("status", 0);
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 999);
        List<Room> roomList = roomService.findList(queryMap);
        //遍历集合
        for (Room room : roomList) {
            //将房间信息放入集合当中
            Map<String, Object> option = new HashMap<String, Object>();
            option.put("value", room.getId());
            option.put("text", room.getSn());
            mapList.add(option);
        }
        return mapList;
    }
}
