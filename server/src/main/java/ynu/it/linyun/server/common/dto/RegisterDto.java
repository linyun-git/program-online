package ynu.it.linyun.server.common.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author linyun
 * @create 2021-04-21-21:49
 */

@Data
public class RegisterDto implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String name;
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String passwordMd5;
}
