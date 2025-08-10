// 代码生成时间: 2025-08-10 15:15:27
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.HttpResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.inject.Singleton;

/**
 * A Micronaut controller class for a file unzip tool.
 */
@Controller("/api")
@Singleton
public class UnzipTool {

    @Post(uri = "/unzip", produces = MediaType.TEXT_PLAIN, consumes = MediaType.MULTIPART_FORM_DATA)
    public HttpResponse<String> unzipFile(@Body File zipFile) {
        try {
            // Ensure the zip file is not null and exists
            if (zipFile == null || !zipFile.exists()) {
                return HttpResponse.badRequest("You must provide a zip file.");
            }

            // Define the destination directory for extracted files
            File destinationDir = new File(zipFile.getParent());

            // Check if the destination directory is valid
            if (!destinationDir.exists() || !destinationDir.isDirectory()) {
                return HttpResponse.badRequest("Invalid destination directory.");
            }

            // Unzip the file to the destination directory
            unzip(zipFile, destinationDir);

            // Return a success message
            return HttpResponse.ok("File successfully unzipped.");
        } catch (IOException e) {
            // Handle any IOExceptions that may occur during unzipping
            return HttpResponse.serverError("There was an error unzipping the file: " + e.getMessage());
        }
    }

    /**
     * Unzips a zip file to a destination directory.
     *
     * @param zipFile the zip file to unzip
     * @param destinationDir the directory where files will be extracted
     * @throws IOException if an I/O error occurs during unzipping
     */
    private void unzip(File zipFile, File destinationDir) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry = zipIn.getNextEntry();
            // Loop through the entries in the zip file
            while (entry != null) {
                String filePath = destinationDir + File.separator + entry.getName();

                if (!entry.isDirectory()) {
                    // If the entry is a file, extract it
                    extractFile(zipIn, filePath);
                } else {
                    // If the entry is a directory, create it
                    File dir = new File(filePath);
                    dir.mkdirs();
                }

                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    /**
     * Extracts a file from the zip input stream to the specified path.
     *
     * @param zipIn the zip input stream
     * @param filePath the path to extract the file to
     * @throws IOException if an I/O error occurs during extraction
     */
    private void extractFile(InputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read = 0;

            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }
}
