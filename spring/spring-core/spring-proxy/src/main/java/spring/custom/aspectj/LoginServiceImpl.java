package spring.custom.aspectj;

/**
 * 登录服务实现类，login 方法使用 @UseAop 注解标记为 AOP 切点目标。
 * 演示自定义注解与 Spring AOP 切面协作，在方法执行前自动触发前置通知。
 */
public class LoginServiceImpl implements LoginService {

    @UseAop
    public String login(String userName){
        System.out.println("正在登录");
        return "success";
    }
}