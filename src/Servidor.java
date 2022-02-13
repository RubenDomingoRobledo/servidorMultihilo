
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor extends Thread {
	private static int puerto;

	public Servidor(int puerto) {
		Servidor.puerto = puerto;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el puerto de escucha para la conexion con los clientes: ");
        try {
            puerto = Integer.parseInt(scanner.nextLine());
            scanner.close();
            
            if (puerto > 0) {
            	Servidor servidor = new Servidor(puerto);
                servidor.start();
            } 
            else {
                System.out.println("Debe indicar un número de puerto válido que no esté en uso.");
            }
        } 
        catch (Exception e) {
            System.out.println("Debe indicar un número de puerto válido que no esté en uso.");
        }
	}

	public void run() {
		try {
			ServerSocket socketServidor = new ServerSocket(Servidor.puerto);
			System.out.println("Creado socket de servidor en puerto " + puerto + ". Esperando conexiones de clientes.\n");

			String fraseCliente = "";
			
			while (!fraseCliente.equalsIgnoreCase("fin")) {
				Socket connectionSocket = socketServidor.accept();
				System.out.println("Cliente conectado desde " + connectionSocket.getLocalSocketAddress());
				
				for (int i=1; i<=5;i++) {
					BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
					fraseCliente = inFromClient.readLine();
					System.out.println("SERVER fraseCliente: " + fraseCliente);
					
					String fraseModificada = fraseCliente.toUpperCase() + '\n';
					
					DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
					System.out.println("SERVER fraseModificada: " + fraseModificada);
					outToClient.writeBytes(fraseModificada);
					
					if (fraseCliente.equalsIgnoreCase("fin")) {
						System.out.println("El servidor se ha cerrado...");
						socketServidor.close();
					}
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}