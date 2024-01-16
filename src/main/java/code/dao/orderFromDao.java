package code.dao;

import code.pojo.orderFrom;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName:orderFromDao
 * Package:code.dao
 * Description:
 *
 * @Date:2024/1/4 15:43
 * @Author:2500594037@qq.com
 */

@Repository("orderFromDao")
@Mapper
public interface orderFromDao {
    public boolean addOrderFrom(orderFrom dr);
    public List<orderFrom> selectedAll();
    public List<orderFrom> selectedByCustomerId(String customerId);
    public List<orderFrom> selectedByStatus(String status);
    public boolean deleteOrderFrom(String idOrderFrom);
    public boolean upDataOrderFrom(orderFrom d);
    public List<orderFrom> getOrderIdByTimeAndCId(String time, String cId);
    public boolean playOk(String idOrderFrom);
}
