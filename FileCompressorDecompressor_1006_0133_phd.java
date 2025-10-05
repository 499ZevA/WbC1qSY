// 代码生成时间: 2025-10-06 01:33:29
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.HttpResponse;
import javax.inject.Singleton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * FileCompressorDecompressor is a controller class to handle file compression and decompression.
 */
@Controller("/api")
@Singleton
public class FileCompressorDecompressor {

    private static final String DESTINATION_FOLDER = "./extractedFiles";

    /**
     * Compress a file and return a compressed file as HttpResponse.
     *
     * @param filePath The path of the file to compress.
     * @return A HttpResponse containing the compressed file data.
     */
    @Post(value = "/compress", produces = MediaType.APPLICATION_OCTET_STREAM, consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<byte[]> compressFile(String filePath) {
        try {
            File file = new File(filePath);
            byte[] compressedFileData = compressFileToByteArray(file);
            return HttpResponse.ok(compressedFileData);
        } catch (IOException e) {
            return HttpResponse.serverError(e);
        }
    }

    /**
     * Decompress a file from an InputStream and save it to a destination folder.
     *
     * @param inputStream The InputStream containing the compressed file data.
     * @return A HttpResponse indicating the status of the operation.
     */
    @Post(value = "/decompress", consumes = MediaType.APPLICATION_OCTET_STREAM)
    public HttpResponse<String> decompressFile(InputStream inputStream) {
        try {
            Files.createDirectories(Paths.get(DESTINATION_FOLDER));
            decompress(inputStream, DESTINATION_FOLDER);
            return HttpResponse.ok("File decompressed successfully.");
        } catch (IOException e) {
            return HttpResponse.serverError(e);
        }
    }

    /**
     * Compress a file into a byte array.
     *
     * @param file The file to compress.
     * @return A byte array containing the compressed file data.
     * @throws IOException If an I/O error occurs during compression.
     */
    private byte[] compressFileToByteArray(File file) throws IOException {
        // Implementation of file compression logic goes here.
        // This method is a placeholder and should be replaced with actual compression logic.
        return new byte[0];
    }

    /**
     * Decompress a file from an InputStream.
     *
     * @param inputStream The InputStream containing the compressed file data.
     * @param destinationFolder The folder where the decompressed files will be saved.
     * @throws IOException If an I/O error occurs during decompression.
     */
    private void decompress(InputStream inputStream, String destinationFolder) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            File newFile = newFile(destinationFolder, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // Write file content
                OutputStream outpuStream = new FileOutputStream(newFile);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    outpuStream.write(buffer, 0, len);
                }
                outpuStream.close();
            }
        }
        zipInputStream.closeEntry();
        zipInputStream.close();
    }

    /**
     * Create a new File instance by defining the path from the destination folder and the zip entry.
     *
     * @param destinationFolder The destination folder path.
     * @param zipEntry The zip entry containing the file path information.
     * @return A new File instance.
     */
    private static File newFile(String destinationFolder, ZipEntry zipEntry) {
        File destDir = new File(destinationFolder);
        String zipEntryName = zipEntry.getName();
        File zipFile = new File(destDir, zipEntryName);
        return zipFile;
    }
}
