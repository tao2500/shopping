package code.controller;

import code.dao.orderFromDao;
import code.pojo.orderFrom;
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
 * ClassName:orderFromController
 * Package:code.controller
 * Description:
 *
 * @Date:2024/1/5 15:25
 * @Author:2500594037@qq.com
 */

@Controller
@MapperScan("code.dao.orderFromDao")
@RequestMapping("/orderFrom")
public class orderFromController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(orderFromController.class);

    @Resource
    private orderFromDao cu;

    //143  150
    @RequestMapping("selectedAll")  // 前端接口
    protected void selectedAllOrderFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<orderFrom> list = new ArrayList<orderFrom>();
        list = cu.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedByStatus")
    public void selectedByStatus(String status, HttpServletRequest request, HttpServletResponse response) {
        List<orderFrom> list = cu.selectedByStatus(status);
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedByCustomerId")
    public void selectedByCustomerId(String id, HttpServletRequest request, HttpServletResponse response) {
        List<orderFrom> list = cu.selectedByCustomerId(id);
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/addOrderFrom")
    public void addOrderFromTwo(@RequestBody orderFrom t, HttpServletResponse response) {
        if (t != null) {
            if (cu.addOrderFrom(t)) {
                ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
            } else {
                ResponseUtils.renderJson(response, JackJsonUtils.toJson("id重复"));
            }
        }
    }

    @RequestMapping(value = "/deleteOrderFrom")
    public void deleteOrderFrom(@RequestBody orderFrom c, HttpServletResponse response) {
        if(cu.deleteOrderFrom(c.getIdOrderFrom())) {
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
        } else {
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除失败"));
        }
    }

    @RequestMapping(value = "/upDataOrderFrom")
    public void upDataOrderFrom(@RequestBody orderFrom c, HttpServletResponse response) {
        if(c.getIdOrderFrom() == null || c.getIdOrderFrom().equals("")){
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("订单编号不能为空"));
        }
        if(cu.upDataOrderFrom(c)){
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改成功"));
        }else{
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改失败"));
        }
    }
}
