<%@page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form class="form-signin" method="post" action="AuthenticateController?action=logon">
    <div class="center">
        <h2 class="form-signin-heading">Logon</h2>
        <div class="form-group">
            <input type="text" class="input-block-level form-control" placeholder="E-mail" name="email">
        </div>                
        <div class="form-group">
            <input type="password" class="input-block-level form-control" placeholder="Senha" name="password">
        </div>    
        <button class="btn btn-large btn-success" type="submit"><i class="glyphicon glyphicon-log-in"></i> Entrar</button>
    </div>
</form>
<div class="form-signin">
    <div class="center">
        <a href="AuthenticateController?action=logonFacebook" >
            <button class="btn btn-large btn-primary"><i class="glyphicon glyphicon-user"></i> Entrar com Facebook</button>
        </a>
    </div>
</div>