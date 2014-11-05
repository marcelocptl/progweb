<%@page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>ProgWeb - Design Patterns</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <style> body { padding-top: 60px; } </style>
  </head>
  <body>
      
     <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">ProgWeb - Design Patterns
          </a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Usuários <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <c:if test="${_permissions.check(_user.getProfile(), 'User', 'edit')}"> 
                    <li><a href="UserController?action=add">Novo Usuário</a></li>
                </c:if>    
                <c:if test="${_permissions.check(_user.getProfile(), 'User', 'list_user')}">
                    <li><a href="UserController?action=list">Listar Usuários</a></li>
                </c:if>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Controle de Acesso <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <c:if test="${_permissions.check(_user.getProfile(), 'Profile', 'create')}">
                    <li><a href="ProfileController?action=add">Novo Perfil</a></li>
                </c:if>   
                <c:if test="${_permissions.check(_user.getProfile(), 'Profile', 'list')}">
                    <li><a href="ProfileController?action=list">Listar Perfils</a></li>
                </c:if>   
                <c:if test="${_permissions.check(_user.getProfile(), 'Action', 'create')}">
                    <li><a href="ActionController?action=add">Nova Ação</a></li>
                </c:if>   
                <c:if test="${_permissions.check(_user.getProfile(), 'Action', 'list')}">
                    <li><a href="ActionController?action=list">Listar Ações</a></li>
                </c:if>  
                <c:if test="${_permissions.check(_user.getProfile(), 'Module', 'create')}">
                    <li><a href="ModuleController?action=add">Novo Módulo</a></li>
                </c:if>  
                <c:if test="${_permissions.check(_user.getProfile(), 'Module', 'list')}">
                    <li><a href="ModuleController?action=list">Listar Módulos</a></li>
                </c:if>  
                <c:if test="${_permissions.check(_user.getProfile(), 'Permission', 'create')}">
                    <li><a href="PermissionController?action=permission">Permissões</a></li>
                </c:if>
              </ul>
            </li>

            <li><a href="#contact">Sobre</a></li>
            <li>
                <c:choose>
                    <c:when test="${_user !=  NULL}"> 
                        <a href="AuthenticateController?action=logoff">Sair</a> 
                         </c:when>
                    <c:otherwise> 

                    </c:otherwise>
                </c:choose>
            </li>
            <li><a href="LogController?action=list">Sistema de Log</a></li>
            <li>
                <c:choose>
                    <c:when test="${_user !=  NULL}"> 
                        <a ><span class="text-warning">Olá, bem vindo <c:out value="${_user.getName()}"/>!</span></a> 
                         </c:when>
                    <c:otherwise> 

                    </c:otherwise>
                </c:choose>
            </li>
            
          </ul>
        </div><!--/.navbar-collapse -->
      </div>
    </div>
    
    <c:import url = "/view/helper/message.jsp"/>
    
    <div class="container" id="container">
        <c:if test="${pageContent !=  NULL}">
            <c:import url = "${pageContent}"/>
        </c:if>    
    </div>
    <div style="height: 100px;"></div>   
    <footer>
        <div class="navbar navbar-inverse navbar-fixed-bottom">
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a ><span class="text-warning"> &COPY; 2014 </span></a> </li>
                    <li ><a href="#">Developer by Lab. de Programação para Web</a></li>                
                </ul>
            </div>
        </div>    
    </footer>
     
    <script src="bootstrap/js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>