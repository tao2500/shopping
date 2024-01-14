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
        ListObject listObject = new ListObject();
        if (t != null) {
            if (cu.addShoppingCart(t)) {
                listObject.setCode(StatusCode.CODE_SUCCESS);
                listObject.setMsg("加入购物车成功");
            } else {
                listObject.setCode(StatusCode.CODE_ERROR);
                listObject.setMsg("加入购物车失败");
            }
        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("请传入正确的参数");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/deleteShoppingCart")
    public void deleteShoppingCart(@RequestBody shoppingCart c, HttpServletResponse response) {
        ListObject listObject = new ListObject();
        if(cu.deleteShoppingCart(c.getCustomerId(), c.getBarCode())) {
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("已从购物车移除");
        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("移除失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/changeCount")
    public void upDataShoppingCart(@RequestBody shoppingCart c, HttpServletResponse response) {
        ListObject listObject = new ListObject();
        if(c.getId() == null || c.getId().equals("")){
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("购物车id不能为空");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if(cu.changeCount(c.getCustomerId(), c.getBarCode(), c.getCount())){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("商品数量修改成功");
        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("商品数量修改失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }
}
