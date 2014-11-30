<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<section>
    <div class="page-header">
        <h1>Configurar Permissões</h1>
    </div>
    <form class="form-horizontal" action="PermissionController" method="post">
        <div class="form-group">
            <ul>
                <c:forEach items="${profiles}" var="profile">
                    <li>
                        <input type="checkbox" name="permissions"> ${profile.getName()} 
                        <ul>
                            <c:forEach items="${modules}" var="module">
                                <li>
                                    <input type="checkbox" name="permissions"> ${module.getName()}
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
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </div>        
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default" name="save">Salvar</button>
            </div>
        </div>
    </form>
</section>    