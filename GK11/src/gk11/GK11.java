/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

public class GK11 extends JPanel {

    private BufferedImage image;
    private int counter;
    private double result;

    public GK11() {
        super();
        JFrame frame = new JFrame("Ile % zielonego");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        try {
            image = ImageIO.read(new File("pb.bmp"));
        } catch (IOException ex) {
           Logger.getLogger(GK11.class.getName()).log(Level.SEVERE, null, ex);
        }
        JTextField text= new JTextField(Double.toString(computing()*100 ) + "%");
        JScrollPane sp = new JScrollPane(this);
        text.setEditable(false);
        frame.add(text, BorderLayout.NORTH);
        frame.add(sp, BorderLayout.CENTER);
        computing();
        frame.pack();
        frame.setVisible(true);
    }

    private double computing() {
        counter = 0;
        int backgroundCounter = 0;
        Color background = new Color(image.getRGB(0, 0));
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color c = new Color(image.getRGB(j, i));
                if(c.getRGB() == background.getRGB()){
                    backgroundCounter++;
                    continue;
                }
                if (c.getGreen() > c.getRed() && c.getGreen() > c.getBlue()) {
                    image.setRGB(j, i, Color.green.getRGB());
                    counter++;
                }
            }
        }
        return result = ((double)counter)/((double)(image.getHeight()*image.getWidth())-backgroundCounter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, null);
    }
    
    public static void main(String[] args) {
      new   GK11();
    }
}


    
   

