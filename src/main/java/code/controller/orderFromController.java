package code.controller;

import cn.hutool.json.JSON;
import code.dao.drugsDao;
import code.dao.orderFromDao;
import code.dao.shoppingCartDao;
import code.pojo.customer;
import code.pojo.drugs;
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

    @Resource
    private drugsDao dd;

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
            // 药品数量对应减少（先获取该药品在修改该药品余量）
            List<drugs> drug = dd.selectedByBarCode(ss.getBarCode());
            drugs drugOne = drug.get(0);
            drugOne.setCount(drugOne.getCount() - Integer.parseInt(ss.getCount()));
            dd.upDataDrugs(drugOne);
            // 从购物车删除
            car.deleteShoppingCart(ss.getCustomerId(), ss.getBarCode());
        }
        // 总价保留两位小数
        sumMany = Math.round(sumMany * 100) / 100.0;

        // 构造orderFrom数据
        orderFrom orderFrom = new orderFrom();
        orderFrom.setIdOrderFrom("等待SQL生成");
        orderFrom.setCustomerId(s[0].getCustomerId());
        orderFrom.setDetail(detail);
        orderFrom.setShoppingAdd(s[0].getAddress().substring(0, s[0].getAddress().length() - 5));
        orderFrom.setDelivery(s[0].getAddress().substring(s[0].getAddress().length() - 4));

        orderFrom.setTotal(sumMany);
        orderFrom.setStatus("待支付");
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
        System.out.println();
        logger.info("idOrderFrom:" + c.getStatus());
        System.out.println();
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

            // 如果是取消订单，则要 加回药品余量
            if (c.getStatus().equals("已取消")) {
                String drugList = c.getDetail();
                addDCount(drugList);
            }

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
        // 订单状态从待支付 =》 待发货
        if(cu.playOk(request.getParameter("idOrderFrom"))){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("订单状态更新成功");
        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("订单状态更新失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    // 修改订单状态，取消时药品余量加回
    @RequestMapping(value = "/upDataOrderFromStatus")
    public void upDataOrderFromStatus(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        ListObject listObject = new ListObject();
        logger.info("修改状态订单编号："+request.getParameter("idOrderFrom"));
        System.out.println();
        if(request.getParameter("idOrderFrom") == null || request.getParameter("idOrderFrom").equals("")){
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("订单编号不能为空");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if(request.getParameter("status") == null || request.getParameter("status").equals("")){
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("订单状态不能为空");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if(cu.upDataOrderFromStatus(request.getParameter("idOrderFrom"), request.getParameter("status"))){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("订单状态更新成功");

            // 加回药品余量
            if (request.getParameter("status").equals("已取消")) {
                String drugList = request.getParameter("drugList");
                addDCount(drugList);
            }

        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("订单状态更新失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    // 取消订单，加回药品余量
    private void addDCount(String drugList) {
        String[] drugArr = drugList.split("；");
        for (int i = 0; i < drugArr.length; i++){
            String[] drugNum = drugArr[i].split("\\*");
            List<drugs> list = dd.selectedByName(drugNum[0].trim());
            if (list.size() == 0) {
                System.out.println();
                logger.info("修改药品库存失败，药品" + drugNum[0] +"不存在");
                System.out.println();
                continue;
            }
            drugs drug = list.get(0);
            drug.setCount(drug.getCount() + Integer.parseInt(drugNum[1].trim()));
            dd.upDataDrugs(drug);
        }
        logger.info("取消订单，药品" + drugArr +"余量加回");
    }
}
