package code.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultResponse implements java.io.Serializable { //后端返回对象
    private int code;
    private String message;
    private Object data;
}
