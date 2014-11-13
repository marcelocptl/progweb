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
    <link href="bootstrap/css/main.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #330033;
      }

      .form-signin {
        color: #fff;
        max-width: 400px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #000000;
        border: 1px solid #333333;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
      }
    </style>
  </head>
  <body>

    <div class="container">

       <c:import url = "/view/helper/message.jsp"/>
       
        <form class="form-signin" method="post" action="AuthenticateController?action=logon">
            <div class="center">
                <h2 class="form-signin-heading">Logon</h2>
                <div class="form-group">
                  <input type="text" class="input-block-level form-control" placeholder="E-mail" name="email">
                </div>                
                <div class="form-group">
                  <input type="password" class="input-block-level form-control" placeholder="Senha" name="password">
                </div>    
                <button class="btn btn-large btn-success" type="submit"><i class="glyphicon glyphicon-ok"></i> Entrar</button>
            </div>
        </form>
        <div class="form-signin">
            <div class="center">
                <a href="AuthenticateController?action=logonFacebook" >
                    <button class="btn btn-large btn-primary"><i class="glyphicon glyphicon-user"></i> Entrar com Facebook</button>
                </a>
                <a href="#">
                    <button class="btn btn-large btn-warning"><i class="glyphicon glyphicon-plus-sign"></i> Cadastre-se</button>
                </a>
            </div>
        </div>
    </div> <!-- /container -->

    <script src="bootstrap/js/jquery.js"></script>
  </body>
</html>
