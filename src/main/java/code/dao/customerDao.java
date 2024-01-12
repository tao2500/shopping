package code.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import code.pojo.customer;

import java.util.List;

/**
 * ClassName:customerDao
 * Package:code.dao
 * Description:
 *
 * @Date:2024/1/4 15:51
 * @Author:2500594037@qq.com
 */

@Repository("customerDao")
@Mapper
public interface customerDao {
    public boolean customerAdd(customer cu);
    public List<customer> selectedAll();
    public customer selectedCustomer(String telephone);
    public customer selectedCustomerById(String id);
    public boolean deleteCustomer(String id);
    public boolean upDataCustomer(customer c);
    public customer login(String telephone, String password);
}
