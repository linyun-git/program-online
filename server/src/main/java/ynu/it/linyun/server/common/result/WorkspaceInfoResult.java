package ynu.it.linyun.server.common.result;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ynu.it.linyun.server.entity.Environment;
import ynu.it.linyun.server.entity.Workspace;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author linyun
 * @since 2021-04-25-14:53
 */

@Data
@NoArgsConstructor
public class WorkspaceInfoResult {
    private Integer id;

    private String name;

    private String authorityType;

    private String description;

    private Integer creator;

    private String createDate;

    private Environment environment;

    public WorkspaceInfoResult(Workspace workspace, Environment environment) {
        this.id = workspace.getId();
        this.name = workspace.getName();
        this.authorityType = workspace.getAuthorityType();
        this.description = workspace.getDescription();
        this.creator = workspace.getCreator();
        this.createDate = workspace.getCreateDate();
        this.environment = environment;
    }
}
