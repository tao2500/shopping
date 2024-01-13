package code.controller;

import code.dao.drugsDao;
import code.dao.freightDao;
import code.pojo.drugs;
import code.pojo.freight;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:freightController
 * Package:code.controller
 * Description:
 *
 * @Date:2024/1/13 12:07
 * @Author:2500594037@qq.com
 */

@Controller
@MapperScan("code.dao.freightDao")
@RequestMapping("/freight")
public class freightController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(freightController.class);

    @Resource
    private freightDao cu;

    @RequestMapping(value = "/selectedAll")
    public void selectedAllFreight(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        System.out.println();
        logger.info("控制台selectedAll" + request.toString());
        List<freight> list = new ArrayList<freight>();
        list = cu.selectedAll();
        ListObject listObject = new ListObject();
        if (list.size() == 0) {
            listObject.setCode(StatusCode.CODE_NULL);
            listObject.setMsg("未查询到运费");
        } else {
            listObject.setItems(list);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("获取运费成功");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedFreight")
    public void selectedFreightByCity(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        System.out.println();
        logger.info("控制台selectedByName" + request.toString() + "request=" + request.getParameter("cities"));
        List<freight> list = new ArrayList<freight>();
        list.add(cu.selectedFreight(request.getParameter("cities")));
        ListObject listObject = new ListObject();
        if (list.size() == 0) {
            listObject.setCode(StatusCode.CODE_NULL);
            listObject.setMsg("未查询到运费");
        } else {
            listObject.setItems(list);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("获取运费成功");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/addTwo")
    public void addFreight(@RequestBody freight t, HttpServletResponse response) {
        ListObject listObject = new ListObject();
        if (t != null) {
            if (cu.addFreight(t)) {
                listObject.setCode(StatusCode.CODE_SUCCESS);
                listObject.setMsg("添加规则成功");
            } else {
                listObject.setCode(StatusCode.CODE_ERROR);
                listObject.setMsg("添加规则失败");
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/deleteFreight")
    public void deleteFreight(@RequestBody freight c, HttpServletResponse response) {
        ListObject listObject = new ListObject();
        if(cu.deleteFreight(c.getId())){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("已删除规则");
        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("删除运费规则失败！请联系管理员");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/upDataFreight")
    public void upDataFreight(@RequestBody freight c, HttpServletResponse response) {
        ListObject listObject = new ListObject();
        if(c.getId() == null || c.getId().equals("")){
            listObject.setCode(StatusCode.CODE_NULL);
            listObject.setMsg("id不能为空");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if(cu.upDataFreight(c)){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("修改成功");
        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("修改失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }
}
