<%@ page import="ru.job4j.model.Advertisement" %>
<%@ page import="ru.job4j.service.AdsService" %>
<%@ page import="ru.job4j.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Доска объявлений</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/auth.jsp"> <c:out value="${user.getName()}" default="Гость"/> | Выйти</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/create.do">Добавить новое объявление</a>
            </li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Объявления о продаже машин:
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Описание</th>
                        <th scope="col">Марка машины</th>
                        <th scope="col">Тип кузова</th>
                        <th scope="col">Статус</th>
                        <th scope="col">Фото</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Advertisement ad : AdsService.instOf().findAllAds()) { %>
                    <tr>
                        <td>
                            <%=ad.getDescription()%>
                        </td>
                        <td>
                            <%=ad.getMark()%>
                        </td>
                        <td>
                            <%=ad.getBody()%>
                        </td>
                        <td>
                            <% if (!ad.isDone()) { %>
                                <%="Актуально"%>
                                <% if (((User) session.getAttribute("user")) != null && ((((User) session.getAttribute("user")).getEmail()).equals(ad.getUser().getEmail()))) { %>
                                    <a href="<%=request.getContextPath()%>/change.do?id=<%=ad.getId()%>">
                                        <span> | Изменить на "Продано"</span>
                                    </a>
                                    <% } %>
                            <% } else { %>
                                <%="Закрыто"%>
                            <% } %>
                        </td>
                        <td>
                            <% if (!ad.isPhoto()) { %>
                                <a href="<%=request.getContextPath()%>/upload.jsp?id=<%=ad.getId()%>">
                                    Добавить фото
                                </a>
                            <% } else { %>
                                <img src="<%=request.getContextPath()%>/download?name=<%=ad.getId()%>" width="100px" height="100px"/>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
