/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Oshadha
 */
public class Utility {
    
     public static void  setImage(JFrame frame,String imagePath,int newWidth, int newHeight){
        try {
            BufferedImage originalImage = ImageIO.read(Utility.class.getResourceAsStream(imagePath));
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
            resizedImage.createGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH ),0 , 0,null);
            ImageIcon backgroundImage  = new ImageIcon(resizedImage);
            JLabel backgroundJLabel = new JLabel(backgroundImage);
            frame.getContentPane().add(backgroundJLabel, BorderLayout.CENTER);
            frame.validate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static HashMap<String, JFrame> formsMap = new HashMap<>();
    
    public static  void openForm(String formName, JFrame formInstance) {
        JFrame existingForm = formsMap.get(formName);
        if (existingForm==null  || !existingForm.isVisible()) {
            formsMap.put(formName, formInstance);
            formInstance.setVisible(true);
            
        }else {
            existingForm.toFront();
        }
        
    }
    
    public static String getPath(String finalPath) {
        String projectPath = System.getProperty("user.dir");
        return projectPath + "\\src\\" + finalPath;
    }
    
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return fileName.substring(lastDotIndex + 1);
            
        }
        return  "";
    }
    
    public static BufferedImage scaleImage(BufferedImage originalImage, BufferedImage selectedImage) {
        int width = selectedImage.getWidth();
        int height = selectedImage.getHeight();
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
        scaledImage.createGraphics().drawImage(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH),0,0, null);
        return scaledImage;
    }
    
}
