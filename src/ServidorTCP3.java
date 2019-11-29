import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP3 extends Thread{
public static void main (String[] args) throws Exception {
		
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";
		
		/*Thread fil1 = new Thread("fil1");
		Thread fil2 = new Thread("fil2");
		Thread fil3 = new Thread("fil3");*/
		System.out.println("Esperant connexió... ");
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
		System.out.println("Tancant connexió... ");
		fentrada.close();
		fsortida.close();
		clientConnectat.close();
		servidor.close();
		
	}
}
