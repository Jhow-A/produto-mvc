<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>

    <title>Produtos - Cadastro</title>
    
    <spring:url value="/resources/css" var="css"/>
    <spring:url value="/resources/js" var="js"/>
    
    <c:set value="${pageContext.request.contextPath}" var="contextPath"/>
    
    <link href="${css}/bootstrap.css" rel="stylesheet">
    <link href="${css}/small-business.css" rel="stylesheet">

</head>

<body>
    
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${contextPath}/produto">Produtos</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="well">
					
					<h2>Produto</h2>
					
					<form:form modelAttribute="produtoModel"  action="${contextPath}/produto" method="post">					
						<div class="form-group">
							<label class="control-label" for="nome">Nome:</label>
							<form:input path="nome" type="text" name="nome" id="nome" value="" class="form-control" maxlength="50" size="50" />
							<font color="red"><form:errors path="nome"></form:errors></font><br/>
                        </div>
                        <div class="form-group">
							<label class="control-label" for="sku">SKU:</label>
							<form:input path="sku" id="sku" name="sku" value="" class="form-control" maxlength="50" size="50" />
							<font color="red"><form:errors path="sku"></form:errors></font><br/>
						</div>
						
						<div class="form-group">
							<label class="control-label" for="categoria">Categoria:</label>
							<form:select path="categoria.idCategoria" class="form-control">
								<form:options items="${categorias}" itemLabel="nomeCategoria" itemValue="idCategoria"/>
							</form:select>
						</div>
						
						<div class="form-group">
							<label class="control-label" for="descricao">Descrição:</label>
							<form:textarea path="descricao" class="form-control" name="descricao" rows="4" cols="100"></form:textarea>
							<font color="red"><form:errors path="descricao"></form:errors></font><br/>
						</div>
						
						<div class="form-group">
							<label class="control-label" for="preco">Preço:</label>
							<form:input path="preco"  type="text" id="preco" name="preco" class="form-control" />
							<font color="red"><form:errors path="preco"></form:errors></font><br/>
						</div>
						
						<div class="form-group">
							<label class="control-label" for="caracteristica">Características:</label>
							<form:textarea path="caracteristicas" id="caracteristica" class="form-control" name="caracteristicas" rows="4" cols="100"></form:textarea >
							<font color="red"><form:errors path="caracteristicas"></form:errors></font><br/>
						</div>
						<hr>
						
						<a class="btn btn-default btn-lg" href="${contextPath}/produto">Cancelar</a>
						<button type="submit" class="btn btn-primary btn-lg">Gravar</button>
                            
                        <br>
                        <br>
					</form:form>									
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="${js}/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${js}/bootstrap.min.js"></script>

</body>
</html>