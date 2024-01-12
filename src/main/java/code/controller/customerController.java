package code.controller;

import code.dao.customerDao;
import code.pojo.pharmacist;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import code.pojo.customer;
import code.util.JackJsonUtils;
import code.util.ListObject;
import code.util.ResponseUtils;
import code.util.StatusCode;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * ClassName:customerController
 * Package:code.controller
 * Description:
 *
 * @Date:2024/1/4 22:09
 * @Author:2500594037@qq.com
 */

@Controller
@MapperScan("code.dao.customerDao")
@RequestMapping("/customer")
public class customerController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(customerController.class);

    @Resource
    private customerDao cu;

    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("控制台login" + "telephone=" + request.getParameter("telephone") + "password=" + request.getParameter("password") + "request=" + request);
        customer flag = cu.login(request.getParameter("telephone"), request.getParameter("password"));
        // 如果flag不为空，则说明账号密码正确
        if (flag != null && !flag.getId().equals("")) {
            logger.info("验证成功");
            List<customer> list = new ArrayList<customer>();
            customer inform = cu.selectedCustomer(request.getParameter("telephone"));
            list.add(inform);
            ListObject listObject = new ListObject();
            listObject.setItems(list);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("登录成功");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
        } else {
            ListObject listObject = new ListObject();
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("账号或密码错误");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
        }
    }

    @RequestMapping("selectedAll")  // 前端接口
    protected void selectedAllCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<customer> list = cu.selectedAll();
        ListObject listObject = new ListObject();
        if (list != null) {
            listObject.setItems(list);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("获取成功");
        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("暂无会员");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedByTelephone")
    public void selectedByTelephone(HttpServletRequest request, HttpServletResponse response) {
        logger.info("selectedByTelephone" + "request=" + request);
        customer inform = cu.selectedCustomer(request.getParameter("telephone"));
        List<customer> list = new ArrayList<customer>();
        list.add(inform);
        ListObject listObject = new ListObject();
        if (inform != null) {
            listObject.setItems(list);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("获取成功");
        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("无此会员");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/addTwo")
    public void addCustomerTwo(@RequestBody(required=false) customer t, HttpServletResponse response) {
        System.out.println();
        System.out.println();
        logger.info("注册用户信息：" + "customer=" + t);
        ListObject listObject = new ListObject();
        customer c = cu.selectedCustomer(t.getTelephone()+"");
        // 如果该手机号已经注册
        if (c != null && !c.getId().equals("")) {
            listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
            listObject.setMsg("该手机号已经注册，请直接登录");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if (cu.customerAdd(t)) {
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("注册成功");
        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("注册失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/delete")
    public void deleteCustomer(@RequestBody pharmacist cus, HttpServletResponse response) {
        logger.info("删除用户信息：" + "id=" + cus.getId());
        customer c = cu.selectedCustomerById(cus.getId());
        ListObject listObject = new ListObject();
        if (c == null || c.getId().equals("")) {
            listObject.setCode(StatusCode.CODE_ERROR_PARAMETER);
            listObject.setMsg("删除失败，无此会员");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if (cu.deleteCustomer(cus.getId())) {
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("删除成功");
        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("删除失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/upDate")
    public void upDataCustomer(@RequestBody customer c, HttpServletResponse response) {
        logger.info("修改用户信息：" + "customer=" + c);
        ListObject listObject = new ListObject();
        if(cu.upDataCustomer(c)){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("修改成功");
        }else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("修改失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    public String createId(){
        Random df = new Random();
        int i = df.nextInt(99999);
        return i+"";
    }
}
