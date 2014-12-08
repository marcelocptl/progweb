<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url = "/view/helper/search.jsp"/>

<section>
    <div class="page-header">
        <h1>Resultado da Busca</h1>
    </div>

    <c:forEach items="${filmes}" var="filme">

        <div class="row">

            <div class="col-lg-2">
                
            <c:choose>
                <c:when test="${ (filme.getImagem() != NULL) && !(filme.getImagem().isEmpty()) }">
                    <a href="FilmeController?action=view&id=${filme.getId()}"><img class="thumbnail" src="${filme.getImagem()}" alt="Poster do Filme ${filme.getNome()}"></a>
                </c:when>

                <c:otherwise>
                   <a href="FilmeController?action=view&id=${filme.getId()}"><img class="thumbnail" src="bootstrap/img/poster.jpg" alt="Filme sem Poster"></a>
                </c:otherwise>
            </c:choose>                
                
               
            </div>
            <div class="col-lg-10">
                <h2><a href="FilmeController?action=view&id=${filme.getId()}">${filme.getNome()}</a></h2>
                <p>

                    <i class="glyphicon glyphicon-calendar"></i> ${filme.getFormatLancamento()} &nbsp;&nbsp;

                    <c:if test="${filme.getQtdFav() != NULL}">
                        <i class="glyphicon glyphicon-star-empty"></i> ${filme.getQtdFav()} &nbsp;&nbsp;
                    </c:if>  

                    <c:if test="${filme.getQtdPret() != NULL}">
                        <i class="glyphicon glyphicon-play"></i> ${filme.getQtdPret()} &nbsp;&nbsp;
                    </c:if> 

                    <c:if test="${filme.getQtdAss() != NULL}">
                        <i class="glyphicon glyphicon-eye-open"></i> ${filme.getQtdAss()} &nbsp;&nbsp;
                    </c:if> 

                    <c:if test="${filme.getQtdNota() != NULL}">
                        <i class="glyphicon glyphicon-stats"></i> ${filme.getQtdNota()}
                    </c:if> 
 
                    <br/><br/>
                    ${filme.getSinopse()}
                </p>
                
            </div>       

        </div>  

        <hr/>        
        
    </c:forEach>
      
    
</section>