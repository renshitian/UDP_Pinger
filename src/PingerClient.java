package org.UDP_Pinger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class PingerClient {

	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.out.println("Ping with java PingerClient [host] [port]");
			return;
		}
		String host = args[0].toString();
		int port = Integer.parseInt(args[1]);

		// create a Datagram socket which is a UDP connection
		DatagramSocket UDPconnection = new DatagramSocket();
		// set the time for timeout, after 1 second and no receiving, then treat that packet lost 
		UDPconnection.setSoTimeout(1000);
		//get the IP address of host
		InetAddress IPAddress = InetAddress.getByName(host);
		System.out.println("Pinging to " + IPAddress);
		for (int i = 0; i < 10; i++) {
			//send 10 packets to server
			//create buffer for sending data and receiving data
			byte[] sendData = new byte[10];
			byte[] receiveData = new byte[10];

			String pingMessage = "PING " + i;
			//transfer the ping message fron string to byte so that it can be send
			sendData = pingMessage.getBytes();
			//create the datagram packet to be sent
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, port);
			try {
				//send packet
				UDPconnection.send(sendPacket);
				//record sending time
				Date sendTime = new Date();
				long timeSend = sendTime.getTime();
				//create datagram packet to receive
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				//receive the datagram packet
				UDPconnection.receive(receivePacket);
				//record the receiving time
				Date receiveTime = new Date();
				long timeReceive = receiveTime.getTime();

				//get the reply message, here is the "PING Packet#"
				String reply = new String(receivePacket.getData());
				//calculate the RTT
				long RTT = timeReceive - timeSend;
				//print info
				System.out.println(reply + "RTT: " + RTT + " ms");
			} catch (java.net.SocketTimeoutException ex) {
				String reply = "Package Lost";
				System.out.println(reply);
			}
		}
		//close connection
		UDPconnection.close();
	}
}
