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
    private String customerId;
    private String barCode;
    private String count;
    private String status;
    private String joinTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
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
                ", customerId='" + customerId + '\'' +
                ", barCode='" + barCode + '\'' +
                ", count=" + count +
                ", status='" + status + '\'' +
                ", joinTime='" + joinTime + '\'' +
                '}';
    }
}
