package cn.itcast.controller.admin;

import cn.itcast.pojo.admin.Account;
import cn.itcast.pojo.admin.Page;
import cn.itcast.service.admin.AccountService;
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
 * 客户控制器
 */
@RequestMapping("/admin/account")
@Controller
public class AccountController {
    /*******************************************************************/
    @Autowired
    private AccountService accountService;
    /*******************************************************************/

    /**
     * 跳转到客户管理界面
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv)throws Exception{
        mv.setViewName("account/list");
        return mv;
    }

    /**
     * 分页查询客户信息
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(Page page,
                                    @RequestParam(name="name",defaultValue="") String name,
                                    @RequestParam(name="realName",defaultValue="") String realName,
                                    @RequestParam(name="idCard",defaultValue="") String idCard,
                                    @RequestParam(name="mobile",defaultValue="") String mobile,
                                    @RequestParam(name="status",required=false) Integer status) throws Exception {
        //查询条件集合
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //创建数据存储集合,用于保存将要返回的数据
        Map<String, Object> map = new HashMap<String, Object>();
        queryMap.put("name", name);
        queryMap.put("status", status);
        queryMap.put("realName", realName);
        queryMap.put("idCard", idCard);
        queryMap.put("mobile", mobile);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        //调用业务层方法查询数据
        List<Account> accountList = accountService.findList(queryMap);
        Integer total = accountService.getTotal(queryMap);
        map.put("rows",accountList);
        map.put("total",total);
        return map;

    }

    /**
     * 添加客户信息
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Account account) throws Exception {
        //创建数据存储集合，用来存储返回数据
        Map<String,String> map=new HashMap<String,String>();
        //判断是否填写楼层信息
        if (account == null) {
            map.put("type","error");
            map.put("msg", "请填写正确的客户信息!");
            return map;
        }

        //判断是否填写客户名称
        if (StringUtils.isEmpty(account.getName())) {
            map.put("type","error");
            map.put("msg", "客户名称不能为空!");
            return map;
        }
        //判断是否已填写密码
        if (StringUtils.isEmpty(account.getPassword())) {
            map.put("type","error");
            map.put("msg", "密码不能为空!");
            return map;
        }
        //判断该客户是否已经存在
        if(isExist(account.getName(),account.getId())){
            map.put("type","error");
            map.put("msg", "该用户名已存在!");
            return map;
        }

        //判断是否添加成功
        if (accountService.add(account)<=0) {
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
     * 编辑客户信息
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Account account)throws Exception{
        //创建返回数据存储集合
        Map<String, String> map = new HashMap<String, String>();
        //判断客户信息是否为空
        if(account == null){
            map.put("type", "error");
            map.put("msg", "请填写客户信息!");
            return map;
        }
        //判断是否有客户名称
        if(StringUtils.isEmpty(account.getName())){
            map.put("type", "error");
            map.put("msg", "客户名称不能为空!");
            return map;
        }
        //判断是否有客户密码
        if(StringUtils.isEmpty(account.getPassword())){
            map.put("type", "error");
            map.put("msg", "客户密码不能为空!");
            return map;
        }
        //判断该客户是否已经存在
        if(isExist(account.getName(),account.getId())){
            map.put("type","error");
            map.put("msg", "该用户名已存在!");
            return map;
        }
        //判断是否修该成功
        if(accountService.edit(account) <= 0){
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
     * 删除客户信息
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,String>delete(Long id)throws Exception{
        //创建返回数据存储集合
        Map<String, String> map = new HashMap<String, String>();
        //判断是否有删除的选项
        if (id == null) {
            map.put("type", "error");
            map.put("msg", "请勾选要删除的客户信息!");
            return map;
        }
        //判断是否删除成功
        try{
            if(accountService.delete(id) <= 0){
                map.put("type", "error");
                map.put("msg", "删除失败，请联系管理员!");
                return map;
            }

        }catch (Exception e){
            map.put("type", "error");
            map.put("msg", "该客户下存在订单信息，请先删除该客户下的所有订单信息!");
            return map;
        }
        //删除成功
        map.put("type", "success");
        map.put("msg", "删除成功!");
        return map;
    }


    /**
     * 判断用户名是否存在
     * @param name
     * @param id
     * @return
     */
    private boolean isExist(String name,Long id) throws Exception {
        Account findByName = accountService.findByName(name);
        if(findByName == null)return false;
        if(findByName.getId().longValue() == id.longValue())return false;
        return true;
    }


}
