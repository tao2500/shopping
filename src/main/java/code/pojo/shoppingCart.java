package code.pojo;

/**
 * ClassName:shoppingCart
 * Package:code.pojo
 * Description:
 *
 * @Date:2024/1/4 14:58
 * @Author:2500594037@qq.com
 */
public class shoppingCart {
    private String id;
    private String telephone;
    private String idOrderFrom;
    private int count;
    private String status;
    private String joinTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdOrderFrom() {
        return idOrderFrom;
    }

    public void setIdOrderFrom(String idOrderFrom) {
        this.idOrderFrom = idOrderFrom;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public String toString() {
        return "shoppingCart{" +
                "id='" + id + '\'' +
                ", telephone='" + telephone + '\'' +
                ", idOrderFrom='" + idOrderFrom + '\'' +
                ", count=" + count +
                ", status='" + status + '\'' +
                ", joinTime='" + joinTime + '\'' +
                '}';
    }
}
