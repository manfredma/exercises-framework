package manfred.exercises.framework.spring.cloud.resilience4j.connector;


import io.reactivex.Observable;
import manfred.exercises.framework.spring.cloud.resilience4j.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Backend B 连接器实现（manfred resilience4j 包），未使用注解式熔断，由上层 Service 编程式装饰。
 * 演示将弹性策略置于服务层而非连接器层的架构风格。
 */
@Component(value = "backendBConnector")
public class BackendBConnector implements Connector {

    @Override
    public String failure() {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
    }

    @Override
    public String success() {
        return "Hello World from backend B";
    }

    @Override
    public String ignoreException() {
        throw new BusinessException("This exception is ignored by the CircuitBreaker of backend B");
    }

    @Override
    public Observable<String> methodWhichReturnsAStream() {
        return Observable.never();
    }
}
