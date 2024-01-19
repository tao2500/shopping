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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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

    @RequestMapping(value = "/selectedByBarCode")
    public void selectedByBarCode(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        System.out.println();
        logger.info("控制台selectedByBarCode" + request.getParameter("barCode"));
        List<drugs> list = cu.selectedByBarCode(request.getParameter("barCode"));
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
    public void selectedByType(HttpServletRequest request, HttpServletResponse response) {
        System.out.println();
        System.out.println();
        logger.info("控制台selectedByType" + request.toString() + "request=" + request.getParameter("name"));
        List<drugs> list = cu.selectedByType(request.getParameter("type"));
        ListObject listObject = new ListObject();
        if (list.size() == 0) {
            listObject.setCode(StatusCode.CODE_NULL);
            listObject.setMsg("没有相关药品");
        } else {
            listObject.setItems(list);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("获取" + request.getParameter("type") + "类药品成功");
        }
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

    @RequestMapping(value = "/selectedAllType")
    public void selectedAllType(HttpServletRequest request, HttpServletResponse response) {
        List<drugs> list = cu.selectedAll();
        ListObject listObject = new ListObject();
        if(list.size() == 0){
            listObject.setCode(StatusCode.CODE_NULL);
            listObject.setMsg("商城暂无药品");
        }else{
            List<String> list1 = new ArrayList<String>();
            for (code.pojo.drugs drugs : list) {
                String[] arr = drugs.getType().split(",");
                for(String s : arr){
                    if (!list1.contains(s)) {
                        list1.add(s);
                    }
                }
            }
            listObject.setItems(list1);
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("所有药品类型已返回");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/addTwo")
    public void addDrugsTwo(@RequestBody drugs t, HttpServletResponse response) {
        ListObject listObject = new ListObject();
        if (t != null) {
            if (cu.addDrugs(t)) {
                listObject.setCode(StatusCode.CODE_SUCCESS);
                listObject.setMsg("添加成功");
            } else {
                listObject.setCode(StatusCode.CODE_ERROR);
                listObject.setMsg("添加失败");
            }
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/deleteDrugs")
    public void deleteDrugs(@RequestBody drugs c, HttpServletResponse response) {
        ListObject listObject = new ListObject();
        if(cu.deleteDrugs(c.getBarCode())){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("已下架");
        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("下架失败！请联系管理员");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/upDataDrugs")
    public void upDataDrugs(@RequestBody drugs c, HttpServletResponse response) {
        ListObject listObject = new ListObject();
        if(c.getBarCode() == null || c.getBarCode().equals("")){
            listObject.setCode(StatusCode.CODE_NULL);
            listObject.setMsg("条形码不能为空");
            ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
            return;
        }
        if(cu.upDataDrugs(c)){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("修改成功");
        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("修改失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/editDrugsImg")
    public void editDrugsImg(@RequestParam(value = "file", required = false) MultipartFile file,
                             @RequestParam(value = "barCode", required = false) String barCode,
                             HttpServletResponse response)
            throws IOException, SQLException {
        System.out.println();
        // MultipartFile转换为Blob对象
        Blob blob = new SerialBlob(file.getBytes());
        logger.info("收到参数" + blob + " | | " + barCode);
        System.out.println();
        ListObject listObject = new ListObject();
        if(cu.setDrugsImg(barCode, blob)){
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("修改成功");
        }else{
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("修改失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }

    @RequestMapping(value = "/getDrugsImg")
    public void getDrugsImg(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        System.out.println();
        logger.info(httpServletRequest.getParameter("barCode") + "读取照片");
        System.out.println();
        ListObject listObject = new ListObject();
        // 读取时反转为Byte对象，
        // 因为Blob对象在数据库中是二进制数据，而Byte对象是字节数据
        byte[] bytes = (byte[]) cu.getDrugsImg(httpServletRequest.getParameter("barCode"));
        if (bytes != null) {
            listObject.setCode(StatusCode.CODE_SUCCESS);
            listObject.setMsg("读取成功");
            listObject.setItems(Collections.singletonList(bytes));
        } else {
            listObject.setCode(StatusCode.CODE_ERROR);
            listObject.setMsg("读取失败");
        }
        ResponseUtils.renderJson(response, JackJsonUtils.toJson(listObject));
    }
}
