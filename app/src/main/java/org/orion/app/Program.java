package org.orion.app;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class Program {
    public static final int PORT = 9500;
    public static final String WEB_DIR = "app/src/webapp";
    public static final String OUTPUT_DIR = "app/out";
    public static final String WEB_APP_MOUNT_DIR = "/WEB-INF/classes";
    public static final String BASE_PATH = "/";
    public static final String APP_BASE_URL = "";

    public static void main(String[] args) throws ServletException, LifecycleException {
        System.setProperty("tomcat.util.scan.StandardJarScanFilter.jarsToSkip", "*.jar");
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(PORT);
        Context context = tomcat.addWebapp(APP_BASE_URL, new File(WEB_DIR).getAbsolutePath());

        File classDir = new File(OUTPUT_DIR);
        WebResourceRoot resourceRoot = new StandardRoot(context);
        resourceRoot.addPreResources(new DirResourceSet(resourceRoot, WEB_APP_MOUNT_DIR, classDir.getAbsolutePath(), BASE_PATH));
        context.setResources(resourceRoot);

        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }
}

