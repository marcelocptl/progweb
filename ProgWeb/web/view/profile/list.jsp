<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="page-header">
    <ul class="breadcrumb">
        <li><a href="#">Controle de Acesso</a> <span class="divider"></span></li>
        <li><a href="#">Perfils</a> <span class="divider"></span></li>
        <li class="active">Listar Perfils</li>
    </ul>
   
    <h1>Lista de Perfis</h1>
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
        <c:forEach items="${profiles}" var="profile">
            <tr>
                <td><c:out value="${profile.getId()}" /></td>
                <td><a href="ProfileController?action=edit&id=<c:out value="${profile.getId()}" />"><c:out value="${profile.getName()}" /></a></td>
                <td><c:out value="${profile.getDescription()}" /></td>
                <td><input type="checkbox" name="active" disabled="disabled" <c:out value="${profile.getActive() ? 'checked': ''}"/> ></td>
                <td>
                    <a href="ProfileController?action=edit&id=<c:out value="${profile.getId()}" />"><button type="button" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-edit"></span></button></a>
                    <a href="ProfileController?action=delete&id=<c:out value="${profile.getId()}" />"><button type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span></button></a>
                    <a href="PermissionController?action=permission"><button type="button" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-check"></span></button></a>
                </td>
            </tr>
      </c:forEach>
  </tbody>
</table>
