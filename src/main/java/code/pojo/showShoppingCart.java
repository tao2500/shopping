package code.pojo;

/**
 * ClassName:showShoppingCart
 * Package:code.pojo
 * Description: 我的购物车页 需要的组合drugs与shoppingCart的映射类
 *
 * @Date:2024/1/14 22:21
 * @Author:2500594037@qq.com
 */
public class showShoppingCart {
    private String id;
    private String customerId;
    private String barCode;
    private String name;
    private String price;
    private String count;
    private String status;
    private String joinTime;
    private String address;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "showShoppingCart{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", barCode='" + barCode + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", count='" + count + '\'' +
                ", status='" + status + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
