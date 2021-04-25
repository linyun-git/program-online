package ynu.it.linyun.server.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author linyun
 * @since 2021-04-25-13:47
 */
@Data
public class EnvironmentDto {
    @NotBlank(message = "环境名称不能为空")
    @Pattern(regexp = "(java)|(node)", message = "不支持的环境")
    private String name;
    @NotBlank(message = "环境版本不能为空")
    private String version;
}
