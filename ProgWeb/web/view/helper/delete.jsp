<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<form class="form-signin" method="post" action="<c:out value="${controller}" />?action=delete&id=<c:out value="${objDeleted.getId()}" />">
    <div class="center">
        <h2 class="form-signin-heading">Deletar?</h2>

        <p><c:out value="${objDeleted}" /></p>
        <br/>
        <div class="form-group">
            <button class="btn btn-large btn-danger" type="submit" name="save"><i class="glyphicon glyphicon-remove-circle"></i> Deletar</button>
        </div>
    </div>
</form>