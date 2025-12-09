<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Success</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 h-screen flex items-center justify-center">

<div class="max-w-lg mx-auto bg-white p-8 rounded-lg shadow-lg text-center">
    <h1 class="text-2xl font-bold text-green-600 mb-4">
        <c:out value="${sessionScope.success}"/>
    </h1>
    <p class="text-gray-700 mb-6">Thank you for booking with us! You can return to the homepage to explore more movies and showtimes.</p>

    <a href="${pageContext.request.contextPath}/" class="bg-blue-500 text-white px-6 py-2 rounded-lg hover:bg-blue-600 transition duration-200">
        Go to Home
    </a>
</div>

</body>
</html>
