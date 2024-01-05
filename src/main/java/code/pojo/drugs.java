package code.pojo;

/**
 * ClassName:drugs
 * Package:code.pojo
 * Description:
 *
 * @Date:2024/1/4 14:43
 * @Author:2500594037@qq.com
 */
public class drugs {
    private String barCode;
    private String imgSrc;
    private String name;
    private String type;
    private String effect;
    private int count;
    private String size;
    private double price;
    private String expires;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "drugs{" +
                "barCode='" + barCode + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", effect='" + effect + '\'' +
                ", count=" + count +
                ", size='" + size + '\'' +
                ", price='" + price + '\'' +
                ", expires='" + expires + '\'' +
                '}';
    }
}
