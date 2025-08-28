// 代码生成时间: 2025-08-29 04:44:19
import io.micronaut.core.io.ResourceLoader;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.FileUpload;
import io.micronaut.views.View;
import io.micronaut.views.ViewsRoute;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/csv-processor")
@Singleton
public class CsvBatchProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CsvBatchProcessor.class);
    private static final String UPLOAD_DIR = "uploads/";

    @Inject
    private ResourceLoader resourceLoader;

    // 上传CSV文件并处理
    @Post("/process")
    @View("csvProcessingResult")
    public HttpResponse processCsvFiles(List<CompletedFileUpload> files) {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        try {
            // 确保上传目录存在
            Files.createDirectories(uploadPath);
            // 处理上传的每个文件
            files.forEach(this::processCsvFile);
            return HttpResponse.ok("CSV files processed successfully.");
        } catch (IOException e) {
            logger.error("Error processing CSV files.", e);
            return HttpResponse.serverError(e.getMessage());
        }
    }

    // 处理单个CSV文件
    private void processCsvFile(CompletedFileUpload fileUpload) throws IOException {
        InputStream inputStream = fileUpload.getInputStream();
        Path tempFile = Files.createTempFile(UPLOAD_DIR, "temp-", ".csv");
        FileUtils.copyInputStreamToFile(inputStream, tempFile.toFile());

        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            // 读取CSV文件
            List<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .withQuoteMode(QuoteMode.MINIMAL)
                .withHeader()
                .parse(reader)
                .getRecords();

            // 处理CSV记录，例如验证数据、转换数据等
            processCsvRecords(records);

            // 删除临时文件
            Files.deleteIfExists(tempFile);
        }
    }

    // 处理CSV记录
    private void processCsvRecords(List<CSVRecord> records) {
        // 在此实现具体的CSV记录处理逻辑
        // 例如数据验证、转换、存储等

        // 示例：打印每行数据
        records.forEach(record -> {
            System.out.println(record.toMap().values());
        });
    }

    // 返回上传目录中的文件列表
    @ViewsRoute("/uploads")
    public HttpResponse listUploadedFiles() {
        File uploadDir = resourceLoader.getResource(UPLOAD_DIR).orElseThrow().getFile();
        return HttpResponse.ok(Arrays.stream(uploadDir.listFiles())
            .map(file -> FilenameUtils.getBaseName(file.getName()))
            .collect(Collectors.toList()));
    }
}