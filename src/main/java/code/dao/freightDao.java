package code.dao;

import code.pojo.drugs;
import code.pojo.freight;

import java.util.List;

/**
 * ClassName:freightDao
 * Package:code.dao
 * Description:
 *
 * @Date:2024/1/13 11:56
 * @Author:2500594037@qq.com
 */
public interface freightDao {
    public boolean addFreight(freight f);
    public freight selectedFreight(String cities);
    public List<freight> selectedAll();
    public boolean deleteFreight(String id);
    public boolean upDataFreight(freight f);
}
