// 代码生成时间: 2025-09-06 08:47:45
package com.example.documentconverter;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.core.type.Argument;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Inject;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.xmlbeans.XmlException;

@Controller("/documents")
public class DocumentConverter {

    // 注入转换服务
    @Inject
    private ConversionService conversionService;

    /**
     * 将Word文档转换为PDF文档
     */
    @Post(uri = "/convert", processes = MediaType.APPLICATION_OCTET_STREAM)
    public HttpResponse<?> convertWordToPdf(@Body InputStream wordInputStream) {
        try {
            // 创建XWPFDocument对象
            XWPFDocument document = new XWPFDocument(wordInputStream);
            // 创建PDF转换选项
            PdfOptions options = PdfOptions.create();
            // 将Word转换为PDF
            byte[] pdfBytes = conversionService.convertToPdf(document, options);
            // 返回PDF字节流
            return HttpResponse.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
        } catch (XmlException | IOException e) {
            // 处理转换错误
            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Document conversion failed: " + e.getMessage());
        }
    }

    private interface ConversionService {
        byte[] convertToPdf(XWPFDocument document, PdfOptions options) throws IOException;
    }
}
