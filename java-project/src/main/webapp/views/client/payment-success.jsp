<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Payment Success</title>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto px-4 py-10">
  <div class="bg-white p-8 rounded-lg shadow-lg max-w-xl mx-auto text-center">
    <h1 class="text-3xl font-bold text-green-500">Payment Successful!</h1>
    <p class="mt-4 text-gray-700">Thank you for booking. Your payment has been successfully processed.</p>
    <p class="mt-4"><strong>Order ID:</strong> <%= request.getParameter("orderId") %></p>
    <a href="<%= request.getContextPath() %>/booking?action=my-booking"
       class="mt-6 inline-block bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
      View My Bookings
    </a>
  </div>
</div>
</body>
</html>
