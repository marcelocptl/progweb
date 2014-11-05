<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="page-header">
    <ul class="breadcrumb">
        <li><a href="#">Permissões</a> <span class="divider"></span></li>
        <li><a href="#">Módulos</a> <span class="divider"></span></li>
        <li class="active">Novo Módulo</li>
    </ul>
    
    <h1>Cadastro de Módulo</h1>
</div>
<form class="form-horizontal" action="ModuleController?action=add" method="post">

    <div class="form-group">
      <label for="name" class="col-lg-2 control-label">Nome</label>
      <div class="col-lg-10">
        <input type="text" class="form-control" id="name" placeholder="Nome" name="name">
      </div>
    </div>
        
    <div class="form-group">
      <label for="descricao" class="col-lg-2 control-label">Descrição</label>
      <div class="col-lg-10">
        <textarea id="descricao" name="description" class="form-control" placeholder="Descricao" rows="3"></textarea>
      </div>
    </div>
    <div class="form-group">
      <label for="action" class="col-lg-2 control-label">Ações</label>
      <div class="col-lg-3">
          <select id="action" multiple="multiple" name="actions" class="form-control">
            <c:forEach items="${actions}" var="action">
              <option value="<c:out value="${action.getId()}" />"><c:out value="${action.getName()}" /></option>
            </c:forEach>
        </select>
      </div>
    </div>
        
    <div class="form-group">
      <label for="active" class="col-lg-2 control-label">Ativo</label>
      <div class="col-lg-10">
        <input type="checkbox" name="active">
      </div>
    </div>
    <div class="form-group">
      <div class="col-lg-offset-2 col-lg-10">
        <button type="submit" class="btn btn-default" name="save">Salvar</button>
      </div>
    </div>
</form>