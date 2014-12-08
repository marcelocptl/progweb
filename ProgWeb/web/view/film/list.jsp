<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section>
    <div class="page-header">
        <h1>Catálogo de Filmes</h1>
    </div>

    <div class="row">

        <div class="col-lg-3">

            <h3><i class="glyphicon glyphicon-filter"></i> Filtrar</h3>

            <ul class="nav nav-pills nav-stacked" style="max-width: 300px;">
                <li role="presentation"><a href="FilmeController?action=recentes">Lançamento</a></li>
                <li role="presentation"><a href="FilmeController?action=generos">Por Gênero</a></li>
                <li role="presentation"><a href="FilmeController?action=assistidos">Mais Assistidos</a></li>
                <li role="presentation"><a href="FilmeController?action=pretendidos">Mais Pretendidos</a></li>
                <li role="presentation"><a href="FilmeController?action=favoritos">Mais Favoritados</a></li>
                <li role="presentation"><a href="FilmeController?action=melhores">Melhores Classificados</a></li>
            </ul>

        </div>

        <div class="col-lg-9">

            <c:if test="${generos != null}">

                <h2 class="pull-left">${title} <small>Gênero</small></h2>
                <ul class="nav navbar-nav navbar-right">
                    <li id="fat-menu" class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" role="button" aria-expanded="false">
                            Gêneros
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                               
                            <c:forEach items="${generos}" var="genero">
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="FilmeController?action=generos&genderId=${genero.id}">${genero.name}</a></li>
                            </c:forEach>
                            
                        </ul>
                    </li>
                </ul>

                <div class="clearfix"></div>
                
            </c:if>

            <c:forEach items="${filmes}" var="filme">

                <div style="border-bottom: 1px solid #bababa">
                    <h3><a href="FilmeController?action=view&id=${filme.getId()}">${filme.getNome()}</a></h3>
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

                        <br/>
                    </p>    
                </div>

            </c:forEach>            

        </div>

    </div>

</section>