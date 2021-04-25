package ynu.it.linyun.server.common.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author linyun
 * @create 2021-04-22-18:09
 */
@Data
public class AddWorkspaceDto {

    @NotBlank(message = "仓库名不允许为空")
    private String name;

    @NotBlank(message = "仓库权限不允许为空")
    @Pattern(regexp = "(private)|(public)", message = "仓库权限控制不正确")
    private String authorityType;

    @Valid
    private List<EnvironmentDto> environments;

    @NotBlank(message = "仓库介绍不能为空")
    private String description;
}
