package br.com.fiap.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.fiap.dao.GenericDao;
import br.com.fiap.entity.Usuario;

@ManagedBean
@RequestScoped
@SessionScoped
public class UsuariosBean {
	
	private Usuario usuario;
	
	public UsuariosBean(){
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String Login(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		
		GenericDao<Usuario> dao = new GenericDao<>(Usuario.class);
		
		try {
			request.login(this.usuario.getNome(), this.usuario.getSenha());
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Falha ao Logar"));
            return "login";
		}
		return "menu";	
	}
}
