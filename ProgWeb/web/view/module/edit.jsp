<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section>
    <div class="page-header">    
        <h1>Editar de Módulo</h1>
    </div>
    <form class="form-horizontal" action="ModuleController?action=edit&id=<c:out value="${module.getId()}"/>" method="post">

        <div class="form-group">
            <label for="name" class="col-lg-2 control-label">Nome</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="name" placeholder="Nome" name="name" value="<c:out value="${module.getName()}" />">
            </div>
        </div>

        <div class="form-group">
            <label for="descricao" class="col-lg-2 control-label">Descrição</label>
            <div class="col-lg-10">
                <textarea id="descricao" name="description" class="form-control" placeholder="Descricao" rows="3"><c:out value="${module.getDescription()}" /></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="action" class="col-lg-2 control-label">Ações</label>
            <div class="col-lg-3">
                <select id="profile" multiple="multiple" name="actions" class="form-control">
                    <c:forEach items="${actions}" var="action">
                        <option value="<c:out value="${action.getId()}"/>"    
                                <c:if test="${ module.actionExists (action.getId()) eq true}"> 
                                    selected="selected"  
                                </c:if> 
                                />
                        <c:out value="${action.getName()}" />
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="active" class="col-lg-2 control-label" /> Ativo</label>
            <div class="col-lg-10">
                <input type="checkbox" name="active" <c:out value="${module.getActive() ? 'checked': ''}"/>>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default" name="save">Salvar</button>
            </div>
        </div>
    </form>
</section>      