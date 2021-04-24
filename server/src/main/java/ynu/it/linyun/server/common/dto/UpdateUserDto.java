package ynu.it.linyun.server.common.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author linyun
 * @since 2021-04-24-21:57
 */
@Data
public class UpdateUserDto {
    @NotBlank(message = "昵称不能为空")
    private String name;
    private String description;
}
