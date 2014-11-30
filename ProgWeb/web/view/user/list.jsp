<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section>
    <div class="page-header">
        <h1>Lista de Usuários</h1>
    </div>

    <table class="table table-hover">
      <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Email</th>
          <th>Ativo</th>
          <th>Opções</th>
        </tr>
      </thead>
      <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.getId()}" /></td>
                    <td><a href="UserController?action=edit&id=<c:out value="${user.getId()}" />"><c:out value="${user.getName()}" /></a></td>
                    <td><c:out value="${user.getEmail()}" /></td>
                    <td><input type="checkbox" name="active" disabled="disabled" <c:out value="${user.getActive() ? 'checked': ''}"/> ></td>
                    <td>
                        <a href="UserController?action=edit&id=<c:out value="${user.getId()}" />"><button type="button" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-edit"></span></button></a>
                        <a href="UserController?action=password&id=<c:out value="${user.getId()}" />"><button type="button" class="btn btn-success btn-xs"><span class="glyphicon glyphicon-wrench"></span></button></a>
                        <a href="UserController?action=delete&id=<c:out value="${user.getId()}" />"><button type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span></button></a>
                    </td>
                </tr>
          </c:forEach>
      </tbody>
    </table>
</section>