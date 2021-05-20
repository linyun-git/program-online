package ynu.it.linyun.server.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linyun
 * @since 2021-05-07-15:13
 */
public class UrlUtil {
    public static Map<String, String> parseQueryParams(String urlQuery) {
        Map<String, String> params = new HashMap<>();
        String[] queryArray = urlQuery.split("&");
        for (String query: queryArray){
            var parse = query.split("=");
            params.put(parse[0], parse[1]);
        }
        return params;
    }
}
