package code.controller;

import code.dao.customerDao;
import code.dao.drugsDao;
import code.pojo.customer;
import code.pojo.drugs;
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
import java.util.Random;

/**
 * ClassName:drugsController
 * Package:code.controller
 * Description:
 *
 * @Date:2024/1/5 15:14
 * @Author:2500594037@qq.com
 */

@Controller
@MapperScan("code.dao.drugsDao")
@RequestMapping("/drugs")
public class drugsController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(drugsController.class);

    @Resource
    private drugsDao cu;

    //143  150
    @RequestMapping("selectedAll")  // 前端接口
    protected void selectedAllDrugs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<drugs> list = new ArrayList<drugs>();
        list = cu.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedByName")
    public void selectedByName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        System.out.println();
        logger.info("控制台selectedByName" + request.toString() + "request=" + request.getParameter("name"));
        List<drugs> list = cu.selectedByName(request.getParameter("name"));
        ListObject listObject = new ListObject();
        if (list.size() == 0) {
            listObject.setCode(StatusCode.CODE_NULL);
            listObject.setMsg("未找到相关药品");
        } else {
            listObject.setItems(list);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("获取成功");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedByType")
    public void selectedByType(String type, HttpServletRequest request, HttpServletResponse response) {
        List<drugs> list = cu.selectedByType(type);
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedByEffect")
    public void selectedByEffect(String effect, HttpServletRequest request, HttpServletResponse response) {
        List<drugs> list = cu.selectedByEffect(effect);
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/addTwo")
    public void addDrugsTwo(@RequestBody drugs t, HttpServletResponse response) {
        if (t != null) {
            if (cu.addDrugs(t)) {
                ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
            } else {
                ResponseUtils.renderJson(response, JackJsonUtils.toJson("id重复"));
            }
        }
    }

    @RequestMapping(value = "/deleteDrugs")
    public void deleteDrugs(@RequestBody drugs c, HttpServletResponse response) {
        cu.deleteDrugs(c.getBarCode());
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
    }

    @RequestMapping(value = "/upDataDrugs")
    public void upDataDrugs(@RequestBody drugs c, HttpServletResponse response) {
        if(c.getBarCode() == null || c.getBarCode().equals("")){
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("条形码不能为空"));
        }
        if(cu.upDataDrugs(c)){
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改成功"));
        }else{
            ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改失败"));
        }
    }
}
