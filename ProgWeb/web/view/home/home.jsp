<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url = "/view/helper/search.jsp"/>

<!-- Film Recents -->

<c:set var="count" value="0" scope="page" />
<section>

    <div class="page-header">
        <h1><i class="glyphicon glyphicon-play"></i> Últimos Filmes</h1>
    </div>

    <c:if test="${filmes != NULL}">
    
        <c:forEach items="${filmes}" var="filme" varStatus="loopCounter">
            <c:if test="${filme.getImagem() != NULL}">
                <c:if test="${count % 4 == 0}">
                <div class="row">
                </c:if>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <div class="col-lg-3">
                        <a href="FilmeController?action=view&id=${filme.getId()}"><img class="thumbnail" src="${ filme.getImagem() }"></a>
                    </div> 
                <c:if test="${ (count % 4 == 0) || (loopCounter.count == filmes.size()) }">
                </div>
                </c:if>
            </c:if>
        </c:forEach>
        
    </c:if>
    
    <hr/>

</section>