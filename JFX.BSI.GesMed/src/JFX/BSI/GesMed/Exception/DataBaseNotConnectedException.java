package JFX.BSI.Gesmed.Exception;

public class DataBaseNotConnectedException extends Exception {

	public DataBaseNotConnectedException(String db){
		super("Banco de dados '"+ db + "' não está conectado!");
	}
}
