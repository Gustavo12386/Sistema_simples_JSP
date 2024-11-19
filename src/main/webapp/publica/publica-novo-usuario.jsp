<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/resources/bootstrap-5.0.2-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.7.0-dist/jquery-3.7.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-5.0.2-dist/js/bootstrap.min.js"></script>
</head>
<body>
  <jsp:include page="/publica/publica-nav.jsp" />
  <div class="container">
   <div class="row">
     <div class="col">
        <h2>Cadastro usuário</h2>
        
        <c:if test="${mensagem != null}">
				 	<div class="alert alert-success alert-dismissible fade show">
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					<span><c:out value="${mensagem}" /></span>
		     		</div>
		</c:if>
        
        <form action="${pageContext.request.contextPath}/publica?acao=inserir" method="post">
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Nome</label>
		    <input type="text" class="form-control" name="nome">		  
		  </div>
		   <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">CPF</label>
		    <input type="text" class="form-control" name="cpf">		  
		  </div>
		   <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Nascimento</label>
		    <input type="text" class="form-control" name="nascimento">		  
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Email</label>
		    <input type="email" class="form-control" name="email">		  
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Login</label>
		    <input type="text" class="form-control" name="login">		  
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">Senha</label>
		    <input type="password" class="form-control" name="password">
		  </div>
		  
		  <input class="btn btn-primary" type="submit" value="Gravar">
		</form>
     </div>
   </div>
  </div> 
</body>
</html>