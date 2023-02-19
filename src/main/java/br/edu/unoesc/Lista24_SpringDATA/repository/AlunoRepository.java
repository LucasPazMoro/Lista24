package br.edu.unoesc.Lista24_SpringDATA.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.unoesc.Lista24_SpringDATA.model.Aluno;


public interface AlunoRepository extends CrudRepository<Aluno, Long> { // <entidade Aluno, tipo do ID da entidade Aluno>
	 
	public List<Aluno> findByNomeContainingIgnoreCase(String nome); //1-D-a  daria pra usar o like tbm pra pesquisar, mas containing melhor
	
	@Query("select a from Aluno a where a.salario >= :salario") // 1-D-b
	public List<Aluno> porSalario(@Param("salario") BigDecimal salario);
	
	// 1-D-c UPPER transforma maiúscula, lower transformaria em minúscula
	@Query("select a from Aluno a where upper(a.nome) like upper(concat('%', :filtro, '%')) order by nome") // 1-D-c UPPER transforma maiuscula
	public List<Aluno> findByFiltro(@Param("filtro") String filtro);

}
