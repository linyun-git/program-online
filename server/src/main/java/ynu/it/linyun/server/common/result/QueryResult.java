package ynu.it.linyun.server.common.result;

import lombok.Data;

import java.util.List;

/**
 * @author linyun
 * @create 2021-04-22-17:37
 */
@Data
public class QueryResult {
    Long count;
    List list;
}
