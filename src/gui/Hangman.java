/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package gui;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author shak
 */
public class Hangman extends JLabel {
     private final int Width;
    

    private final int Height;
    
  
    private final String ImageName;
    

    private final String ImageLoc;
    
    
    private final String ImageProp;
    

    private String path;
    

    private BufferedImage image;
    
 
    public Hangman()
    {
        this("hangman", "images/", ".png");
    }

    public Hangman(String imageBaseName, String imageDirectory, 
            String imageType)
    {
        Width = 440;
        Height = 255;
        
        ImageName = imageBaseName;
        ImageLoc = imageDirectory;
        ImageProp = imageType;
        
        
        setPreferredSize(new Dimension(Width, Height));
        path = ImageLoc + ImageName + "_0" + ImageProp;
        image = loadImage(path);
    }
    

    private BufferedImage loadImage(String imagePath)
    {
        BufferedImage img = null;

        try 
        {
            img = ImageIO.read(new File(imagePath));
        } 

        catch (IOException ex) 
        {
            System.err.println("loadImage(): Error: Image at "
                    + imagePath + " could not be found");
            System.exit(1);
        }
        
        return img;
    }
    

    public void nextImage(int imageNumber) 
    { 
        loadNewImage(String.valueOf(imageNumber));
    }
    

    public void loseImage() { loadNewImage("lose"); }
    

    public void winImage() { loadNewImage("win"); }

    private void loadNewImage(String suffix)
    {
        path = ImageLoc + ImageName + "_" + suffix + ImageProp;
        image = loadImage(path);
        repaint();  
    }
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(image, 
                0, 
                0, 
                Width, 
                Height, 
                null);
    }
}
   

