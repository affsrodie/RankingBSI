package GesMED.bsi.Testes;

import GesMED.bsi.Entidades.Endereco;
import GesMED.bsi.Entidades.Paciente;
import GesMED.bsi.Entidades.PlanoSaude;
import GesMED.bsi.Entidades.Telefone;
import GesMED.bsi.Repositorios.PacienteRepositorio;



public class TestePaciente {

	public static void main(String[] args) {
		
            Paciente paciente = new Paciente();
            paciente.setID(20180002);
            paciente.setNome("LEONCIO GRANGEIRO CARIOCA");
            paciente.setRG("10835296");
            paciente.setCPF("00402188217");
            paciente.setDataNasc("26/01/1991");
            paciente.setObservacao("Aluno de Sistemas de Informação");
            paciente.setTipoSangue("O+");
            
            Telefone phone = new Telefone();
            phone.setTelefone("32214417");
            phone.setTipo("Residencial");
            paciente.setTelefone(phone);
            
            Endereco end = new Endereco();
            end.setBairro("Santa Maria");
            end.setRua("Rodovia AC-40 KM 12");
            end.setNumero("8264");
            end.setCEP("69909788");
            paciente.setEndereco(end);
            
            PlanoSaude ps = new PlanoSaude();
            ps.setTitulo("Sinteac");
            ps.setDescricao("Sindicato dos Professores do Acre");
            paciente.setPlanoSaude(ps);
            
            PacienteRepositorio pr = new PacienteRepositorio();
            pr.adicionar(paciente);
            pr.encerrar();
		
		
	}

}
