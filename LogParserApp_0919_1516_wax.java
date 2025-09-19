// 代码生成时间: 2025-09-19 15:16:40
package com.example.logparser;

import io.micronaut.runtime.Micronaut;
import jakarta.inject.Singleton;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main application class that parses log files.
 */
@Singleton
public class LogParserApp {

    // Regular expression pattern for log lines
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2},\d{3})\s+(\w+)\s+(\S+)\s+(.*)"
    );

    public static void main(String[] args) {
        Micronaut.run(LogParserApp.class);
    }

    /**
     * Parses a log file and returns a list of log entries.
     *
     * @param filePath The path to the log file.
     * @return A list of log entries.
     * @throws IOException If an I/O error occurs.
     */
    public List<LogEntry> parseLogFile(String filePath) throws IOException {
        List<LogEntry> logEntries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = LOG_PATTERN.matcher(line);
                if (matcher.find()) {
                    LogEntry entry = new LogEntry(
                            matcher.group(1), // Timestamp
                            matcher.group(2), // Log level
                            matcher.group(3), // Logger name
                            matcher.group(4)  // Message
                    );
                    logEntries.add(entry);
                }
            }
        } catch (IOException e) {
            throw new IOException("Failed to parse log file: " + filePath, e);
        }
        return logEntries;
    }
}

/**
 * Represents a log entry with its properties.
 */
class LogEntry {
    private final String timestamp;
    private final String level;
    private final String loggerName;
    private final String message;

    public LogEntry(String timestamp, String level, String loggerName, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
    }

    // Getters
    public String getTimestamp() { return timestamp; }
    public String getLevel() { return level; }
    public String getLoggerName() { return loggerName; }
    public String getMessage() { return message; }
}
