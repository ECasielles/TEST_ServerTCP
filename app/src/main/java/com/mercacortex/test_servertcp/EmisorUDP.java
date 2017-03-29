package com.mercacortex.test_servertcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class EmisorUDP extends Thread {

	private int contador = 0;

	private void call() {
		String key = "0";
		int PORT = 5555;
		byte[] mensajeEnBytes = key.getBytes();

		try {
			DatagramSocket dSocket = new DatagramSocket();
			DatagramPacket dPacket = new DatagramPacket(mensajeEnBytes, mensajeEnBytes.length, InetAddress.getByName("192.168.1.255"), PORT);
			dSocket.send(dPacket);
			dSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while (contador++ < 10) {
			call();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
