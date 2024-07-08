package de.demoncore.main;

import de.demoncore.utils.Logger;

public class Test {

	public static void main(String[] args) {
		

		double start = System.currentTimeMillis();
		
		Thread thread = new Thread() {
			public void run() {
				int n = 5;
				for(int iteration = 0; iteration < 1000000; iteration++) {
					n = iteration;
					
					double kStartTime = System.currentTimeMillis();
					double k = calc(n);
					double kTime = System.currentTimeMillis() - kStartTime;
					
					double fStartTime = System.currentTimeMillis();
					double f = (double)Math.pow(n, 2);
					double fTime = System.currentTimeMillis() - fStartTime;
					
					if(f == k)
						System.out.println("Basis: " + n + "\t -> " + k + " (" + kTime + "ms)" + "\t -> " + (f) + " (" + fTime + "ms)" + Logger.ANSI_GREEN + "\t\t" + ((f == k) ? " (Test Passed)" : "(Test Failed!)") + Logger.ANSI_RESET);
					else
						System.out.println("Basis: " + n + "\t -> " + k + " (" + kTime + "ms)" + "\t -> " + (f) + " (" + fTime + "ms)" + Logger.ANSI_RED + "\t\t" + ((f == k) ? " (Test Passed)" : "(Test Failed!)") + Logger.ANSI_RESET);
				}
				
				
				double end = System.currentTimeMillis();
				double time = end - start;
				System.out.println(Logger.ANSI_YELLOW + "Test finished (" + time + "ms)" + Logger.ANSI_RESET);
			};
		};
		thread.start();
	}
	
	
	static double calc(int n) {
		double k = 0;
		
		for(int i = 1; i <= n; i++) {
			k =k+ 2*i-1;
		}
		
		return k;
	}
	
}
