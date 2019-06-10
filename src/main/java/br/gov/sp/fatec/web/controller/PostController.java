package br.gov.sp.fatec.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.model.Post;
import br.gov.sp.fatec.service.PostService;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/post")
@CrossOrigin
public class PostController {
	
	@Autowired
	private PostService postService;

	public void setAnotacaoService(PostService postService) {
		this.postService = postService;
	}
	
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	@JsonView(View.Anotacao.class)
	public ResponseEntity<Post> pesquisarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<Post>(postService.buscarPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.Anotacao.class)
	public ResponseEntity<Collection<Post>> getAll() {
		return new ResponseEntity<Collection<Post>>(postService.todos(), HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@JsonView(View.Anotacao.class)
	public ResponseEntity<Post> salvar(@RequestBody Post post, UriComponentsBuilder uriComponentsBuilder) {
		post = postService.salvar(post);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + post.getId()).build().toUri());
		return new ResponseEntity<Post>(post, responseHeaders, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Post> deletar(@PathVariable ("id") Long id, Post  post) {
		postService.excluir(id);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<Post>(post, responseHeaders, HttpStatus.OK);
	}
	
}
