import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String args[]) {
		try {
			Scanner scanner = new Scanner(System.in);
			Socket clientSocket = new Socket("localhost", 5080);

			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			for (int i = 1; i <= 100; i++) {
				System.out.println("\nCLIENTE: texto a enviar al servidor: ");
				String frase = scanner.nextLine();

				System.out.println("CLIENTE: Palabra recogida por teclado: " + frase);
				outToServer.writeBytes(frase + '\n');

				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String fraseModificada = inFromServer.readLine();
				System.out.println("Palabra recibida del Servidor: " + fraseModificada);
			}
			scanner.close();
			clientSocket.close();

		} catch (UnknownHostException e) {
			System.out.println("Servidor no encontrado: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al conectar con el servidor " + e.getMessage());
			e.printStackTrace();
		}
	}
}
