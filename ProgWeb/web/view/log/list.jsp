<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section>
    <div class="page-header">
        <h1>Registro de Log</h1>
    </div>

    <table class="table table-hover">
        <thead>
            <tr>
                <th>Data</th>
                <th>Módulo</th>
                <th>Ação</th>
                <th>Usuário</th>
                <th>Mensagem</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${logs}" var="log">
                <tr>
                    <td><c:out value="${log.getDate()}" /></td>
                    <td><c:out value="${log.getModule()}" /></td>
                    <td><c:out value="${log.getAction()}" /></td>
                    <td><c:out value="${log.getUser()}" /></td>                
                    <td><c:out value="${log.getMessage()}" /></td> 
                </tr>
            </c:forEach>

        </tbody>
    </table>
</section>    
