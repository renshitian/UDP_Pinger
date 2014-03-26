package org.UDP_Pinger;

import java.util.Date;

public class testASD {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Date currentTime = new Date();

	
		Date currentTime2 = new Date();
		long b = currentTime2.getTime();
		Thread.sleep(1000);
		long a = currentTime.getTime();
		String timeStamp = 	Long.toString(a);
		String timeStamp2 = Long.toString(b);
		System.out.println("current time"+currentTime);
		System.out.println("current time2"+currentTime2);
		System.out.println(timeStamp);
		System.out.println(timeStamp2);
		long c = b-a;
		System.out.println("time difference "+c);
	}

}
