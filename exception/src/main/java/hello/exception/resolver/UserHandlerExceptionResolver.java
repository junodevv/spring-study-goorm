package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex
    ) {
        try{
            if (ex instanceof UserException) {
                log.info("UserException Resolver to 400");
                String acceptHeader = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if (acceptHeader.equals("application/json")) {
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("ex", ex.getClass());
                    errorResult.put("message", ex.getMessage());

                    String result = objectMapper.writeValueAsString(errorResult); // 객체를 문자로 변환

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(result); // 문자를 json으로?
                    return new ModelAndView(); // 위에서 예외 먹어버리고, 정상응답을 보내면서 Body에는 response를 채워보내는것
                }else{
                    //text/html 이런거, 클라이언트의 accept가 application/json이 아닌경우
                    return new ModelAndView("error/500");
                }
            }
        }catch (IOException e){
            log.error("resolver ex", e);
        }
        return null;
    }
}
