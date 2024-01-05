package code.pojo;

/**
 * ClassName:orderFrom
 * Package:code.pojo
 * Description:
 *
 * @Date:2024/1/4 14:51
 * @Author:2500594037@qq.com
 */
public class orderFrom {
    private String idOrderFrom;
    private String telephone;
    private String detail;
    private String shoppingAdd;
    private double total;
    private String status;
    private String joinTime;

    public String getIdOrderFrom() {
        return idOrderFrom;
    }

    public void setIdOrderFrom(String idOrderFrom) {
        this.idOrderFrom = idOrderFrom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getShoppingAdd() {
        return shoppingAdd;
    }

    public void setShoppingAdd(String shoppingAdd) {
        this.shoppingAdd = shoppingAdd;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
        return "orderFrom{" +
                "idOrderFrom='" + idOrderFrom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", detail='" + detail + '\'' +
                ", shoppingAdd='" + shoppingAdd + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", joinTime='" + joinTime + '\'' +
                '}';
    }
}
