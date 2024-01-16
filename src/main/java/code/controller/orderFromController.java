package code.controller;

import cn.hutool.json.JSON;
import code.dao.orderFromDao;
import code.dao.shoppingCartDao;
import code.pojo.customer;
import code.pojo.orderFrom;
import code.pojo.showShoppingCart;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.core.io.buffer.DataBufferUtils.readInputStream;

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
    public void selectedByStatus(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        System.out.println();
        logger.info("status:" + request.getParameter("status"));
        List<orderFrom> list = cu.selectedByStatus(request.getParameter("status"));
        ListObject listObject = new ListObject();
        if (list != null) {
            listObject.setItems(list);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("获取成功");
        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("暂无该类型订单");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedByCustomerId")
    public void selectedByCustomerId(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        logger.info("addOrderFromTwo:" + request.getParameter("customerId"));
        System.out.println();
        List<orderFrom> list = cu.selectedByCustomerId(request.getParameter("customerId"));
        ListObject listObject = new ListObject();
        if (list != null){
            listObject.setItems(list);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("获取成功");
        } else {
            listObject.setCode(StatusCode.CODE_NULL);
            listObject.setMsg("暂无订单");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @Resource
    private shoppingCartDao car;

    @RequestMapping(value = "/addOrderFrom")
    public void addOrderFromTwo(@RequestBody showShoppingCart[] s, HttpServletResponse response) throws IOException {
        System.out.println();
        System.out.println();
        logger.info("addOrderFromTwo:" + s[0]);
        System.out.println();
        logger.info("addOrderFromTwo:" + s[0].getAddress());
        // 计算结算药品及订单总价
        String detail = "";
        double sumMany = 0;
        for (showShoppingCart ss : s) {
            if (!detail.equals("")) detail += "； ";
            detail += ss.getName() +" * " + ss.getCount();
            sumMany += Double.parseDouble(ss.getPrice()) * Integer.parseInt(ss.getCount());
            // 从购物车删除
            car.deleteShoppingCart(ss.getCustomerId(), ss.getBarCode());
        }

        // 构造orderFrom数据
        orderFrom orderFrom = new orderFrom();
        orderFrom.setIdOrderFrom("等待SQL生成");
        orderFrom.setCustomerId(s[0].getCustomerId());
        orderFrom.setDetail(detail);
        orderFrom.setShoppingAdd(s[0].getAddress().substring(0, s[0].getAddress().length() - 5));
        orderFrom.setDelivery(s[0].getAddress().substring(s[0].getAddress().length() - 4));

        orderFrom.setTotal(sumMany);
        orderFrom.setStatus("待付款");
        orderFrom.setJoinTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        ListObject listObject = new ListObject();
        if (s.length > 0){
            if (cu.addOrderFrom(orderFrom)) {
                listObject.setCode(StatusCode.CODE_SUCCESS);
                listObject.setMsg("已创建订单");
                // 根据订单创建时间及customerId查询生成的订单号，返回给前端
                List<orderFrom> list = cu.getOrderIdByTimeAndCId(orderFrom.getJoinTime(), orderFrom.getCustomerId());
                listObject.setItems(list);
            } else {
                listObject.setCode(StatusCode.CODE_ERROR);
                listObject.setMsg("创建订单失败");
            }

        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("药品数据为空");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/deleteOrderFrom")
    public void deleteOrderFrom(@RequestBody orderFrom o, HttpServletResponse response) {
        System.out.println();
        System.out.println();
        logger.info("idOrderFrom:" + o.getIdOrderFrom());
        ListObject listObject = new ListObject();
        if (o.getIdOrderFrom() == null || o.getIdOrderFrom().equals("")) {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("订单编号不能为空");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if (cu.deleteOrderFrom(o.getIdOrderFrom())) {
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("退单成功");
        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("退单失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/upDataOrderFrom")
    public void upDataOrderFrom(@RequestBody orderFrom c, HttpServletResponse response) {
        ListObject listObject = new ListObject();
        if(c.getIdOrderFrom() == null || c.getIdOrderFrom().equals("")){
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("订单编号不能为空");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if(cu.upDataOrderFrom(c)){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("订单状态更新成功");
        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("订单状态更新失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/playOk")
    public void playOk(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        ListObject listObject = new ListObject();
        logger.info("支付成功订单编号："+request.getParameter("idOrderFrom"));
        System.out.println();
        if(request.getParameter("idOrderFrom") == null || request.getParameter("idOrderFrom").equals("")){
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("订单编号不能为空");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if(cu.playOk(request.getParameter("idOrderFrom"))){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("订单状态更新成功");
        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("订单状态更新失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }
}
