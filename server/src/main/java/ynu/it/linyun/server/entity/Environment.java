package ynu.it.linyun.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author linyun
 * @since 2021-04-25-14:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Environment {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @JsonIgnore
    private String image;
}
