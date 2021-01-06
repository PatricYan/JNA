package com.test;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;

@RestController
@RequestMapping("/test")
public class Controller {
    private static Logger logger = LoggerFactory.getLogger(Controller.class);

    public static void test_1() {
        try {
            JavaShellTest javaShellTest = new JavaShellTest();
            javaShellTest.runShell("/home/www/test123/test.sh", "test_1_sid.log");
            JNAPGID.setsid();
            javaShellTest.runShell("/home/www/test123/test.sh", "test_2_sid.log");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("****** error: {}", e.getMessage());
        }


    }


    public static void test_2() {
        try {
            JavaShellTest javaShellTest = new JavaShellTest();
            javaShellTest.runShell("/home/www/test123/test.sh", "test_1_pgid.log");

            String name = ManagementFactory.getRuntimeMXBean().getName();
            logger.info("****** name: {}", name);
            // get pid
            String pid = name.split("@")[0];
            logger.info("***** Pid is: {}", pid);
            int pidI = Integer.parseInt(pid);
            JNAPGID.setpgid(0, pidI);
            javaShellTest.runShell("/home/www/test123/test.sh", "test_2_pgid.log");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("****** error: {}", e.getMessage());
        }


    }

    @RequestMapping("/ping")
    public String health() {
        System.out.printf("************** path is:/health/ping\n");
        return "success";
    }

    @RequestMapping("/kill")
    public String kill(@RequestBody JSONObject jsonObject) {
        try {
            System.out.printf("************** path is:/test/kill\n");
            logger.info("jsonobjct is: {}", jsonObject.toJSONString());
            JavaShellTest javaShellTest = new JavaShellTest();
            javaShellTest.runShell(jsonObject.getString("Path"), jsonObject.getString("logName"));

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

        }

        return "success";
    }

    @RequestMapping("/hello")
    public String hello(@RequestBody JSONObject jsonObject) {
        try {
            System.out.printf("************** path is:/test/hello\n");
            logger.info("jsonobjct is: {}", jsonObject.toJSONString());
            String text = JNAPGID.sayHello(jsonObject.getString("data"));
            logger.info("*************** result is: {}", text);


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

        }

        return "success";
    }

    @RequestMapping("/sidTest")
    public String sidTest() {
        try {
            System.out.printf("************** path is:/test/sidTest\n");
            test_1();
            logger.info("********* ok...");


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

        }

        return "success";
    }

    @RequestMapping("/pgidTest")
    public String pgidTest() {
        try {
            System.out.printf("************** path is:/test/pgidTest\n");
            test_2();
            logger.info("********* ok...");


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());

        }

        return "success";
    }


}
