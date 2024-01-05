package code.controller;

import code.dao.pharmacistDao;
import code.pojo.pharmacist;
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
 * ClassName:pharmacistController
 * Package:code.controller
 * Description:
 *
 * @Date:2024/1/5 12:09
 * @Author:2500594037@qq.com
 */

@Controller
@MapperScan("code.dao.pharmacistDao")
@RequestMapping("/pharmacist")
public class pharmacistController {
    // 得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
    private static final Log logger = LogFactory.getLog(pharmacistController.class);

    @Resource
    private pharmacistDao cu;

    @RequestMapping("selectedAll")
    protected void selectedAllPharmacist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<pharmacist> list = new ArrayList<pharmacist>();
        list = cu.selectedAll();
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/selectedById")
    public void selectedById(String id, HttpServletRequest request, HttpServletResponse response) {
        pharmacist inform = cu.selectedPharmacistById(id);
        List<pharmacist> list = new ArrayList<pharmacist>();
        list.add(inform);
        ListObject listObject = new ListObject();
        listObject.setItems(list);
        listObject.setCode(StatusCode.CODE_SUCCESS);
        listObject.setMsg("获取成功");
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/addPharmacist")
    public void addPharmacist(String[] account,HttpServletRequest request, HttpServletResponse response) throws IOException {
        pharmacist c = new pharmacist();
        c.setId(account[0]);
        c.setName(account[1]);
        c.setPassword(account[2]);
        c.setDuties(account[3]);
        if (cu.addPharmacist(c)){
            response.sendError(500,"id重复,添加失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
    }

    @RequestMapping(value = "/addTwo")
    public void addPharmacistTwo(@RequestBody pharmacist t, HttpServletResponse response) throws IOException {
        if (t != null) {
            if (cu.addPharmacist(t)) {
                response.sendError(500,"id重复,添加失败");
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("添加成功"));
    }

    @RequestMapping(value = "/delete")
    public void deletePharmacist(@RequestBody pharmacist c, HttpServletResponse response) {
        cu.deletePharmacist(c.getId());
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("删除成功"));
    }

    @RequestMapping(value = "/upDate")
    public void upDataPharmacist(@RequestBody pharmacist c, HttpServletResponse response) {
        cu.upDataPharmacist(c);
        ResponseUtils.renderJson(response, JackJsonUtils.toJson("修改成功"));
    }
}
