package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex
    ) {
        log.info("call resolver", ex);
        // RuntimeException 일때는 아래 try 로직을 무시하고 return null 하게됨
        // 그러면 그냥 기존에 발생한 예외를 서블릿 밖으로 던진다

        try {
            if(ex instanceof IllegalArgumentException){
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST); // 여기서 잡아서 400 에러를 보내버린다?
                return new ModelAndView(); // exception을 sendError 로 바꿔치기 해버림
            }
        } catch (IOException e) {
            log.error("resolver ex:", e);
        }
        return null;
    }
}
