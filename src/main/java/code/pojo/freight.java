package code.pojo;

/**
 * ClassName:freight
 * Package:code.pojo
 * Description:
 *
 * @Date:2024/1/13 11:55
 * @Author:2500594037@qq.com
 */
public class freight {
    private String id;
    private String type;
    private String price;
    private String cities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "freight{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", cities='" + cities + '\'' +
                '}';
    }
}
