/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package gui;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author shak
 */
public class LetterSetting extends JPanel {
     private final int Cols;

    private final int Rows;
  
    private final GridLayout LETTER_LET_LAYOUT;
    
    private final int CAPACITY;
 
    private final String ImageLoc;
    

    private final String ImageProp;
    

    private final String word;
    

    private final ArrayList<LetterImgs> rack;

    public LetterSetting()
    {
        this("word", "images/", ".png");
    }

    public LetterSetting(String inPassword, String imageDirectory, 
            String imageType)
    {
        Cols = 8;
        Rows = 3;
        LETTER_LET_LAYOUT = new GridLayout(Rows, Cols);
        LETTER_LET_LAYOUT.setVgap(10);
        CAPACITY = Rows * Cols;
        
        ImageLoc = imageDirectory;
        ImageProp = imageType;
        
        rack = new ArrayList<>();
        word = inPassword;
        
        
        setBorder(BorderFactory.createEmptyBorder(10, 17, 10, 10));
        setLayout(LETTER_LET_LAYOUT);
        loadRack();
    }
    

    private void loadRack()
    {
        buildRack();
        for (LetterImgs tile : rack)
            add(tile);
    }
    

    private void buildRack()
    {
        StringBuilder wordBuilder = 
                new StringBuilder(word.toLowerCase());
        ArrayList<Character> tiles = new ArrayList<>(); 
        Random rand = new Random();
        int i = 0, j = 0;
        
        while (wordBuilder.length() > 0)
        {
           
            if (!tiles.contains(wordBuilder.charAt(0)))
            {
                tiles.add(wordBuilder.charAt(0));
                i++;
            }
            wordBuilder.deleteCharAt(0);
        }
        
       
        for (; i < CAPACITY; i++)
        {
            Character c = 'a'; 
            do
            {
                c = (char) (rand.nextInt(26) + 'a');
            } while (tiles.contains(c));
            tiles.add(c);
        }
        
        
        for (i = 0; i < CAPACITY; i++)
        {
            j = rand.nextInt(tiles.size());
            rack.add(new LetterImgs(tiles.get(j), 
                    ImageLoc, 
                    ImageProp));
            tiles.remove(j);
        }
    }
    
    public void attachListeners(MouseListener l)
    {
        for (LetterImgs tile : rack)
            tile.addTileListener(l);
    }
    

    public void removeListeners()
    {
        for (LetterImgs tile : rack)
            tile.removeTileListener();
    }
}

