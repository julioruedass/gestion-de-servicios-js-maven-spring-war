package com.actualizer.system;


import java.io.IOException;

public class SystemCtl {

    public static  void systemCtl(String status, String serviceName) throws IOException, InterruptedException {

        String []serviceCommand = new String[]{
                "sudo",
                "systemctl",
                //"net",
                status,
                serviceName
        };

        Process process = Runtime.getRuntime().exec(serviceCommand);
        process.waitFor();

    }

}
