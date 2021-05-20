package ynu.it.linyun.server.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    @NotNull(message = "仓库环境不能为空")
    private Integer environmentId;

    @NotBlank(message = "仓库介绍不能为空")
    private String description;
}
