package com.test;

import com.test.System.EPlatform;
import com.test.System.OSinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@SpringBootApplication
public class Data {

    private static Logger logger = LoggerFactory.getLogger(Data.class);

    public static void main(String args[]) {
        ConfigurableApplicationContext context = SpringApplication.run(Data.class, args);
         loadLib();
    }


    /**
     * load the JNA lib
     */
    public static void loadLib() {
        try {
            EPlatform platform = OSinfo.getOSname();
            logger.info("*********+++++++++======================= operation system is: {}", platform.toString());
            // set default to mac
            String libName = "libTools.jnilib";
            URL url = Data.class.getResource("/JNA/libTools.jnilib");
            if (platform.equals(EPlatform.Mac_OS) || platform.equals(EPlatform.Mac_OS_X)) {
                libName = "libTools.jnilib";
                url = Data.class.getResource("/JNA/libTools.jnilib");
            } else if (platform.equals(EPlatform.Linux)) {
                libName = "libTools.so";
                url = Data.class.getResource("/JNA/libTools.so");
            }

            logger.info("*********+++++++++======================= url path: {}", url.getPath());
            File nativeLibTmpFile = new File(libName);
            nativeLibTmpFile.deleteOnExit();

            InputStream in = url.openStream();
            // InputStream in = Data.class.getResourceAsStream("/JNA/libTools.jnilib");
            Files.copy(in, nativeLibTmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            logger.info("***************** =========================== new path: {}", nativeLibTmpFile.getAbsolutePath());
            System.load(nativeLibTmpFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("*********+++++++++======================= Failed to load lib: {}", e.getMessage());
        }
    }


}
