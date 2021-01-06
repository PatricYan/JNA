package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class JavaShellTest {
    private static Logger logger = LoggerFactory.getLogger(JavaShellTest.class);

    public int runShell(String cmd, String name) throws IOException {
        String log = getLogFile(name);
        BufferedWriter writer = new BufferedWriter(new FileWriter(log, true));
        BufferedReader reader = null;
        int result = -1;
        Process process = null;
        try {

            process = new ProcessBuilder("/bin/bash", "-c", cmd).redirectErrorStream(true).start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                } else {
                    logger.info("###: " + line);
                    write(writer, line + "\n");
                }
            }

            logger.info("*********** command is over ...");

            process.waitFor(10, TimeUnit.MINUTES);
            result = process.exitValue();
        } catch (Exception e) {
            logger.error(" run shell error: {}", e.getMessage());

        } finally {
            if (process != null) {
                process.destroy();
            }
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }


    public int write(BufferedWriter writer, String data) {
        try {
            writer.write(data);
            writer.flush();
//            writer.close();
            return data.length();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(" write error: {}", e.getMessage());
        }

        return 0;
    }

    public String getLogFile(String name) throws IOException {
        String filename = "/home/www/test123/dataTest/" + name;
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        return filename;
    }

    public String makeShell(String cmd, String file) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("#!/bin/bash\n");

            sb.append("source /etc/profile\n");
            sb.append("hostname\n");
            sb.append("result=0\n");
            sb.append("pid=`echo \"$$\"`\n");
            sb.append("runfile=$0\n");
            sb.append("taskid=$1\n");
            sb.append("firetime=$2\n");

            sb.append("mkdir -p $workdir&&cd $workdir\n");
            sb.append("echo \"ps --ppid $$| awk '{if(\\$1~/[0-9]+/) print \\$1}'| xargs kill -9\">$workdir/_killPid\n");
            sb.append("echo \"FireTime:$firetime\"\n");
            sb.append("echo \"RunTime:`date '+%Y-%m-%d %H:%M:%S'`\"\n");
            sb.append("echo \"CommandFile:$runfile\"\n");

            sb.append(cmd).append("\n");
            sb.append("result=$?\nif [ $result -ne 0 ]; then\nexit $result\nfi\n");


            BufferedWriter writer = new BufferedWriter(new FileWriter(getLogFile(file), true));
            write(writer, sb.toString());

        } catch (Exception e) {
            e.printStackTrace();

        }

        return file;
    }

}
