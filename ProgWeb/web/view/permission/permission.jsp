<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section>
    <div class="page-header">
        <h1>Configurar Permissões - <small> ${profile.getName()} </small></h1>
    </div>
    <form class="form-horizontal" action="PermissionController?id=${profile.getId()}" method="post">
        <div class="form-group">
            <div class="row">
                <c:forEach items="${modules}" var="module">
                    <div class="col-lg-4">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title"> ${module.getName()} </h3>
                            </div>
                            <div class="panel-body">
                                <ul>
                                    <c:forEach items="${module.getActions()}" var="action">
                                        <li>
                                            <label class="checkbox">
                                                <input type="checkbox" name="permissions" value="${profile.getId()};${module.getId()};${action.getId()}"  
                                                       <c:if test="${permissions.check(profile.getId(), module.getName(), action.getName())}"> 
                                                           checked="checked"
                                                       </c:if>
                                                       > ${action.getName()}
                                            </label>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>            
                    </div>
                </c:forEach>
            </div>
        </div>        
        <div class="form-group">
            <button type="submit" class="btn btn-default" name="save">Salvar</button>
        </div>
    </form>
</section>    