<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Ламповый парсер</title>
  </head>
  <body ng-app="myApp" class="ng-cloak">
  <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css">
  <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
  <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
  <script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="webjars/angularjs/1.6.8/angular.min.js"></script>
  <script src="webjars/angularjs/1.6.8/angular-cookies.min.js"></script>
  <script src="webjars/angularjs/1.6.8/angular-resource.min.js"></script>
  <script src="webjars/angularjs/1.6.8/angular-route.min.js"></script>

  <script src="<c:url value='/static/js/app.js' />"></script>
  <script src="<c:url value='/static/js/errorInterceptor.js' />"></script>

  <nav class="navbar navbar-default">
      <div class="container">
          <div class="navbar-header">
              <a class="navbar-brand" href="/">Arcturus</a>
          </div>
          <ul class="nav navbar-nav navbar-right">
              <li><a href="#!parse">Спарсить новости с сайта</a></li>
              <li><a href="#!rssparse">Спарсить новости с rss-ленты</a></li>
              <li><a href="#!result">Просмотреть результаты</a></li>
          </ul>
      </div>
  </nav>

  <div class="alert alert-success fade al">
      <p class="text-center"><strong>Запрос отправлен!</strong> Ваш запрос обрабатывается сервером, после обработки результаты отобразятся в разеде "Посмотреть результы".</p>
  </div>

  <div ng-view></div>

  <script src="<c:url value='/static/js/ParseController.js' />"></script>
  <script src="<c:url value='/static/js/ParseService.js' />"></script>
  </body>
</html>
