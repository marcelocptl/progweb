<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section>
    
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
    <hr/>

    <div>
        <h2>${filme.getNome()}</h2>
        <p>
            <i class="glyphicon glyphicon-calendar"></i> ${filme.getLancamento()}
            <br/>
            Sinopse: <br/>
            ${filme.getSinopse()}
            <br/>
        </p>

    </div> 

    <hr/>

    <h3>Comentários</h3>
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
    
    <c:forEach items="${comments}" var="comment">

        <div class="well well-lg">

            <div class="row">

                <div class="col-lg-2" style="border-right: 1px solid #bababa">

                    <h4><i class="glyphicon glyphicon-user"></i> ${comment.getUser().getName()}</h4>
                    <i class="glyphicon glyphicon-calendar"></i><small> ${comment.getData()} - ${comment.getTime()}</small>

                </div>
                <div class="col-lg-10">

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
</section>