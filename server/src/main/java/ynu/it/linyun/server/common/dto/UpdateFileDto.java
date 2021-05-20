package ynu.it.linyun.server.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author linyun
 * @since 2021-05-07-18:57
 */

@Data
public class UpdateFileDto {
    @NotNull(message = "文件所属项目不能为空")
    private Integer projectId;
    @NotBlank(message = "文件路径不能为空")
    private String path;
    @Pattern(regexp = "(file)|(folder)", message = "类型只能是文件或目录")
    private String fileType;
}
