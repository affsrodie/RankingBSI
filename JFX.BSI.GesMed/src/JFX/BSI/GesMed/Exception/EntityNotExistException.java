package JFX.BSI.Gesmed.Exception;

public class EntityNotExistException extends Exception {

	public EntityNotExistException(String entidade){
		super("Entidade não existe: " + entidade);
	}
}
