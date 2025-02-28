package project.canteen.config.websocketConfig;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import project.canteen.config.jwtConfig.jwtProvider;

import java.util.Map;

@Component
public class webSocketAuthInterceptor implements HandshakeInterceptor {

    @Autowired
    private jwtProvider jwtUtil;
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
//        System.out.println("🔍 Interceptor - beforeHandshake() triggered");
        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            String token = httpRequest.getParameter("token");
//            String token = httpRequest.getHeader("Authorization");
//            System.out.println(token);
            // Lấy token từ URL query

            if (token != null && jwtUtil.validateToken(token)) {
                String username = jwtUtil.getLoginFormToke(token);
//                System.out.println(username);
                attributes.put("username", username); // Lưu username vào session
                return true;
            }
        }
        return false; // Từ chối kết nối nếu không có JWT hợp lệ
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
    }
}

