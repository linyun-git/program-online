package ynu.it.linyun.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 
 * </p>
 *
 * @author linyun
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Workspace implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "仓库名不允许为空")
    private String name;

    @NotBlank(message = "仓库权限不允许为空")
    @Pattern(regexp = "(private)|(public)", message = "仓库权限控制不正确")
    private String authorityType;

    @JsonIgnore
    private String directoryCode;


}
