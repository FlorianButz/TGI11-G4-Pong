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
import de.demoncore.utils.Logger;

public class Sprite {
	
	private String filepath;
	private BufferedImage texture;
	
	InputStream imageStream;
	
	public Sprite(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	
	public Sprite load() {

		if(texture != null){
            texture.flush();
            texture = null;
        }
		
		try {
			
			BufferedImage bufferedImage = ImageIO.read(imageStream);
			texture = bufferedImage;

//			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//	        GraphicsDevice gd = ge.getDefaultScreenDevice();
//	        GraphicsConfiguration gc = gd.getDefaultConfiguration();
//			
//			VolatileImage vImage = gc.createCompatibleVolatileImage(bufferedImage.getWidth(), bufferedImage.getHeight(), Transparency.TRANSLUCENT);
//
//	        do {
//	            if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
//	                vImage = gc.createCompatibleVolatileImage(bufferedImage.getWidth(), bufferedImage.getHeight(), Transparency.TRANSLUCENT);
//	            }
//
//	            Graphics2D g = vImage.createGraphics();
//	            g.setComposite(AlphaComposite.Src);
//	            g.drawImage(bufferedImage, 0, 0, null);
//	            g.dispose();
//	        } while (vImage.contentsLost());
			
	        texture = bufferedImage;
	        
	        imageStream.close();
	        
		}catch(Exception e) {
			Logger.logError("Konnte Bilddatei nicht laden. " + e.getMessage() + " : " + filepath, e);
		}
		
		return this;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
}
