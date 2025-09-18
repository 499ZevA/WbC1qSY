// 代码生成时间: 2025-09-18 12:57:10
import io.micronaut.core.annotation.Introspected;
    import java.io.File;
    import java.io.IOException;
    import java.nio.file.*;
    import java.nio.file.attribute.BasicFileAttributes;
    import java.util.Comparator;

    @Introspected
    public class FolderOrganizer {

        /**
         * Organizes the files in a directory by moving them into subdirectories based on their file type.
         * 
         * @param sourceDirectory The directory to organize
         */
        public void organizeDirectory(String sourceDirectory) {
            File directory = new File(sourceDirectory);

            if (!directory.isDirectory()) {
                throw new IllegalArgumentException(