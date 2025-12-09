<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
    <style>
        .error-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #ffdddd;
            border: 1px solid #cc0000;
            border-radius: 5px;
        }
        .error-heading {
            color: #cc0000;
            margin-bottom: 10px;
        }
        .error-message {
            color: #666;
            line-height: 1.6;
        }
        .home-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border-radius: 5px;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1 class="error-heading">Error</h1>
    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>
    <c:if test="${empty error}">
        <p class="error-message">An unknown error occurred. Please try again later.</p>
    </c:if>
    <a href="${pageContext.request.contextPath}/" class="home-link">Go to Homepage</a>
</div>
</body>
</html>
