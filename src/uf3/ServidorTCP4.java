package uf3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorTCP4 {

	public static void main (String[] args) throws Exception {
		int parametre = Integer.parseInt(args[0]);

		for (int i = 0; i < parametre; i++) {

			System.out.println("Connexi� " + (i+1));
			
			int numPort = 60000;
			ServerSocket servidor = new ServerSocket(numPort);
			String cadena = "";

			System.out.println("Esperant connexi�... ");
			Socket clientConnectat = servidor.accept();
			System.out.println("Client connectat... ");

			//FLUX DE SORTIDA AL CLIENT
			PrintWriter fsortida = new PrintWriter(clientConnectat.getOutputStream(), true);


			//FLUX D'ENTRADA DEL CLIENT
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(clientConnectat.getInputStream()));

			while ((cadena = fentrada.readLine()) != null) {

				fsortida.println(cadena);
				System.out.println("Rebent: "+cadena);
				if (cadena.equals("*")) break;

			}

			//TANCAR STREAMS I SOCKETS
			System.out.println("Tancant connexi�... ");
			fentrada.close();
			fsortida.close();
			clientConnectat.close();
			servidor.close();	
		}
	}

}