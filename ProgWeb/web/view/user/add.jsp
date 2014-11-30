<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form class="form-signin" method="post" action="UserController?action=add">
    <div class="center">
        <h2 class="form-signin-heading">Cadastro</h2>

        <div class="form-group">
            <input type="text" class="input-block-level form-control" id="name" placeholder="Nome" name="name">
        </div>
        <div class="form-group">
            <input type="password" class="input-block-level form-control" id="password" placeholder="Senha" name="password">
        </div>
        <div class="form-group">
            <input type="password" class="input-block-level form-control" id="confirm" placeholder="Confirmar Senha" name="confirm">
        </div>        
        <div class="form-group">
            <input type="email" class="input-block-level form-control" id="email" placeholder="E-mail" name="email">
        </div>

        <c:if test="${ (_permissions != NULL) && (_permissions.check(_user.getProfile(), 'User', 'add')) }">

            <div class="form-group">
                <select id="profile" name="profile" class="input-block-level form-control">
                    <c:forEach items="${profiles}" var="profile">
                        <option value="<c:out value="${profile.getId()}" />"><c:out value="${profile.getName()}" /></option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="active" class="input-block-level control-label">Ativo</label>
                <input type="checkbox" name="active" checked="checked">
            </div>

        </c:if>

        <div class="form-group">
            <button class="btn btn-large btn-success" type="submit" name="save"><i class="glyphicon glyphicon-ok"></i> Confirmar</button>
        </div>
    </div>
</form>
<div class="form-signin">
    <div class="center">
        <a href="AuthenticateController?action=logonFacebook" >
            <button class="btn btn-large btn-primary"><i class="glyphicon glyphicon-user"></i> Entrar com Facebook</button>
        </a>
    </div>
</div>