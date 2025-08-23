// 代码生成时间: 2025-08-23 18:43:45
import io.micronaut.core.annotation.Introspected;
    import io.micronaut.http.annotation.Controller;
    import io.micronaut.http.annotation.Post;
    import io.micronaut.http.MediaType;
    import java.io.File;
    import java.io.IOException;
    import java.nio.file.*;
    import java.util.*;
    import java.util.stream.Collectors;

    /**
     * Controller for handling batch file renaming operations.
     */
    @Controller("/rename")
    public class BatchFileRenamer {

        /**
         * Rename multiple files in a specified directory.
         *
         * @param request Request body containing directory path and file rename pattern.
         * @return A response indicating the result of the operation.
         */
        @Post(value = "/batch", consumes = MediaType.APPLICATION_JSON)
        public Map<String, Object> renameFiles(RenameRequest request) {
            Map<String, Object> response = new HashMap<>();
            File directory = new File(request.getDirectory());

            try {
                // Check if the directory exists and is indeed a directory
                if (!directory.exists() || !directory.isDirectory()) {
                    response.put("status", "error");
                    response.put("message", "The specified directory does not exist or is not a directory.");
                    return response;
                }

                // List all files in the directory
                List<File> files = Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                        .sorted()
                        .collect(Collectors.toList());

                // Rename files based on the provided pattern
                int renamedCount = 0;
                for (File file : files) {
                    String newName = generateNewName(request.getPattern(), renamedCount);
                    File renamedFile = new File(file.getParent(), newName);
                    if (file.renameTo(renamedFile)) {
                        renamedCount++;
                    }
                }

                response.put("status", "success");
                response.put("renamedFilesCount", renamedCount);
            } catch (SecurityException e) {
                response.put("status", "error");
                response.put("message", "Access denied to the specified directory.");
            } catch (Exception e) {
                response.put("status", "error");
                response.put("message", "An error occurred during the renaming process.");
            }

            return response;
        }

        /**
         * Generates a new file name based on the provided pattern and index.
         *
         * @param pattern The renaming pattern.
         * @param index   The index of the file.
         * @return The new file name.
         */
        private String generateNewName(String pattern, int index) {
            return String.format(pattern, index);
        }

        /**
         * Request model for batch file renaming.
         */
        @Introspected
        static class RenameRequest {
            private String directory;
            private String pattern;

            // Getters and setters
            public String getDirectory() {
                return directory;
            }

            public void setDirectory(String directory) {
                this.directory = directory;
            }

            public String getPattern() {
                return pattern;
            }

            public void setPattern(String pattern) {
                this.pattern = pattern;
            }
        }
    }