package JFX.BSI.GesMed.Exception;

import javax.persistence.PersistenceException;


public class DataBaseConstraintException extends PersistenceException {

	public DataBaseConstraintException(String mensagem){
            super("Não é possível excluir ou atualizar uma linha pai: uma restrição de chave estrangeira : Falha["+mensagem+"]!*/");
	}
}
