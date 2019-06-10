package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Post;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.PostRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("anotacaoService")
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository anotacaoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public void setAnotacaoRepo(PostRepository anotacaoRepo) {
		this.anotacaoRepo = anotacaoRepo;
	}
	
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Post salvar(Post post) {
		if(post.getUsuario() != null) {
			Usuario usuario = post.getUsuario();
			if(usuario.getId() != null) {
				usuario = usuarioRepo.findById(usuario.getId()).get();
			}
			else {
				usuario = usuarioRepo.save(usuario);
			}
		}
		return anotacaoRepo.save(post);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void excluir(Long idAnotacao) {
		anotacaoRepo.deleteById(idAnotacao);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Post> todos() {
		List<Post> retorno = new ArrayList<Post>();
		for(Post anotacao: anotacaoRepo.findAll()) {
			retorno.add(anotacao);
		}
		return retorno;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Post> buscarPorUsuario(String nome) {
		if(nome == null || nome.isEmpty()) {
			return todos();
		}
		return anotacaoRepo.findByUsuarioNome(nome);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Post buscarPorId(Long idAnotacao) {
		Optional<Post> anotacao = anotacaoRepo.findById(idAnotacao);
		if(anotacao.isPresent()) {
			return anotacao.get();
		}
		return null;
	}

}
