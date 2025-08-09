// 代码生成时间: 2025-08-10 05:53:23
import io.micronaut.context.annotation.Requires;
    import io.micronaut.http.HttpResponse;
    import io.micronaut.http.annotation.Controller;
    import io.micronaut.http.annotation.Post;
    import io.micronaut.http.annotation.Body;
    import io.micronaut.http.exceptions.HttpStatusException;
    import io.micronaut.http.HttpStatus;
    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import javax.inject.Singleton;
    import java.util.Optional;

    /**
     * JSON数据格式转换器，提供JSON数据格式转换功能。
     */
    @Controller("/json")
    @Singleton
    @Requires(env = "jsonTransformer")
    public class JsonDataTransformer {

        private final ObjectMapper objectMapper;

        /**
         * 构造函数，初始化ObjectMapper对象。
         */
        public JsonDataTransformer(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        /**
         * 提供JSON数据格式转换的POST接口。
         * @param jsonNode 输入的JSON数据。
         * @return 转换后的JSON数据。
         */
        @Post("/transform")
        public HttpResponse<String> transformJson(@Body JsonNode jsonNode) {
            try {
                // 检查输入的JSON数据是否为空
                if (jsonNode == null) {
                    throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Input JSON data is null.");
                }

                // 将输入的JSON数据转换为字符串
                String jsonString = objectMapper.writeValueAsString(jsonNode);

                // 返回转换后的JSON数据
                return HttpResponse.ok(jsonString);
            } catch (Exception e) {
                // 处理转换过程中的异常
                throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while transforming JSON data: " + e.getMessage());
            }
        }
    }