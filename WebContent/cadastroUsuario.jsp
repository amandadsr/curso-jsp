<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usu�rio</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
<center>
	<h1>Cadastro de Usu�rio</h1>
	<h3 style="color:red; ">${msg}</h3>
	<h3 style="color:orange; ">${msgsenha}</h3>
	<h3 style="color:blue; ">${msgcad}</h3>
</center>


	<form action="salvarUsuario" method="post" id="formUser">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>C�digo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}"></td>
					</tr>
					
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}"></td>
					</tr>
					
					<tr>
						<td>Fone:</td>
						<td><input type="text" id="fone" name="fone"
							value="${user.fone}"></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input type="submit" value="Cancelar" onclick = "document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
					</tr>

				</table> 
			</li>
		</ul>
	</form>

<div class="container">
	<table class="responsive-table">
	 <caption>Usu�rios cadastrados</caption> 
	 <tr>
	 	<th>Id</th>
	 	<th>Login</th>
	 	<th>Nome</th>
	 	<th>Fone</th>
	 	<th>Excluir</th>
	 	<th>Editar</th>
	 </tr>
		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td style="width: 100px"><c:out value="${user.id}">
					</c:out></td>
				<td style="width: 100px"><c:out value="${user.login}">
					</c:out></td>
				<td><c:out value="${user.nome}"></c:out></td>
				<td><c:out value="${user.fone}"></c:out></td>

				<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img src="resources/img/excluir.png" alt="Excluir" title="Excluir" width="20px" heigth="20px"></a></td>
				<%-- servlet?parametro&usuario--%>
				<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img src="resources/img/editar.jpg" alt="Editar" title="Editar" width="22px" heigth="22px"></a></td>
				<%-- servlet?parametro&usuario--%>

			</tr>
		</c:forEach>
	</table> 
</div>

</body>
</html>