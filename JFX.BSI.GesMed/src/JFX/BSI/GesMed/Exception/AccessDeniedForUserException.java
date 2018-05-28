package JFX.BSI.Gesmed.Exception;

public class AccessDeniedForUserException extends Exception {

	public AccessDeniedForUserException(String usuario){
		super("Acesso negado para o usuário '"+ usuario + "'!");
	}
}
