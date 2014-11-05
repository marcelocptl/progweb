<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="page-header">
    <ul class="breadcrumb">
        <li><a href="#">Permissões</a> <span class="divider"></span></li>
        <li><a href="#">Módulos</a> <span class="divider"></span></li>
        <li class="active">Listar Módulos</li>
    </ul>
   
    <h1>Lista de Módulos</h1>
</div>

<table class="table table-hover">
  <thead>
    <tr>
      <th>#</th>
      <th>Name</th>
      <th>Descrição</th>
      <th>Ativo</th>
      <th>Opções</th>
    </tr>
  </thead>
  <tbody>
        <c:forEach items="${modules}" var="module">
            <tr>
                <td><c:out value="${module.getId()}" /></td>
                <td><a href="ModuleController?action=edit&id=<c:out value="${module.getId()}" />"><c:out value="${module.getName()}" /></a></td>
                <td><c:out value="${module.getDescription()}" /></td>
                <td><input type="checkbox" name="active" disabled="disabled" <c:out value="${module.getActive() ? 'checked': ''}"/> ></td>
                <td>
                    <a href="ModuleController?action=edit&id=<c:out value="${module.getId()}" />"><button type="button" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-edit"></span></button></a>
                    <a href="ModuleController?action=delete&id=<c:out value="${module.getId()}" />"><button type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span></button></a>
                </td>
            </tr>
      </c:forEach>
  </tbody>
</table>
