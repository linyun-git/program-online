import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import ynu.it.linyun.server.common.util.JWTUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linyun
 * @since 2021-04-24-14:05
 */
public class JwtTest {
    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        String token = JWTUtil.generateToken(map);
        System.out.println(token);
        String id = JWTUtil.getTokenInfo(token).getClaim("id").asString();
        System.out.println("id: " + id);
        System.out.println(Integer.parseInt(id));
    }
}
