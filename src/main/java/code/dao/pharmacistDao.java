package code.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import code.pojo.pharmacist;

import java.util.List;

/**
 * ClassName:pharmacistDao
 * Package:code.dao
 * Description:
 *
 * @Date:2024/1/4 15:46
 * @Author:2500594037@qq.com
 */

@Repository("pharmacistDao")
@Mapper
public interface pharmacistDao {
    public boolean addPharmacist(pharmacist ph);
    public List<pharmacist> selectedAll();
    public pharmacist selectedPharmacistById(String id);
    public boolean deletePharmacist(String id);
    public boolean upDataPharmacist(pharmacist p);
}
