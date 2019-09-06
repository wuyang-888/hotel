package cn.itcast.controller.admin;

import cn.itcast.pojo.admin.Floor;
import cn.itcast.pojo.admin.Page;
import cn.itcast.service.admin.FloorService;
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
 * 楼层管理的后台控制器
 */
@RequestMapping("/admin/floor")
@Controller
public class FloorController {
    /*************************************************************/
    @Autowired
    private FloorService floorService;
    /****************************************************************/

    /**
     * 控制跳转到楼层管理页面
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv) throws Exception {
        //跳转到楼层管理界面
        mv.setViewName("floor/list");
        return mv;
    }

    /**
     * 分页查询楼层信息
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(Page page,
                                    @RequestParam(name = "name", defaultValue = "") String name) throws Exception {
        //查询条件集合
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //创建数据存储集合,用于保存将要返回的数据
        Map<String, Object> map = new HashMap<String, Object>();
        queryMap.put("name", name);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        //调用业务层方法查询数据
        List<Floor> floors = floorService.findList(queryMap);
        Integer total = floorService.getTotal(queryMap);
        map.put("rows",floors);
        map.put("total",total);
        return map;

    }

    /**
     * 添加楼层信息
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String>add(Floor floor)throws Exception{
        //创建数据存储集合，用来存储返回数据
        Map<String,String> map=new HashMap<String,String>();
        //判断是否填写楼层信息
        if (floor == null) {
            map.put("type","error");
            map.put("msg", "请填写正确的楼层信息!");
            return map;
        }

        //判断是否填写楼层名称
        if (StringUtils.isEmpty(floor.getName())) {
            map.put("type","error");
            map.put("msg", "楼层名称不能为空!");
            return map;
        }
        //判断该楼层是否已存在
        if((floorService.findByName(floor.getName()))!=null){
            map.put("type","error");
            map.put("msg", "该楼层信息已经存储!");
            return map;
        }
        //判断是否添加成功
        if (floorService.add(floor)<=0) {
            map.put("type","error");
            map.put("msg", "添加失败，请联系管理员!");
            return map;
        }
        //添加成功
        map.put("type","success");
        map.put("msg", "添加成功!");
        return map;
    }

    /**
     * 编辑楼层信息
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Floor floor)throws Exception{
        //创建返回数据存储集合
        Map<String, String> map = new HashMap<String, String>();
        //判断是否楼层信息是否为空
        if(floor == null){
            map.put("type", "error");
            map.put("msg", "请填写正确的楼层信息!");
            return map;
        }
        //判断是否有楼层信息
        if(StringUtils.isEmpty(floor.getName())){
            map.put("type", "error");
            map.put("msg", "楼层名称不能为空!");
            return map;
        }
        //判断是否修该成功
        if(floorService.edit(floor) <= 0){
            map.put("type", "error");
            map.put("msg", "修改失败，请联系管理员!");
            return map;
        }
        //
        map.put("type", "success");
        map.put("msg", "修改成功!");
        return map;
    }

    /**
     * 删除楼层信息
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String>delete(Long id)throws Exception{
        //创建返回数据存储集合
        Map<String, String> map = new HashMap<String, String>();
        //判断是否有删除的选项
        if (id == null) {
            map.put("type", "error");
            map.put("msg", "请勾选要删除的楼层信息!");
            return map;
        }
        //判断是否删除成功
        try{
            if(floorService.delete(id) <= 0){
                map.put("type", "error");
                map.put("msg", "删除失败，请联系管理员!");
                return map;
            }

        }catch (Exception e){
            map.put("type", "error");
            map.put("msg", "该楼层下存在房间信息，请先删除该楼层下的所有房间信息!");
            return map;
        }
        //删除成功
        map.put("type", "success");
        map.put("msg", "删除成功!");
        return map;
    }
}

