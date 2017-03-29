package com.mercacortex.test_servertcp;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//ERR-LOG
//Broadcast Address: Enri es tonto y no se acuerda de que llamar al X.X.X.0 o al X.X.X.255
//envía paquetes a todas las direcciones que cumplan con la máscara X.X.X.*
//La diferencia entre 0 y 255 es que 255 no es reenviada por el router.


class ServidorTCP extends Thread {

    private int contador = 0;
    private Context context;

    private void listen() {
		String key = "0";
		int PORT = 5555;
		byte[] mensajeEnBytes = key.getBytes(); 
		
		try {
			DatagramSocket dSocket = new DatagramSocket(PORT);
			DatagramPacket dPacket = new DatagramPacket(mensajeEnBytes, mensajeEnBytes.length, InetAddress.getByName("localhost"), PORT);
			dSocket.setSoTimeout(1000);
            Toast.makeText(context, "Escuchando...", Toast.LENGTH_SHORT).show();
			dSocket.receive(dPacket);
            Toast.makeText(context, dPacket.getPort() +"; " + dPacket.getAddress(), Toast.LENGTH_SHORT).show();
            dSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	@Override
	public void run() {
		while (contador++ < 10) {
			listen();
			try {
				sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

    private ServidorTCP(Context context) {
        this.context = context;
    }

    static void load(Context context) {
		ServidorTCP servidorTCP = new ServidorTCP(context);
		//EmisorUDP emisorUDP = new EmisorUDP(context, txvMain);
		servidorTCP.start();
		//emisorUDP.start();
		try {
			servidorTCP.join();
            //emisorUDP.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
