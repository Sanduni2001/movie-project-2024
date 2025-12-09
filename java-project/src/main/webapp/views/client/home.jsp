<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="css/output.css">
  <script src="https://cdn.tailwindcss.com"></script>
  <title>ABC Cinema</title>
</head>
<body class="bg-gray-100 text-gray-800">

<!-- Material Tailwind -->
<script src="https://cdn.jsdelivr.net/npm/@material-tailwind/html@latest/dist/material-tailwind.min.js"></script>

<!-- Navbar -->
<nav>
  <jsp:include page="/components/navbar.jsp" />
</nav>

<!-- Carousel Section -->
<jsp:include page="/components/carousal.jsp" />

<!-- Now Showing Section -->
<section class="my-12 mx-auto max-w-screen-xl px-4 sm:px-6 lg:px-8">

      <jsp:include page="/components/now_showing.jsp" />

</section>

<!-- Coming Soon Section -->
<section class="my-12 mx-auto max-w-screen-xl px-4 sm:px-6 lg:px-8">

  <jsp:include page="/components/comming_soon.jsp" />

</section>

<section class="my-12 mx-auto max-w-screen-xl px-4 sm:px-6 lg:px-8">

  <jsp:include page="/views/client/add-review.jsp" />

</section>

<!-- Footer -->
<footer class="mt-12">
  <jsp:include page="/components/footer.jsp" />
</footer>

</body>
</html>
