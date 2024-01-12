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
    private String customerId;
    private String detail;
    private String shoppingAdd;
    private String delivery;
    private String waybillNumber;
    private double total;
    private String status;
    private String joinTime;
    private String reasons;

    public String getIdOrderFrom() {
        return idOrderFrom;
    }

    public void setIdOrderFrom(String idOrderFrom) {
        this.idOrderFrom = idOrderFrom;
    }

    public String getTelephone() {
        return customerId;
    }

    public void setTelephone(String customerId) {
        this.customerId = customerId;
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

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    @Override
    public String toString() {
        return "orderFrom{" +
                "idOrderFrom='" + idOrderFrom + '\'' +
                ", customerId='" + customerId + '\'' +
                ", detail='" + detail + '\'' +
                ", shoppingAdd='" + shoppingAdd + '\'' +
                ", delivery='" + delivery + '\'' +
                ", waybillNumber='" + waybillNumber + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", reasons='" + reasons + '\'' +
                '}';
    }
}
