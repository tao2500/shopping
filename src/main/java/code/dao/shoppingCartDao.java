package code.dao;

import code.pojo.shoppingCart;
import code.pojo.showShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName:shoppingCartDao
 * Package:code.dao
 * Description:
 *
 * @Date:2024/1/4 15:49
 * @Author:2500594037@qq.com
 */

@Repository("shoppingCartDao")
@Mapper
public interface shoppingCartDao {
    public boolean addShoppingCart(shoppingCart sh);
    public List<shoppingCart> selectedAll();
    public List<shoppingCart> selectedByCustomerId(String customerId);
    public List<showShoppingCart> getMyShoppingCart(String customerId);
    public boolean deleteShoppingCart(String customerId, String barCode);
    public boolean upDataShoppingCart(shoppingCart s);
    public boolean changeCount(String customerId, String barCode, String count);
}
