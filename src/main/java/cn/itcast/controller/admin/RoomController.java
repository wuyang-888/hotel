package cn.itcast.controller.admin;

import cn.itcast.pojo.admin.Floor;
import cn.itcast.pojo.admin.Page;
import cn.itcast.pojo.admin.Room;
import cn.itcast.pojo.admin.RoomType;
import cn.itcast.service.admin.FloorService;
import cn.itcast.service.admin.RoomService;
import cn.itcast.service.admin.RoomTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 房间的控制类
 */
@RequestMapping("/admin/room")
@Controller
public class RoomController {
    /**********************************************************/
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private FloorService floorService;
    /*************************************************************/

    /**
     * 跳转到房间管理界面
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv)throws Exception{
        //调用业务层查询方法
        List<RoomType> roomTypeList = roomTypeService.findAll();
        List<Floor> floorList = floorService.findAll();
        //将楼层和房间类型存入model中
        mv.addObject("roomTypeList",roomTypeList);
        mv.addObject("floorList",floorList);
        mv.setViewName("/room/list");
        return mv;
    }

    /**
     * 分页查询房间信息
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> list(Page page,
                                   @RequestParam(name="sn",defaultValue="") String sn,
                                   @RequestParam(name="status",required=false) Integer status,
                                   @RequestParam(name="roomTypeId",required=false) Long roomTypeId,
                                   @RequestParam(name="floorId",required=false) Long floorId
    )throws Exception{
        //返回数据保存集合
        Map<String, Object> map = new HashMap<String, Object>();
        //查询条件集合
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //封装查询条件
        queryMap.put("sn", sn);
        queryMap.put("status", status);
        queryMap.put("roomTypeId", roomTypeId);
        queryMap.put("floorId", floorId);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        //调用业务层方法查询
        List<Room> rooms = roomService.findList(queryMap);
        int total = roomService.getTotal(queryMap);
        //将查询到的信息保存到返回集合
        map.put("rows",rooms);
        map.put("tatal",total);
        return map;
    }

    /**
     * 添加房间信息
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Room room)throws Exception{
        //返回数据保存集合
        Map<String,String> map = new HashMap<String,String>();
        //判断该房间是否存在
        if(room == null){
            map.put("type", "error");
            map.put("msg", "请填写正确的房间信息!");
            return map;
        }
        //判断房间编号是否填写
        if(StringUtils.isEmpty(room.getSn())){
            map.put("type", "error");
            map.put("msg", "房间编号不能为空!");
            return map;
        }
        //判断房间类型是否存在
        if(room.getRoomTypeId() == null){
            map.put("type", "error");
            map.put("msg", "请选择房间类型!");
            return map;
        }
        //判断所属楼层是否存在
        if(room.getFloorId() == null){
            map.put("type", "error");
            map.put("msg", "请选择房间所属楼层!");
            return map;
        }
        //判断该房间编号是否已经存在
        if(isExist(room.getSn(),room.getId())){
            map.put("type", "error");
            map.put("msg", "该房间编号已经存在!");
            return map;
        }
        //判断是否添加成功
        if(roomService.add(room) <= 0){
            map.put("type", "error");
            map.put("msg", "添加失败，请联系管理员!");
            return map;
        }
        //添加成功
        map.put("type", "success");
        map.put("msg", "添加成功!");
        return map;
    }

    /**
     * 修改房间信息
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Room room)throws Exception{
        //返回数据保存集合
        Map<String,String> map = new HashMap<String,String>();
        //判断该房间是否存在
        if(room == null){
            map.put("type", "error");
            map.put("msg", "请填写正确的房间信息!");
            return map;
        }
        //判断该房间编号是否为空
        if(StringUtils.isEmpty(room.getSn())){
            map.put("type", "error");
            map.put("msg", "房间编号不能为空!");
            return map;
        }
        //判断该房间类型是否存在
        if(room.getRoomTypeId() == null){
            map.put("type", "error");
            map.put("msg", "请选择房间类型!");
            return map;
        }
        //判断该楼层是否存在
        if(room.getFloorId() == null){
            map.put("type", "error");
            map.put("msg", "请选择房间所属楼层!");
            return map;
        }
        //判断该房间编号是否存在
        if(isExist(room.getSn(), room.getId())){
            map.put("type", "error");
            map.put("msg", "该房间编号已经存在!");
            return map;
        }
        //判断修改是否成功
        if(roomService.edit(room) <= 0){
            map.put("type", "error");
            map.put("msg", "修改失败，请联系管理员!");
            return map;
        }
        //修改成功
        map.put("type", "success");
        map.put("msg", "修改成功!");
        return map;
    }

    /**
     * 删除房间信息
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(Long id)throws Exception{
        //返回数据保存集合
        Map<String,String> map = new HashMap<String,String>();
        //判断该房间是否存在
        if(id == null){
            map.put("type", "error");
            map.put("msg", "请选择要删除的信息!");
            return map;
        }
        //判断是否删除成功
        try {
            if(roomService.delete(id) <= 0){
                map.put("type", "error");
                map.put("msg", "删除失败，请联系管理员!");
                return map;
            }
        } catch (Exception e) {
            // TODO: handle exception
            map.put("type", "error");
            map.put("msg", "该房间下存在订单信息，请先删除该房间下的所有订单信息!");
            return map;
        }
        //删除成功
        map.put("type", "success");
        map.put("msg", "删除成功!");
        return map;
    }

    /**
     * 判断房间编号是否存在
     * @param sn
     * @param id
     * @return
     */
    private boolean isExist(String sn,Long id)throws Exception{
        Room findBySn = roomService.findBySn(sn);
        if(findBySn == null)return false;
        if(findBySn.getId().longValue() == id.longValue())return false;
        return true;
    }
}
