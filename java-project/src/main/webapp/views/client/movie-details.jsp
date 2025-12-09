<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie Details</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>

<body class="g-gray-100 flex flex-col min-h-screen">
<div>
    <jsp:include page="/components/navbar.jsp" />

</div>
<div class="container mx-auto px-4 py-10 max-w-6xl w-full">
    <div class="bg-white rounded-2xl shadow-2xl overflow-hidden">
        <!-- Movie Details -->
        <div class="md:flex">
            <!-- Movie Poster Section -->
            <div class="md:w-1/3 relative">
                <img src="${pageContext.request.contextPath}/DBImages/${movie.image}"
                        alt="${movie.movieName}"
                        class="w-full h-full object-cover"
                >
                <div class="absolute bottom-0 left-0 right-0 bg-black bg-opacity-50 text-white p-4">
                    <h1 class="text-3xl font-bold truncate">${movie.movieName}</h1>
                </div>
            </div>

            <!-- Movie Details Section -->
            <div class="md:w-2/3 p-6 lg:p-8">
                <p class="text-gray-600 mb-6 text-justify">${movie.description}</p>
                <div class="grid md:grid-cols-2 gap-4">
                    <div class="bg-gray-100 p-4 rounded-lg">
                        <strong>Director:</strong>
                        <p>${movie.directorName}</p>
                    </div>
                    <div class="bg-gray-100 p-4 rounded-lg">
                        <strong>Music by:</strong>
                        <p>${movie.musicBy}</p>
                    </div>
                    <div class="bg-gray-100 p-4 rounded-lg">
                        <strong>Produced by:</strong>
                        <p>${movie.produceBy}</p>
                    </div>
                    <div class="bg-gray-100 p-4 rounded-lg">
                        <strong>Release Date:</strong>
                        <p>${movie.releaseDate}</p>
                    </div>
                </div>
                <div class="mt-6 bg-yellow-100 p-4 rounded-lg">
                    <strong>Ticket Price:</strong>
                    <p class="text-2xl font-bold text-yellow-700">$${movie.ticketPrice}</p>
                </div>
            </div>
        </div>

        <!-- Show Dates and Times Section -->
        <div class="bg-gray-50 p-6 lg:p-8 mt-6">
            <h2 class="text-2xl font-bold text-gray-800 mb-6">Show Dates and Times</h2>
            <div class="grid sm:grid-cols-2 lg:grid-cols-3 gap-4">
                <c:forEach items="${showDates}" var="showDate">
                    <div class="bg-white rounded-lg shadow-md p-4 border-l-4 border-blue-500">
                        <h3 class="text-xl font-semibold text-gray-800 mb-3">${showDate.showDate}</h3>
                        <div class="space-y-2">
                            <c:forEach items="${showDate.showTimes}" var="showTime">
                                <a href="${pageContext.request.contextPath}/booking?action=booking&movieId=${movie.movieId}&showTimeId=${showTime.showTimeId}&showDateId=${showDate.showDateId}"
                                   class="block py-2 px-3 bg-gray-100 rounded-md hover:bg-blue-50 transition duration-200">
                                        ${showTime.showTime}
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<div>
    <jsp:include page="/components/footer.jsp" />

</div>
</body>
</html>
