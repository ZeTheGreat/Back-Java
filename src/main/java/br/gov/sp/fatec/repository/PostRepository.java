package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	
	public List<Post> findByUsuarioNome(String nome);

}
