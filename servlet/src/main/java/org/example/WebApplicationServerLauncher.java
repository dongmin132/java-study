//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

import java.io.File;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebApplicationServerLauncher {
    private static final Logger log = LoggerFactory.getLogger(WebApplicationServerLauncher.class);

    public WebApplicationServerLauncher() {
    }

    public static void main(String[] args) throws Exception {
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.addWebapp("/", (new File(webappDirLocation)).getAbsolutePath());
        log.info("configuring app with basedir: {}", (new File("./" + webappDirLocation)).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
