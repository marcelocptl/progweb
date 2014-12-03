<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section>
    <div class="page-header">
        <h1>Catálogo de Filmes</h1>
    </div>

    <div class="row">
        
        <div class="col-lg-2">
            
            <h3>Classificação</h3>
            
            <ul class="nav nav-pills nav-stacked" style="max-width: 300px;">
                <li role="presentation"><a href="#">Lançamento</a></li>
                <li role="presentation"><a href="#">Por Gênero</a></li>
                <li role="presentation"><a href="FilmeController?action=assistidos">Mais Assistidos</a></li>
                <li role="presentation"><a href="FilmeController?action=pretendidos">Mais Pretendidos</a></li>
                <li role="presentation"><a href="FilmeController?action=favoritos">Mais Favoritados</a></li>
                <li role="presentation"><a href="FilmeController?action=melhores">Melhores Classificados</a></li>
            </ul>
            
        </div>
        
        <div class="col-lg-10">
            
            <c:forEach items="${filmes}" var="filme">
                
                <div style="border-bottom: 1px solid #bababa">
                    <h3><a href="FilmeController?action=view&id=${filme.getId()}">${filme.getNome()}</a></h3>
                    <p>
                        <i class="glyphicon glyphicon-calendar"></i> ${filme.getLancamento()}
                        <br/>
                    </p>    
                </div>
                    
            </c:forEach>            
            
        </div>
        
    </div>
        
</section>