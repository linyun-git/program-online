package ynu.it.linyun.server.common.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import ynu.it.linyun.server.common.util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linyun
 * @create 2021-04-21-21:39
 */
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Map<String, String> map = new HashMap<>();

        //获取请求头中的token令牌
        String token = request.getHeader("token");
        try {
            JWTUtil.verify(token);//验证令牌
            return true;//放行请求
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "无效签名!");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token过期!");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "token算法不一致!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效!!");
        }
        map.put("code", "403");//设置状态
        //将 map 转为json  jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
