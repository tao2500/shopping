package code.pojo;

/**
 * ClassName:pharmacist
 * Package:code.pojo
 * Description:
 *
 * @Date:2024/1/4 14:56
 * @Author:2500594037@qq.com
 */
public class pharmacist {
    private String id;
    private String name;
    private String duties;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "pharmacist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", duties='" + duties + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
