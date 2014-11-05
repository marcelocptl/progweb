<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="page-header">
    <ul class="breadcrumb">
        <li><a href="#">Home</a> <span class="divider"></span></li>
        <li><a href="#">Usuários</a> <span class="divider"></span></li>
        <li class="active">Editar Usuário</li>
    </ul>
    <h1>Editar Senha</h1>
</div>
<form class="form-horizontal" action="UserController?action=password&id=<c:out value="${user.getId()}"/>" method="post">
    <div class="form-group">
        <label for="password" class="col-lg-2 control-label">Senha</label>
        <div class="col-lg-10">
            <input type="password" class="form-control" id="password" placeholder="Senha" name="password">
        </div>
    </div>
     <div class="form-group">
        <label for="password" class="col-lg-2 control-label">Confirma Senha</label>
        <div class="col-lg-10">
            <input type="password" class="form-control" id="confirm" placeholder="Confirma Senha" name="confirm">
        </div>
    </div>
  
    <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
           <button type="submit" class="btn btn-default" name="save">Salvar</button>
        </div>
    </div>
</form>