<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>

<title>Categorias - Listagem</title>

<spring:url value="/resources/css" var="css" />
<spring:url value="/resource/js" var="js" />

<c:set value="${pageContext.request.contextPath}" var="contextPath" />

<link href="${css}/bootstrap.css" rel="stylesheet">
<link href="${css}/small-business.css" rel="stylesheet">

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/produto">Produtos</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<h1>Categorias</h1>

				<p class="toolbar">
					<a class="create btn btn-default" href="${contextPath}/categoria/form?page=categoria-nova">Nova
						Categoria</a>

					<c:if test="${not empty messages}">
						<h3 class="alert alert-warning">${messages}</h3>
					</c:if>
				</p>

				<table class="table table-striped" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<th data-field="name">Nome</th>
							<th class="actions" width="220">Ações</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${categorias}" var="categoria">

							<tr>
								<td>${categoria.nomeCategoria}</td>

								<td class="actions"><form:form
										action="${contextPath}/categoria/${categoria.idCategoria}"
										method="delete">

										<a class="btn btn-success btn-xs"
											href="${contextPath}/categoria/${categoria.idCategoria}">Detalhes</a>
										<a class="btn btn-warning btn-xs"
											href="${contextPath}/categoria/form?page=categoria-editar&id=${categoria.idCategoria}">Editar</a>
										<input type="submit" class="btn btn-danger btn-xs"
											value="Excluir" />
									</form:form></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<hr>
	</div>

	<!-- jQuery -->
	<script src="${js}/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${js}/bootstrap.min.js"></script>

</body>
</html>