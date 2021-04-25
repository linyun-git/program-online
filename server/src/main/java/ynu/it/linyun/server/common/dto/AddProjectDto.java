package ynu.it.linyun.server.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author linyun
 * @since 2021-04-22-18:26
 */
@Data
public class AddProjectDto {

    @NotBlank(message = "项目名不允许为空")
    private String name;

    @NotBlank(message = "项目权限不允许为空")
    @Pattern(regexp = "(private)|(public)", message = "项目权限控制不正确")
    private String authorityType;

    @NotBlank(message = "项目介绍不能为空")
    private String description;

    @NotNull(message = "所属仓库不能为空")
    private Integer workspaceId;
}
