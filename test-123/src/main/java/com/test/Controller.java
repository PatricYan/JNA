package com.test;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class Controller {
    private static Logger logger = LoggerFactory.getLogger(Controller.class);

    public void runShell(String cmd) {
        logger.info("******** cmd is: {}", cmd);
        String[] shell = new String[]{"/bin/bash", "-c", cmd};
        try {
            Process process = Runtime.getRuntime().exec(shell);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                } else {
                    logger.info("###: " + line);
                }
            }

            logger.info("###: *********** command is over ...");

            process.waitFor(10, TimeUnit.MINUTES);
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Failed to run shell: {}", e.getMessage());
        }

    }

    public void test_1() {
        try {
            String cmd = "ps j -A | grep " + getPid() + " | grep -v grep";
            runShell(cmd);
            JNAPGID.setsid();
            runShell(cmd);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("****** error: {}", e.getMessage());
        }
    }

    public void test_2() {
        try {
            String pid = getPid();
            String cmd = "ps j -A | grep " + pid + " | grep -v grep";

            runShell(cmd);
            int pidI = Integer.parseInt(pid);
            JNAPGID.setpgid(0, pidI);
            runShell(cmd);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("****** error: {}", e.getMessage());
        }


    }

    public String getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        logger.info("****** name: {}", name);
        // get pid
        String pid = name.split("@")[0];
        logger.info("****** pid: {}", pid);
        return pid;
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

            runShell("ls -alh");

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
