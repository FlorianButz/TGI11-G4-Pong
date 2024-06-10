package de.demoncore.game;

import java.text.SimpleDateFormat;

public class Logger {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static void logMessage(String message, Object caller){
		System.out.println(ANSI_GREEN + "[Log][" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "][From: " + caller.getClass().getSimpleName() + "] " + message);
	}
	
	public static void logError(String message, Object caller){
		System.out.println(ANSI_RED + "[Error][" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "][From: " + caller.getClass().getSimpleName() + "] " + message);
	}
	
	public static void logWarning(String message, Object caller){
		System.out.println(ANSI_YELLOW + "[Warning][" + new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())
				+ "][From: " + caller.getClass().getSimpleName() + "] " + message);
	}
}
