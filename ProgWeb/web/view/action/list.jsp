<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section>
    <div class="page-header">
        <h1>Lista de Ações</h1>
    </div>

    <table class="table table-hover">
        <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Descrição</th>
                <th>Ativo</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${actions}" var="action">
                <tr>
                    <td><c:out value="${action.getId()}" /></td>
                    <td><a href="ActionController?action=edit&id=<c:out value="${action.getId()}" />"><c:out value="${action.getName()}" /></a></td>
                    <td><c:out value="${action.getDescription()}" /></td>
                    <td><input type="checkbox" name="active" disabled="disabled" <c:out value="${action.getActive() ? 'checked': ''}"/> ></td>
                    <td>
                        <a href="ActionController?action=edit&id=<c:out value="${action.getId()}" />"><button type="button" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-edit"></span></button></a>
                        <a href="ActionController?action=delete&id=<c:out value="${action.getId()}" />"><button type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span></button></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>    
