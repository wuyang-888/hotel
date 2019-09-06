package cn.itcast.controller.admin;

import cn.itcast.service.admin.CheckinService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 营业统计控制器
 */
@RequestMapping("/admin/stats")
@Controller
public class StatsController {
    /******************************************************************/
    @Autowired
    private CheckinService checkinService;
    /********************************************************************/

    /**
     * 跳转到统计页面
     */
    @RequestMapping(value = "/stats",method = RequestMethod.GET)
    public ModelAndView stats(ModelAndView mv)throws Exception{
        mv.setViewName("stats/stats");
        return mv;
    }

    /**
     * 根据类型获取统计数据
     */
    @RequestMapping(value="/get_stats",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getStats(String type)throws Exception{
        //创建数据返回集合
        Map<String,Object> map=new HashMap<String,Object>();
        //判断是否选择统计类型
        if(StringUtils.isEmpty(type)){
            map.put("type","error");
            map.put("msg","请选择统计类型");
            return map;
        }

        //根据统计类型获取数据
        switch (type){
            case "month":{
                map.put("type","success");
                map.put("content",getStatsValue(checkinService.getStatsByMonth()));
                return map;
            }
            case "day":{
                map.put("type","success");
                map.put("content",getStatsValue(checkinService.getStatsByDay()));
                return map;
            }
            default:{
                map.put("type","error");
                map.put("msg","请选择正确的统计类型");
                return map;
            }
        }
    }

    /**
     * 将获取到数据的键和值分开
     */
    private Map<String,Object> getStatsValue(List<Map> statsValue){
        //创建数据返回集合
        Map<String,Object> map=new HashMap<String,Object>();
        //创建键保存集合
        List<String> keyList=new ArrayList<String>();
        //创建值保存集合
        List<Double> valueList=new ArrayList<Double>();
        for (Map map1 : statsValue) {
            keyList.add(map1.get("stats_date").toString());
            valueList.add(Double.valueOf(map1.get("money").toString()));
        }
        map.put("keyList",keyList);
        map.put("valueList",valueList);
        return map;
    }
}
