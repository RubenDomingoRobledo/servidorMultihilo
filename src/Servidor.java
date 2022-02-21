
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Servidor extends Thread {
	private Socket socket;

	public Servidor(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String fraseCliente = inFromClient.readLine();
				System.out.println("SERVER fraseCliente: " + fraseCliente);
				
				String fraseModificada = fraseCliente.toUpperCase() + '\n';
					
				DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
				System.out.println("SERVER fraseModificada: " + fraseModificada);
				outToClient.writeBytes(fraseModificada);				
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}