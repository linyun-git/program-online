package ynu.it.linyun.server.common.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linyun
 * @since 2021-04-25-20:12
 */

@NoArgsConstructor
@Data
public class PathNode {
    private String fileName;
    @Pattern(regexp = "(file)|(folder)", message = "类型只能是文件或目录")
    private String fileType;
    private List<PathNode> children;

    public PathNode(String fileName, String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
        if (this.fileType.equals("folder")) {
            this.children = new ArrayList<>();
        }
    }
}
