// 代码生成时间: 2025-07-30 19:53:28
package com.example.services;

import io.micronaut.core.annotation.Introspected;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Singleton
@Introspected
public class BackupRestoreService {

    private static final String BACKUP_DIR = "backup";
    private static final String BACKUP_FILE_EXTENSION = ".zip";

    // Backups the data to a ZIP file
    public String backupData(String data) {
        String backupFileName = "backup_" + System.currentTimeMillis() + BACKUP_FILE_EXTENSION;
        try {
            Path backupPath = Paths.get(BACKUP_DIR, backupFileName);
            try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(backupPath))) {
                ZipEntry zipEntry = new ZipEntry("data.txt");
                zos.putNextEntry(zipEntry);
                zos.write(data.getBytes());
                zos.closeEntry();
            }
            return backupFileName;
        } catch (IOException e) {
            throw new RuntimeException("Error backing up data", e);
        }
    }

    // Restores data from a ZIP file
    public String restoreData(String backupFileName) {
        try {
            Path backupPath = Paths.get(BACKUP_DIR, backupFileName);
            if (!Files.exists(backupPath)) {
                throw new RuntimeException("Backup file does not exist");
            }
            byte[] data = Files.readAllBytes(backupPath);
            return new String(data);
        } catch (IOException e) {
            throw new RuntimeException("Error restoring data", e);
        }
    }
}
