<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Payment Failed</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto px-4 py-10">
    <div class="bg-white p-8 rounded-lg shadow-lg max-w-xl mx-auto text-center">
        <h1 class="text-3xl font-bold text-red-500">Payment Failed!</h1>
        <p class="mt-4 text-gray-700">Unfortunately, your payment could not be processed.</p>
        <a href="<%= request.getContextPath() %>/booking?action=create"
           class="mt-6 inline-block bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Try Again
        </a>
    </div>
</div>
</body>
</html>
