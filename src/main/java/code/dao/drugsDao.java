package code.dao;

import code.pojo.drugs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;

/**
 * ClassName:drugsDao
 * Package:code.dao
 * Description:
 *
 * @Date:2024/1/4 15:41
 * @Author:2500594037@qq.com
 */

@Repository("drugsDao")
@Mapper
public interface drugsDao {
    public boolean addDrugs(drugs dr);
    public List<drugs> selectedAll();
    public List<drugs> selectedByBarCode(String barCode);
    public List<drugs> selectedByName(String name);
    public List<drugs> selectedByType(String type);
    public List<drugs> selectedByEffect(String effect);
    public boolean deleteDrugs(String barCode);
    public boolean upDataDrugs(drugs d);
    public boolean setDrugsImg(String barCode, Blob imgSrc);
    public byte[] getDrugsImg (String barCode);
}
