<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url = "/view/helper/search.jsp"/>

<!-- Film Recents -->

<section>

    <div class="page-header">
        <h1><i class="glyphicon glyphicon-play"></i> Últimos Filmes</h1>
    </div>

    <c:if test="${filmes != NULL}">
    <div class="row">
        
        <c:forEach items="${filmes}" var="filme">
            <c:if test="${filme.getImagem() != NULL}">
            <div class="col-lg-3">
                <a href="FilmeController?action=view&id=${filme.getId()}"><img class="thumbnail" src="${ filme.getImagem() }"></a>
            </div>    
            </c:if>
        </c:forEach>
        
    </div>
    </c:if>
    
    <hr/>

</section>