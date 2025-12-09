<%@ page import="com.movieproject.utils.AssetsUrl" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/output.css">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>All Movies - ABC Cinema</title>
</head>
<body class="bg-gray-100 text-gray-800">

<!-- Material Tailwind -->
<script src="https://cdn.jsdelivr.net/npm/@material-tailwind/html@latest/dist/material-tailwind.min.js"></script>

<!-- Navbar -->
<jsp:include page="/components/navbar.jsp" />

<!-- Carousel Section -->
<div>
    <jsp:include page="/components/carousal.jsp" />
</div>

<!-- Movies Section -->
<section class="my-12 mx-auto max-w-screen-xl px-4 sm:px-6 lg:px-8">
    <h1 class="text-3xl font-bold text-gray-800 mb-8 text-center">All Movies</h1>

    <!-- Movies Grid -->
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">
        <c:forEach var="movie" items="${movies}" varStatus="loop">
            <!-- Movie Card -->
            <div class="group relative cursor-pointer items-center justify-center overflow-hidden transition-shadow hover:shadow-xl hover:shadow-gray-500/50 rounded-lg">
                <!-- Movie Image -->
                <div class="h-96 w-72 rounded-lg overflow-hidden">
                    <img class="h-full w-full object-cover transition-transform duration-500 ease-in-out"
                         src="${pageContext.request.contextPath}/DBImages/${movie.image}" alt="${movie.movieName}" />
                </div>

                <!-- Overlay and Text -->
                <div class="absolute inset-0 bg-gradient-to-b from-transparent via-transparent to-gray-900 group-hover:from-gray-800/70 group-hover:via-gray-800/60 group-hover:to-gray-900/80"></div>
                <div class="absolute inset-0 flex translate-y-[60%] flex-col items-center justify-center px-6 text-center transition-all duration-500 group-hover:translate-y-0">
                    <h2 class="text-xl font-bold text-white mb-3">${movie.movieName}</h2>
                    <a href="${pageContext.request.contextPath}/movie-details?movieId=${movie.movieId}"
                       class="bg-blue-600 text-white px-4 py-2 rounded-md text-sm shadow-md transition-colors hover:bg-blue-700">
                        More Info
                    </a>
                </div>
            </div>
        </c:forEach>

        <!-- No Movies Available -->
        <c:if test="${empty movies}">
            <div class="col-span-full text-center text-gray-500">
                <p>No movies available at this time. Please check back later!</p>
            </div>
        </c:if>
    </div>
</section>

<!-- Footer -->
<footer class="mt-12">
    <jsp:include page="/components/footer.jsp" />
</footer>

</body>
</html>
