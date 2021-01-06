package com.test;

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
        logger.info("********** main dir:{}", System.getProperty("user.dir"));
        logger.info("********** main class dir URI: {}", Data.class.getResource("").toString());
        logger.info("********** main class dir PATH: {}", Data.class.getResource("/").getPath());
        loadLib();

    }


    public static void loadLib() {
        try {
            String libName = "libTools.jnilib";
            URL url = Data.class.getResource("/JNA/libTools.jnilib");
            logger.info("*********+++++++++======================= url path: {}", url.getPath());
            File nativeLibTmpFile = new File(libName);
            nativeLibTmpFile.deleteOnExit();

            InputStream in = Data.class.getResourceAsStream("/JNA/libTools.jnilib");
            Files.copy(in, nativeLibTmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            logger.info("***************** =========================== new path: {}", nativeLibTmpFile.getAbsolutePath());
            System.load(nativeLibTmpFile.getAbsolutePath());

            // System.load("/Users/mac/working/javaWorking/program/test-123/src/main/java/JNI/libTools.jnilib");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("******* Failed to load lib: {}", e.getMessage());
        }
    }

    public void test() {
        JavaShellTest javaShellTest = new JavaShellTest();
        try {
            String cmd = "ls -alh \n pwd";
            String file = javaShellTest.makeShell(cmd, "abc-123.sh");
            javaShellTest.runShell(javaShellTest.getLogFile(file), "log.log");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(" error: {}", e.getMessage());
        }
    }


}
