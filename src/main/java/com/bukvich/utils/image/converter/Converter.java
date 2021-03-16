package com.bukvich.utils.image.converter;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
public class Converter {
    private final String imageMagickPath;
    private final File imageDirectory;
    private final FileFilter heicFilter = (pathname) -> pathname.isFile() && pathname.getName().endsWith(".HEIC");

    public void run() {
        File[] files = imageDirectory.listFiles(heicFilter);
        if (files == null) {
            log.warn("There is no .HEIC files in a specified directory");
            return;
        }
        log.info("Found {} files in {} directory. Starting conversion...", files.length, imageDirectory.getAbsolutePath());
        ConvertCmd convertCmd = new ConvertCmd();
        convertCmd.setSearchPath(imageMagickPath);
        for (File file : files) {
            log.info("Process {} file.", file.getAbsolutePath());
            IMOperation imOperation = new IMOperation();
            imOperation.addImage(file.getAbsolutePath());
            imOperation.resize(1280);
            imOperation.addImage(file.getAbsolutePath().replace(".HEIC", "_converted.jpg"));
            try {
                convertCmd.run(imOperation);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            log.info("The conversion have been completed.");
        }
        log.info("All files have been conversed!");
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Please specify path to a folder with images");
        }
        File dir = new File(args[0]);
        Properties properties = new Properties();
        properties.load(new FileInputStream("app.properties"));
        String imageMagicPath = properties.getProperty("IMAGE_MAGIC_PATH");
        Converter converter = new Converter(imageMagicPath, dir);
        converter.run();
    }
}
