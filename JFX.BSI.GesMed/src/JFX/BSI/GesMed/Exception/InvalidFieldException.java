package JFX.BSI.Gesmed.Exception;

public class InvalidFieldException extends Exception {

	public InvalidFieldException(String entidiade, String listaDeCampos){
		super("Campos inválidos para entidade '" + entidiade + "': " + listaDeCampos);
	}
}
