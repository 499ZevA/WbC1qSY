// 代码生成时间: 2025-09-14 00:10:24
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import javax.validation.executable.ValidateOnExecution;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * HTTP请求处理器
 *
 * @author Your Name
 * @since 1.0
 */
@ValidateOnExecution
@Controller("/api/handler")
public class HttpRequestHandler {

    // 限制输入为非空且长度为10的字符串
    @NotBlank(message = "Input string cannot be blank")
    @Pattern.List({
        @Pattern(regexp = "^.{10}$", message = "Input string must be exactly 10 characters long")
    })
    private String input;

    /**
     * 处理GET请求并返回问候语
     *
     * @return HttpResponse<String> 问候语
     */
    @Get("/greet")
    public HttpResponse<String> greet() {
        return HttpResponse.ok("Hello, welcome to the HTTP request handler!");
    }

    /**
     * 根据提供的ID返回一个随机数
     *
     * @param id 路径变量
     * @return HttpResponse<Integer> 随机数
     */
    @Get("/random/{id}")
    public HttpResponse<Integer> getRandomNumber(@PathVariable Integer id) {
        if (id == null) {
            // 如果ID为空，则返回400错误
            return HttpResponse.status(HttpStatus.BAD_REQUEST).body("Invalid ID provided");
        }
        return HttpResponse.ok(ThreadLocalRandom.current().nextInt(1, 100));
    }

    /**
     * 根据提供的ID返回一个问候语
     *
     * @param id 路径变量
     * @return HttpResponse<String> 问候语
     */
    @Get("/greet/{id}")
    public HttpResponse<String> greetById(@PathVariable Integer id) {
        if (id == null) {
            // 如果ID为空，则返回400错误
            return HttpResponse.status(HttpStatus.BAD_REQUEST).body("Invalid ID provided");
        }
        return HttpResponse.ok("Hello, ID: " + id);
    }

    /**
     * 处理POST请求并验证输入
     *
     * @param input 输入字符串
     * @return HttpResponse<String> 验证结果
     */
    @Get("/check-input")
    public HttpResponse<String> checkInput() {
        if (input == null || input.isEmpty() || input.length() != 10) {
            // 如果输入不符合规范，则返回400错误
            return HttpResponse.status(HttpStatus.BAD_REQUEST).body("Invalid input provided");
        }
        return HttpResponse.ok("Valid input received");
    }
}
