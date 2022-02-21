import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(5080);
			System.out.println("Creado socket de servidor en puerto " + serverSocket.getLocalPort()
					+ ". Esperando conexiones de clientes.\n");

			while (true) {
				Socket servidor = serverSocket.accept();
				System.out.println("Cliente conectado desde " + servidor.getLocalSocketAddress());
				Servidor hilo = new Servidor(servidor);
				hilo.start();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
