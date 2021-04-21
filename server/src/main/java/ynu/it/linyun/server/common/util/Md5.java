package ynu.it.linyun.server.common.util;

import org.springframework.util.DigestUtils;

/**
 * @author linyun
 * @create 2021-04-21-21:59
 */
public class Md5 {
    private Md5() {
    }

    public static String toMd5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
