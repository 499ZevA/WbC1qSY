// 代码生成时间: 2025-09-24 06:24:56
import io.micronaut.http.annotation.Controller;
# 增强安全性
import io.micronaut.http.annotation.Post;
# 改进用户体验
import io.micronaut.http.MediaType;
# NOTE: 重要实现细节
import io.micronaut.http.annotation.Body;
import io.micronaut.core.io.ResourceLoader;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.core.util.ArrayUtils;
# 优化算法效率
import java.io.InputStream;
# 优化算法效率
import java.io.OutputStream;
import java.io.IOException;
# 改进用户体验
import java.nio.file.Files;
import java.nio.file.Paths;
# 改进用户体验
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import javax.inject.Inject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
# 优化算法效率
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.converter.pdf.NoPrintElementPDF;

@Controller("/document")
public class DocumentConverterService {

    @Inject
    private ResourceLoader resourceLoader;
# 优化算法效率

    private static final String TEMP_DIR = "/tmp/";

    /**
     * Converts an uploaded Word document to a PDF file.
# FIXME: 处理边界情况
     * 
     * @param request The HTTP request containing the uploaded file.
     * @return HttpResponse with the converted PDF file.
     */
# NOTE: 重要实现细节
    @Post(value = "/convert", consumes = MediaType.MULTIPART_FORM_DATA)
    public HttpResponse<String> convertDocument(@Body HttpRequest<?> request) {
        // Check if the request contains a file
        if (request.getBody().isEmpty()) {
            return HttpResponse.badRequest("No file uploaded.");
        }

        try {
            // Extract the file from the request
            MultipartBody body = request.getBody(MultipartBody.class).orElseThrow(() -> new IllegalArgumentException("No file uploaded."));
            InputStream wordInputStream = body.getFile("file").getInputStream();

            // Convert the Word document to a PDF
            XWPFDocument document = new XWPFDocument(wordInputStream);
            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, createPdfOutputStream(), options);

            // Return the PDF as a response
            OutputStream pdfOutputStream = createPdfOutputStream();
            return HttpResponse.ok(pdfOutputStream);

        } catch (IOException e) {
            // Handle exceptions and return an error response
            return HttpResponse.serverError("Error during document conversion: " + e.getMessage());
        }
    }

    /**
     * Creates a temporary PDF output stream.
     * 
     * @return OutputStream for writing the converted PDF.
     * @throws IOException If an I/O error occurs.
     */
# FIXME: 处理边界情况
    private OutputStream createPdfOutputStream() throws IOException {
        String tempFilePath = TEMP_DIR + "output.pdf";
        return Files.newOutputStream(Paths.get(tempFilePath), StandardCopyOption.REPLACE_EXISTING);
    }
}
