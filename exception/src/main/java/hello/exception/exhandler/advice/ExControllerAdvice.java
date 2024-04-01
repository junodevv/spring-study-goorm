package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
// controllerAdvice + ResponseBody
@RestControllerAdvice(basePackages = "hello.exception.api")
public class ExControllerAdvice {

    /**
     * @ExceptionHandler:
     * 이 컨트롤러에서 예외(IllegalArgumentException)가 발생되면 여기서 잡고 메서드내의 로직을 수행한다.
     * 그리고 RestController이기 때문에 그대로 Json으로 반환된다.
     * 근데 이제 정상응답 처리가 되기 떄문에
     * @ResponseStatus: 로 Http 상태코드를 에러코드로 바꿔서 보내줘야한다.
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalException(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler // 이름이 같은 경우 `()`생략가능
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) { //Exception: 예외의 최상위, 즉 위에서 처리하지 않은 모든 예외를 여기서 처리함
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
