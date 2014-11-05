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
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
    </style>
  </head>
  <body>

    <div class="container">

       <c:import url = "/view/helper/message.jsp"/>
      
       <form class="form-signin" method="post" action="AuthenticateController?action=logon">
            <h2 class="form-signin-heading">Logon</h2>
            <input type="text" class="input-block-level" placeholder="Email" name="email">
            <input type="password" class="input-block-level" placeholder="Senha" name="password">
                <label class="checkbox">
                  <input type="checkbox" value="remember-me"> Lembrar me
                </label>
            <button class="btn btn-large btn-primary" type="submit">Entrar</button>
      </form>
    </div> <!-- /container -->

    <script src="bootstrap/js/jquery.js"></script>
  </body>
</html>
