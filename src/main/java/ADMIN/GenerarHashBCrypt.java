package ADMIN;

import org.mindrot.jbcrypt.BCrypt;

public class GenerarHashBCrypt {
	public static void main(String[] args) {
		String contraseña = "admin123"; // Cambia esto por tu contraseña deseada
		String hash = BCrypt.hashpw(contraseña, BCrypt.gensalt());
		System.out.println("Hash generado: " + hash);
	}
}
