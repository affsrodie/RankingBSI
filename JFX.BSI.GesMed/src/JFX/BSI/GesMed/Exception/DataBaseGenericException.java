package JFX.BSI.Gesmed.Exception;

import javax.persistence.PersistenceException;

public class DataBaseGenericException extends PersistenceException {

	public DataBaseGenericException(int codigo, String mensagem){
		super("Exceção gerada pelo servidor do banco de dados: [" 
				+ codigo+ "] "
				+ mensagem+"!");
	}
}
