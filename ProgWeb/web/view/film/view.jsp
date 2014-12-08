<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="count" value="0" scope="page" />

<section>

    <div class="pull-right">
        <c:if test="${ (_permissions != NULL) && (_permissions.check(_user.getProfile(), 'Filme', 'check')) }">
            <form method="post" action="FilmeController?action=check&imdb=${filme.getId()}">

                <label for="favorito" class="control-label">Favorito</label>
                <input type="checkbox" name="favorito" <c:out value="${checks.isFavorito() ? 'checked': ''}"/> >

                <label for="pretendido" class="control-label">Pretendo Ver</label>
                <input type="checkbox" name="pretendido" <c:out value="${checks.isPretende_ver() ? 'checked': ''}"/> >

                <label for="assistido" class="control-label">Assisti</label>
                <input type="checkbox" name="assistido" <c:out value="${checks.isAssistiu() ? 'checked': ''}"/> >

                <select id="nota" name="nota" class="form-control">
                    <option value="-1">Dê uma nota</option>
                    <c:forEach var="i" begin="1" end="10">

                        <c:choose>
                            <c:when test="${ checks.getNota() == i }">
                                <option value="${i}" selected="selected">${i}</option>
                            </c:when>

                            <c:otherwise>
                                <option value="${i}">${i}</option>
                            </c:otherwise>
                        </c:choose>                          

                    </c:forEach>
                </select>    

                <div class="center">
                    <button type="submit" class="btn btn-primary" name="save"><i class="glyphicon glyphicon-ok"></i> Confirmar</button>
                </div>

            </form>
        </c:if>    
    </div>
    <div class="clearfix"></div>

    <div class="center">

        <c:choose>
            <c:when test="${ (filme.getImagem() != NULL) && !(filme.getImagem().isEmpty()) }">
                <img src="${filme.getImagem()}" alt="Poster do Filme ${filme.getNome()}">
            </c:when>

            <c:otherwise>
                <img src="bootstrap/img/poster.jpg" alt="Filme sem Poster">
            </c:otherwise>
        </c:choose>         

    </div>

    <c:if test="${filme.getNome() != NULL}">      
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><h2>Título:</h2></div>
            <div class="col-md-9"><h2>${filme.getNome()}</h2></div>
        </div>    
    </c:if>

    <c:if test="${filme.getSinopse() != NULL}">
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><strong>Sinopse:</strong></div>
            <div class="col-md-9"><p>${filme.getSinopse()}</p></div>
        </div>
    </c:if>

    <c:if test="${filme.getDiretores() != NULL}">
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><strong>Diretor(es):</strong></div>
            <div class="col-md-9">
                <p>
                    <c:forEach items="${filme.getDiretores()}" var="diretor">

                        <c:choose>
                            <c:when test="${count != 0}">
                                , ${diretor}
                            </c:when>

                            <c:otherwise>
                                ${diretor}

                                <c:set var="count" value="${count + 1}" scope="page"/>
                            </c:otherwise>
                        </c:choose>                                         

                    </c:forEach>
                </p>
            </div>
        </div>    
    </c:if>

    <c:if test="${filme.getGeneros() != NULL}">
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><strong>Gênero(s):</strong></div>
            <div class="col-md-9">
                <c:set var="count" value="0" scope="page" />
                <p>
                    <c:forEach items="${filme.getGeneros()}" var="genero">

                        <c:choose>
                            <c:when test="${count != 0}">
                                , ${genero}
                            </c:when>

                            <c:otherwise>
                                ${genero}

                                <c:set var="count" value="${count + 1}" scope="page"/>
                            </c:otherwise>
                        </c:choose>    

                    </c:forEach>
                </p>
            </div>
        </div>             
    </c:if>

    <c:if test="${filme.getAtores() != NULL}">
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><strong>Ator(es):</strong></div>
            <div class="col-md-9">
                <c:set var="count" value="0" scope="page" />

                <p>
                    <c:forEach items="${filme.getAtores()}" var="ator">

                        <c:choose>
                            <c:when test="${count != 0}">
                                , ${ator}
                            </c:when>

                            <c:otherwise>
                                ${ator}

                                <c:set var="count" value="${count + 1}" scope="page"/>
                            </c:otherwise>
                        </c:choose>  

                    </c:forEach>
                </p>

            </div>
        </div>              
    </c:if>      

    <c:if test="${filme.getLancamento() != NULL}">
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><strong>Lançamento:</strong></div>
            <div class="col-md-9"><p>${filme.getFormatLancamento()}</p></div>
        </div>            
    </c:if>  
    
    <c:if test="${filme.getQtdFav() != NULL}">
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><strong>Favoritos:</strong></div>
            <div class="col-md-9"><p>${filme.getQtdFav()} usuários</p></div>
        </div>          
    </c:if>  

    <c:if test="${filme.getQtdPret() != NULL}">
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><strong>Pretendidos:</strong></div>
            <div class="col-md-9"><p>${filme.getQtdPret()} usuários</p></div>
        </div>          
    </c:if> 

    <c:if test="${filme.getQtdAss() != NULL}">
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><strong>Assistidos</strong></div>
            <div class="col-md-9"><p>${filme.getQtdAss()} usuários</p></div>
        </div>          
    </c:if> 

    <c:if test="${filme.getQtdNota() != NULL}">
        <div class="row info-film-row">
            <div class="col-md-2 info-film"><strong>Nota Média:</strong></div>
            <div class="col-md-9"><p>${filme.getQtdNota()}</p></div>
        </div>          
    </c:if>     

    <hr/>

    <h3><i class="glyphicon glyphicon-comment"></i> Comentários</h3>
    <c:if test="${ (_permissions != NULL) && (_permissions.check(_user.getProfile(), 'Filme', 'comment')) }">

        <form class="form-horizontal" action="FilmeController?action=comment&id=${filme.getId()}" method="post">

            <div class="form-group">
                <label for="comment" class="col-lg-2 control-label">Comentário</label>
                <div class="col-lg-8">

                    <textarea class="form-control" rows="4" cols="50" name="comment" class="" id="comment">O que achou desse filme ${_user.getName()}?</textarea>                     

                </div>
                <div class="col-lg-2">
                    <button type="submit" class="btn btn-success" name="save"><i class="glyphicon glyphicon-send"></i> Enviar</button>
                </div>
            </div>
        </form>

    </c:if>

    <c:choose>
        <c:when test="${comments == NULL}">
            <div class="center">
                <strong>Sem comentários</strong>
            </div>
        </c:when>

        <c:otherwise>

            <c:forEach items="${comments}" var="comment">

                <div class="well well-lg">

                    <div class="row">

                        <div class="col-lg-3" style="border-right: 1px solid #bababa">

                            <h4><i class="glyphicon glyphicon-user"></i> ${comment.getUser().getName()}</h4>
                            <i class="glyphicon glyphicon-calendar"></i><small> ${comment.getFormatData()} - ${comment.getTime()}</small>

                        </div>
                        <div class="col-lg-9">

                            <p>${comment.getComment()}</p>

                        </div>       

                    </div> 
                    <c:if test="${ (_permissions != NULL) && (_permissions.check(_user.getProfile(), 'Filme', 'delete')) }">
                        <div class="pull-right">            
                            <a href="FilmeController?action=delete&id=${comment.getImdb()}&user=${comment.getUser().getId()}&date=${comment.getData()}&time=${comment.getTime()}"><button type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span></button></a>        
                        </div>                    
                    </c:if>       
                </div>

            </c:forEach>

        </c:otherwise>
    </c:choose>  

</section>