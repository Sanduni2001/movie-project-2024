<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Now Showing</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.4/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800">

<h2 class="text-3xl font-bold text-gray-800 text-center mb-6">Now Showing</h2>

<div class="flex items-center justify-center bg-white mt-4 mb-8">
    <div class="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
        <c:forEach var="movie" items="${nowShowingMovies}" varStatus="loop" begin="0" end="9">
            <!-- Movie Card -->
            <div class="group relative cursor-pointer items-center justify-center overflow-hidden transition-shadow hover:shadow-xl hover:shadow-gray-500/50 rounded-lg">
                <div class="h-96 w-72 rounded-lg overflow-hidden">
                    <img class="h-full w-full object-cover transition-transform duration-500 ease-in-out"
                         src="${pageContext.request.contextPath}/DBImages/${movie.image}" alt="${movie.movieName}" />
                </div>
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
    </div>
</div>

</body>
</html>
