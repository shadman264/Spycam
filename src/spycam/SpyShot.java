/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spycam;

 
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import javax.imageio.ImageIO;
 
/**
 * This program demonstrates how to capture a screenshot (full screen)
 * as an image which will be saved into a file.
 * @author shadman264
 *
 */
public class SpyShot {

    public SpyShot() {
    }
 
    public void ScreenshotGrabber() {
        try {
            Robot robot = new Robot();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            String format = "jpg";
            String fileName = timeStamp + "." + format;
             
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
             
            System.out.println("A full screenshot saved!");
        } 
        catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }
}