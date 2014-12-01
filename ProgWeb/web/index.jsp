<%@page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="USER_MODULE" scope="session" value="User"/>
<c:set var="PROFILE_MODULE" scope="session" value="Profile"/>
<c:set var="ACTION_MODULE" scope="session" value="Action"/>
<c:set var="MODULE_MODULE" scope="session" value="Module"/>
<c:set var="PERMISSION_MODULE" scope="session" value="Permission"/>
<c:set var="LOG_MODULE" scope="session" value="Log"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>WebFilms - Catalog</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Trabalho de ProgWeb 2 - UFMS/CPTL - Bacharelado em Sistemas de Informação">
        <meta name="author" content="Brucce Neves, Marcelo Henrique Pereira Lima e Marcos Roberto Nesso">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="bootstrap/css/main.css" rel="stylesheet">

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
                    <a class="navbar-brand" href="/ProgWeb"><i class="glyphicon glyphicon-film"></i> WebFilms
                    </a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        
                        <li><a href="FilmeController?action=list"><i class="glyphicon glyphicon-globe"></i> Catálogo de Filmes</a></li>
                        <c:if test="${_user != null}"> 
                            <li><a href="#"><i class="glyphicon glyphicon-list"></i> Meus Filmes</a></li>
                        </c:if>  
                            
                    </ul>
                    
                    <ul class="nav navbar-nav pull-right">
                        <c:choose>
                            <c:when test="${_user != NULL}">

                                <li>
                                    <a ><span class="text-warning"><i class="glyphicon glyphicon-user"></i> <c:out value="${_user.getName()}"/></span></a> 
                                </li>
                                <c:set var="add_user" value="${_permissions.check(_user.getProfile(), USER_MODULE, 'add')}"/>
                                <c:set var="list_user" value="${_permissions.check(_user.getProfile(), USER_MODULE, 'list')}"/>
                                <c:if test="${add_user || list_user}"> 
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Usuários <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${add_user}"> 
                                            <li><a href="UserController?action=add">Novo Usuário</a></li>
                                        </c:if>    
                                        <c:if test="${list_user}"> 
                                            <li><a href="UserController?action=list">Listar Usuários</a></li>
                                        </c:if>
                                    </ul>
                                </li>
                                </c:if>
                                <c:set var="add_profile" value="${_permissions.check(_user.getProfile(), PROFILE_MODULE, 'add')}"/>
                                <c:set var="list_profile" value="${_permissions.check(_user.getProfile(), PROFILE_MODULE, 'list')}"/>
                                <c:set var="add_action" value="${_permissions.check(_user.getProfile(), ACTION_MODULE, 'add')}"/>
                                <c:set var="list_action" value="${_permissions.check(_user.getProfile(), ACTION_MODULE, 'list')}"/>
                                <c:set var="add_module" value="${_permissions.check(_user.getProfile(), MODULE_MODULE, 'add')}"/>
                                <c:set var="list_module" value="${_permissions.check(_user.getProfile(), MODULE_MODULE, 'list')}"/>
                                <c:if test="${add_profile || list_profile || add_action || list_action || add_module || list_module}">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Controle de Acesso <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <c:if test="${add_profile}">
                                            <li><a href="ProfileController?action=add">Novo Perfil</a></li>
                                            </c:if>   
                                            <c:if test="${list_profile}">
                                            <li><a href="ProfileController?action=list">Listar Perfils</a></li>
                                            </c:if>   
                                            <c:if test="${add_action}">
                                            <li><a href="ActionController?action=add">Nova Ação</a></li>
                                            </c:if>   
                                            <c:if test="${list_action}">
                                            <li><a href="ActionController?action=list">Listar Ações</a></li>
                                            </c:if>  
                                            <c:if test="${add_module}">
                                            <li><a href="ModuleController?action=add">Novo Módulo</a></li>
                                            </c:if>  
                                            <c:if test="${list_module}">
                                            <li><a href="ModuleController?action=list">Listar Módulos</a></li>
                                            </c:if>
                                        </ul>
                                    </li>
                                </c:if>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="glyphicon glyphicon-cog"></i> </a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${_permissions.check(_user.getProfile(), LOG_MODULE, 'list')}">
                                            <li><a href="LogController?action=list"><i class="glyphicon glyphicon-stats"></i> Log do Sistema</a></li>
                                        </c:if>

                                        <li><a href="AuthenticateController?action=logoff"><i class="glyphicon glyphicon-off"></i> Sair</a></li>
                                    </ul>
                                </li>                                

                            </c:when>
                            <c:otherwise>
                                <li><a href="UserController?action=add"><i class="glyphicon glyphicon-fire"></i> Cadastre-se</a></li>
                                <li><a href="AuthenticateController?action=logon"><i class="glyphicon glyphicon-log-in"></i> Entrar</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>

                </div><!--/.navbar-collapse -->
            </div>
        </div>

        <c:import url = "/view/helper/message.jsp"/>

        <div class="container bodycolor" id="container">        
            <c:choose>
                <c:when test="${pageContent != NULL}">
                    <c:import url = "${pageContent}"/>
                </c:when>

                <c:otherwise>
                    <c:import url = "/view/home/home.jsp"/>
                </c:otherwise>
            </c:choose>

        </div>

        <footer>
            <div class="navbar navbar-inverse navbar-fixed-bottom">
                <ul class="nav navbar-nav">
                    <li><a><span class="text-warning"> &COPY; 2014 </span></a> </li>
                    <li><a href="#">Developer by Brucce | Marcelo | Marcos</a></li>     
                </ul>
                <ul class="nav navbar-nav pull-right">   
                    <li><a href="FilmeController?action=recentes">Filmes Recentes</a></li>
                    <!--<li><a href="FilmeController?action=melhores">Melhores Filmes</a></li>-->
                </ul>                    
            </div>
        </footer>

        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>