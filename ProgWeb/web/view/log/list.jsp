<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="page-header">
    <ul class="breadcrumb">
        <li><a href="#">Sistema de Log</a> <span class="divider"></span></li>
        <li class="active">Log</li>
    </ul>

    <h1>Registro de Log</h1>
</div>

<table class="table table-hover">
    <thead>
        <tr>
            
            <th>Profile</th>
            <th>Módulo</th>
            <th>Ação</th>
        </tr>
    </thead>
    <tbody>

        <c:forEach items="${logs}" var="log">
            <tr>
                <td><c:out value="${log.getDate()}" /></td>
                <td><c:out value="${log.getModule()}" /></td>
                <td><c:out value="${log.getAction()}" /></td>
                <td><c:out value="${log.getUser().getName()}" /></td>                
                
            </tr>
        </c:forEach>

    </tbody>
</table>
