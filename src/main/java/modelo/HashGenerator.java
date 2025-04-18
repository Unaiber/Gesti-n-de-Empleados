package modelo;

import org.mindrot.jbcrypt.BCrypt;

public class HashGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   String password = "admin123";  // La contraseña original
	        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
	        System.out.println("Hash generado: " + hashed);
	}

}
