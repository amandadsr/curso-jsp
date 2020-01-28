package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user); // deletou, eu tenho que carregar os usuarios e jogar pra mesma pagina conforme
											// abaixo
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("msgcad", "Usuário deletado com sucesso!");
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String fone = request.getParameter("fone");

			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0); // se id for diferente de vazio, faz a conversão. Se
																	// não, seta como 0
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setFone(fone);

			try {
				if (id.isEmpty() || id == null && !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg", "Login já existe para outro usuário!"); // aparecer a msg para o usuário
				} else {
					if (id == null || id.isEmpty() && daoUsuario.validarLogin(login)) {
						daoUsuario.salvar(usuario);
						request.setAttribute("msgcad", "Usuário cadastrado com sucesso!");
					} else if (id != null || !id.isEmpty()) {
						if (!daoUsuario.validarLoginUpdate(login, id)) {
							request.setAttribute("msg", "Login já existe para outro usuário!");
						} else {
							daoUsuario.atualizar(usuario);
							request.setAttribute("msgcad", "Usuário atualizado com sucesso!");
						}
					}
				}
				if (senha.length() < 3) {
					request.setAttribute("msgsenha", "Senha menor que 3 caracteres!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
