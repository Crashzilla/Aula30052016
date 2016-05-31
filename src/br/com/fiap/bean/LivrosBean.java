package br.com.fiap.bean;

import java.io.InputStream;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Livro;

@ManagedBean
@RequestScoped
public class LivrosBean {

	private Livro livro;
	private Part figura; 

	public LivrosBean(){
		livro = new Livro();
	}
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Part getFigura() {
		return figura;
	}
	public void setFigura(Part figura) {
		this.figura = figura;
	} 
		
	public String incluirLivro(){
		try {
			InputStream inputStream = figura.getInputStream();
			byte[] imagem = new byte[(int)figura.getSize()];
			inputStream.read(imagem, 0, (int)figura.getSize());
			livro.setImagem(imagem);

			GenericDao<Livro> dao = new GenericDao<Livro>(Livro.class);
			dao.adicionar(livro); 

			return "sucesso"; //dispatching para sucesso.xhtml
		} catch (Exception e) {
			return "erro"; //dispatching para erro.xhtml
		}
	} 
	
	public List<Livro> getListaLivros(){
		GenericDao<Livro> dao = new GenericDao<Livro>(Livro.class);
		return dao.listar();
	} 
}
