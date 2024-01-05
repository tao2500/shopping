package code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ClassName:customer
 * Package:code.pojo
 * Description:
 *
 * @Date:2024/1/4 14:41
 * @Author:2500594037@qq.com
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class customer implements java.io.Serializable{
    private String id;
    private String telephone;
    private String name;
    private String address;
    private String password;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "customer{" +
                "id=" + id +
                "telephone=" + telephone +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
