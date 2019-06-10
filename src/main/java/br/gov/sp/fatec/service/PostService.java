package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Post;

public interface PostService {
	
	public Post salvar(Post autorizacao);
	
	public void excluir(Long idAnotacao);
	
	public List<Post> todos();
	
	public List<Post> buscarPorUsuario(String nome);
	
	public Post buscarPorId(Long idAnotacao);

}
