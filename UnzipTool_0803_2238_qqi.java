// 代码生成时间: 2025-08-03 22:38:22
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * A utility class for unzipping files using Java.
 */
public class UnzipTool {

    private static final String ZIP_EXTENSION = ".zip";
    private static final String DIRECTORY_PREFIX = "unzipped_";

    /**<ol>
     * Unzips a file to the specified destination directory.
     *
     * @param zipFilePath The path to the zip file.
     * @param destDirectory The destination directory to unzip the files.
     * @throws IOException If an I/O error occurs.
     */
    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // Iterates over the entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // If the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // If the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    /**<ol>
     * Extracts a file from the zip input stream.
     *
     * @param zipIn The input stream of the zip file.
     * @param filePath The path to extract the file to.
     * @throws IOException If an I/O error occurs.
     */
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    /**<ol>
     * The main method to test the UnzipTool.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            String zipFilePath = "path_to_zip_file" + ZIP_EXTENSION;
            String destDirectory = "destination_directory";
            UnzipTool.unzip(zipFilePath, destDirectory);
            System.out.println("Unzipping completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred during unzipping.");
        }
    }
}
