package ynu.it.linyun.server.common.dto;

import lombok.Data;

/**
 * @author linyun
 * @create 2021-04-22-17:23
 */
@Data
public class QueryDto {
    private String query;
    private Integer pageIndex;
    private Integer pageSize;
}
