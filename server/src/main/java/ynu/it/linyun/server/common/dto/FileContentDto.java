package ynu.it.linyun.server.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author linyun
 * @since 2021-04-25-22:50
 */

@Data
public class FileContentDto {
    @NotNull(message = "要查看的项目不能为空")
    private Integer projectId;
    @NotBlank(message = "文件路径不能为空")
    private String path;
}
