package de.demoncore.game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveManager {

	public static <T> T LoadSave(String fileName){
		// Speicherdatei laden

		T deserializedObj = null;

		try (FileInputStream fileIn = new FileInputStream(System.getenv("APPDATA") + "\\" + fileName);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
				deserializedObj = (T) in.readObject();
				
				return deserializedObj;

		} catch (IOException i) {
			
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		
		return null;
	}
	
	public static <T> void SaveToFile(String fileName, T toSave) {
		// Speicherdatei erstellen
		
		try (FileOutputStream fileOut = new FileOutputStream(System.getenv("APPDATA") + "\\" + fileName);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				out.writeObject(toSave);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
}
