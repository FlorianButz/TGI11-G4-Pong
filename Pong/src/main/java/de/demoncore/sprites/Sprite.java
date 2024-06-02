package de.demoncore.sprites;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import de.demoncore.gui.Gui;

public class Sprite {
	
	private String filepath;
	private VolatileImage texture;
	
	InputStream imageStream;
	
	public Sprite(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	
	public Sprite Load() {

		if(texture != null){
            texture.flush();
            texture = null;
        }
		
		try {
		
			BufferedImage bufferedImage = ImageIO.read(imageStream);

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        GraphicsDevice gd = ge.getDefaultScreenDevice();
	        GraphicsConfiguration gc = gd.getDefaultConfiguration();
			
			VolatileImage vImage = gc.createCompatibleVolatileImage(bufferedImage.getWidth(), bufferedImage.getHeight(), Transparency.TRANSLUCENT);

	        do {
	            if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
	                vImage = gc.createCompatibleVolatileImage(bufferedImage.getWidth(), bufferedImage.getHeight(), Transparency.TRANSLUCENT);
	            }

	            Graphics2D g = vImage.createGraphics();
	            g.setComposite(AlphaComposite.Src);
	            g.drawImage(bufferedImage, 0, 0, null);
	            g.dispose();
	        } while (vImage.contentsLost());
			
	        texture = vImage;
	        
	        imageStream.close();
	        
		}catch(IOException e) {
			System.err.println("Konnte Bilddatei nicht laden. " + e.getLocalizedMessage() + " : " + filepath);
		}
		
		return this;
	}
	
	public VolatileImage GetTexture() {
		return texture;
	}
}
