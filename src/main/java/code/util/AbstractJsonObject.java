package code.util;

import java.util.Date;

public class AbstractJsonObject {
    //code
    private String code;
    //msg
    private String msg;

    private int total = 100;

    private Long time = new Date().getTime();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the time
     */
    public Long getTime() {
        return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(Long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setContent(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setStatusObject(StatusObject statusObject) {
        this.code = statusObject.getCode();
        this.msg = statusObject.getMsg();
    }
}
