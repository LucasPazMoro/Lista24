package br.edu.unoesc.Lista24_SpringDATA;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;

import br.edu.unoesc.Lista24_SpringDATA.model.Aluno;
import br.edu.unoesc.Lista24_SpringDATA.repository.AlunoRepository;

@SpringBootApplication
public class Lista24SpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lista24SpringDataApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AlunoRepository repositorio) {
		return args -> {
			Aluno a = new Aluno(null, "Guibson", new BigDecimal(1300.66), LocalDate.of(2022, 6, 6));
			a = repositorio.save(a);
			Aluno b = new Aluno(null, "Fender Square", new BigDecimal(11300.55), LocalDate.of(1957, 12, 31));
			b = repositorio.save(b);			
			
			repositorio.save(new Aluno(null, "Madruga Chaves", new BigDecimal(5300.55), LocalDate.of(1988, 4, 30)));
			
			try {
				repositorio.deleteById(8L);
			} catch (EmptyResultDataAccessException e) {
				System.out.println("ERRO, ID não encontrado!");
			}
			
			System.out.println("Tchau!");
			
			
			// Teste com a classe optional
			Optional<Aluno> aln = repositorio.findById(1L);
			if (!aln.isPresent()) {
				System.out.println("Aluno não existe");
			} else {
				System.out.println(aln);
			}
			
			a = repositorio.findById(7L).get();
			a.setNome("Paulo Dantas");
			a.setSalario(new BigDecimal("1800.77"));
			a.setNascimento(LocalDate.of(2022, 12, 31 ));
			repositorio.save(a);
			
			for (Aluno alunos : repositorio.findAll()) {
				System.out.println(alunos);
				
			}
			

		};
	}

}
