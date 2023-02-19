package br.edu.unoesc.Lista24_SpringDATA.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.Lista24_SpringDATA.model.Aluno;
import br.edu.unoesc.Lista24_SpringDATA.repository.AlunoRepository;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	@Autowired
	private AlunoRepository repositorio; // aqui esta fazendo injeção dependência, não precisa mais instanciar(dar um new)
	
	@GetMapping
	public Iterable<Aluno> listar() {
		return repositorio.findAll();
	}
	
	
	@GetMapping("/find")
	public List<Aluno> findByFiltro(@RequestParam("filtro") String filtro){
		return repositorio.findByFiltro(filtro);
	}
	
	@GetMapping(value= {"/salario", "/salario/{salario}"})
	public List<Aluno> bucasrPorSalario(@PathVariable BigDecimal salario){
		return repositorio.porSalario(salario);
	}
	
	
	@PostMapping()
	public Aluno incluir (@RequestBody Aluno aluno) {
		return repositorio.save(aluno);
	}
	
	@PutMapping("/{id}")
	public Aluno atualizar(@PathVariable("id") Long id,
							@RequestBody Aluno aluno) {
		System.out.println(id);
		aluno.setId(id); //Caso não seja passado o ID no navegador, ele seta o ID		
		aluno = repositorio.save(aluno);
		
		return aluno;
		
	}
	
	@DeleteMapping("/{id}")
	public void deletar (@PathVariable("id") Long id) {
		repositorio.deleteById(id);
	}
	
	
	
	
}
