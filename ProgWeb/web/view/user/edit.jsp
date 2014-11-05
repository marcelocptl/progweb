<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="page-header">
    <ul class="breadcrumb">
        <li><a href="#">Home</a> <span class="divider"></span></li>
        <li><a href="#">Usuários</a> <span class="divider"></span></li>
        <li class="active">Editar Usuário</li>
    </ul>
    <h1>Editar Usuário</h1>
</div>
<form class="form-horizontal" action="UserController?action=edit&id=<c:out value="${user.getId()}"/>" method="post">

    <div class="form-group">
        <label for="name" class="col-lg-2 control-label">Nome</label>
        <div class="col-lg-10">
            <input type="text" class="form-control" id="name" placeholder="Nome" name="name" value="<c:out value="${user.getName()}" />">
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-lg-2 control-label">E-mail</label>
        <div class="col-lg-10">
            <input type="email" class="form-control" id="email" placeholder="E-mail" name="email" value="<c:out value="${user.getEmail()}" />">
        </div>
    </div>
    <div class="form-group">
        <label for="profile" class="col-lg-2 control-label">Perfil</label>
        <div class="col-lg-3">
            <select id="profile" name="profile" class="form-control">
                <c:forEach items="${profiles}" var="profile">
                    <option value="<c:out value="${profile.getId()}"/>" <c:if test="${profile.getId() == user.getProfile()}"> selected="selected"  </c:if> />
                            <c:out value="${profile.getName()}" />
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="active" class="col-lg-2 control-label">Ativo</label>
        <div class="col-lg-10">
            <input type="checkbox" name="active" <c:out value="${user.getActive() ? 'checked': ''}"/> >
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
            <button type="submit" class="btn btn-default" name="save">Salvar</button>
        </div>
    </div>
</form>