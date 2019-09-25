package cn.itcast.controller.admin;

import cn.itcast.pojo.RoomType;
import cn.itcast.pojo.admin.Page;
import cn.itcast.service.RoomTypeService;
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
 * 房间类型的控制类
 */
@RequestMapping("/admin/room_type")
@Controller
public class RoomTypeController {
    /******************************************************************/
    @Autowired
    private RoomTypeService roomTypeService;
    /*******************************************************************/

    /**
     * 跳转到房间类型管理页面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv) throws Exception {
        //设置跳转路径
        mv.setViewName("room_type/list");
        return mv;
    }

    /**
     * 分页查询查询房间类型信息
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(Page page,
                                    @RequestParam(name = "name", defaultValue = "") String name,
                                    @RequestParam(name = "status", required = false) Integer status
    ) throws Exception {
        //返回数据保存集合
        Map<String, Object> map = new HashMap<String, Object>();
        //查询条件集合
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //封装查询条件
        queryMap.put("name", name);
        queryMap.put("status", status);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        //调用业务层方法查询相应信息
        List<RoomType> roomTypes = roomTypeService.findLsit(queryMap);
        int total = roomTypeService.getTotal(queryMap);
        //将查询到的信息存放到集合当中
        map.put("rows", roomTypes);
        map.put("total", total);
        return map;
    }

    /**
     * 增加房间类型信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(RoomType roomType) throws Exception {
        //返回数据保存集合
        Map<String, Object> map = new HashMap<String, Object>();
        //判断该房间类型是否存在
        if (roomType == null) {
            map.put("type", "error");
            map.put("msg", "请填写正确的房间类型信息!");
            return map;
        }
        //判断该房间类型名称是否为空
        if (StringUtils.isEmpty(roomType.getName())) {
            map.put("type", "error");
            map.put("msg", "房间类型名称不能为空!");
            return map;
        }
        //判断该房间类型是否已存在
        if ((roomTypeService.findByName(roomType.getName())) != null) {
            map.put("type", "error");
            map.put("msg", "该类型房间信息已添加!");
            return map;
        }
        //设置该类型房间预定初始值为0
        roomType.setAvilableNum(roomType.getRoomNum());//默认房间数等于可用房间数
        roomType.setBookNum(0);//设置预定数0
        roomType.setLivedNum(0);//设置已入住数0

        //判断该房间类型是否添加成功
        if (roomType.getRoomNum()<1) {
            map.put("type", "error");
            map.put("msg", "房间数目不正确");
            return map;
        }
        if (roomType.getBedNum()<1) {
            map.put("type", "error");
            map.put("msg", "床位数目不正确");
            return map;
        }

        if (roomType.getLiveNum()<1) {
            map.put("type", "error");
            map.put("msg", "可住e数目不正确");
            return map;
        }

        //判断该房间类型是否添加成功
        if (roomTypeService.add(roomType) <= 0) {
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
     * 编辑房间类型信息
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> edit(RoomType roomType) throws Exception {
        //返回数据保存集合
        Map<String, Object> map = new HashMap<String, Object>();
        //判断该房间类型是否存在
        if (roomType == null) {
            map.put("type", "error");
            map.put("msg", "请填写正确的房间类型信息!");
            return map;
        }

        //判断该房间类型名称是否为空
        if (StringUtils.isEmpty(roomType.getName())) {
            map.put("type", "error");
            map.put("msg", "房间类型名称不能为空!");
            return map;
        }

        //判断是否存在该数据
        RoomType roomType1 = roomTypeService.findById(roomType.getId());
        if (roomType1 == null) {
            map.put("type", "error");
            map.put("msg", "未找到该数据!");
            return map;
        }
        //若修改了总房间数，则响应改变可用房间数
        int offset=roomType.getRoomNum()-roomType1.getRoomNum();
        roomType.setAvilableNum(roomType1.getAvilableNum()+offset);
        //判断可用房间数是否合理
        if(roomType.getAvilableNum()<=0){
            roomType.setAvilableNum(0);//无可用房间
            roomType.setStatus(0);//放行已满
            if(roomType.getAvilableNum()+roomType1.getLivedNum()+roomType1.getBookNum()>roomType.getRoomNum()){
                map.put("type", "error");
                map.put("msg", "房间数设置不合理!");
                return map;
            }
        }
        //判断该房间类型是否修改成功
        if (roomTypeService.edit(roomType) <= 0) {
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
     * 删除房间类型信息
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>delete(Long id)throws Exception{
        //返回数据保存集合
        Map<String, Object> map = new HashMap<String, Object>();
        //判断是否删除成功
        try{
            if(roomTypeService.delete(id) <= 0){
                map.put("type", "error");
                map.put("msg", "删除失败，请联系管理员!");
                return map;
            }

        }catch (Exception e){
            map.put("type", "error");
            map.put("msg", "该房间类型下存在房间信息，请先删除该楼层下的所有房间信息!");
            return map;
        }
        //删除成功
        map.put("type", "success");
        map.put("msg", "删除成功!");
        return map;
    }

}
