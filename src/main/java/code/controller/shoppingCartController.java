package code.controller;

import code.dao.shoppingCartDao;
import code.pojo.shoppingCart;
import code.util.JackJsonUtils;
import code.util.ListObject;
import code.util.ResponseUtils;
import code.util.StatusCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:shoppingCartController
 * Package:code.controller
 * Description:
 *
 * @Date:2024/1/5 15:33
 * @Author:2500594037@qq.com
 */

@Controller
@MapperScan("code.dao.shoppingCartDao")
@RequestMapping("/shoppingCart")
public class shoppingCartController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(shoppingCartController.class);

    @Resource
    private shoppingCartDao cu;

    //143  150
    @RequestMapping("selectedAll")  // 前端接口
    protected void selectedAllshoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<shoppingCart> list = new ArrayList<shoppingCart>();
        list = cu.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedByCustomerId")
    public void selectedByCustomerId(String id, HttpServletRequest request, HttpServletResponse response) {
        List<shoppingCart> list = cu.selectedByCustomerId(id);
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/addShoppingCart")
    public void addShoppingCart(@RequestBody shoppingCart t, HttpServletResponse response) {
        if (t != null) {
            if (cu.addShoppingCart(t)) {
                ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
            } else {
                ResponseUtils.renderJson(response, JackJsonUtils.toJson("id重复"));
            }
        }
    }

    @RequestMapping(value = "/deleteShoppingCart")
    public void deleteShoppingCart(@RequestBody shoppingCart c, HttpServletResponse response) {
        if(cu.deleteShoppingCart(c.getId())) {
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
        } else {
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除失败"));
        }
    }

    @RequestMapping(value = "/upDataShoppingCart")
    public void upDataShoppingCart(@RequestBody shoppingCart c, HttpServletResponse response) {
        if(c.getId() == null || c.getId().equals("")){
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("购物车id不能为空"));
        }
        if(cu.upDataShoppingCart(c)){
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改成功"));
        }else{
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改失败"));
        }
    }
}
