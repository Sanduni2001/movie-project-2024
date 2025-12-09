<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Bookings</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 text-gray-800 min-h-screen flex flex-col">

<!-- Navbar -->
<jsp:include page="/components/navbar.jsp" />

<!-- Main Content -->
<div class="container mx-auto px-4 py-8 flex-grow">
    <h1 class="text-3xl font-bold mb-6 text-center">Your Bookings</h1>

    <!-- If no bookingMovieList or it's empty, show message -->
    <c:if test="${empty bookingMovieList}">
        <p class="text-center text-gray-600 text-lg">You have no bookings yet.</p>
    </c:if>

    <!-- Otherwise, display booking cards -->
    <c:if test="${not empty bookingMovieList}">
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            <c:forEach var="bm" items="${bookingMovieList}">
                <!-- Extract booking & movie from the map -->
                <c:set var="booking" value="${bm.booking}" />
                <c:set var="movie"   value="${bm.movie}" />

                <div class="bg-white shadow-lg rounded-lg overflow-hidden hover:shadow-xl transition-shadow duration-300">
                    <!-- If you have a movie image path -->
                    <c:if test="${not empty movie.image}">
                        <img class="w-full h-48 object-cover"
                             src="${pageContext.request.contextPath}/DBImages/${movie.image}"
                             alt="${movie.movieName}" />
                    </c:if>

                    <div class="p-6">
                        <!-- Movie Title -->
                        <h2 class="text-xl font-bold text-gray-800 mb-2">
                            <c:choose>
                                <c:when test="${not empty movie.movieName}">
                                    ${movie.movieName}
                                </c:when>
                                <c:otherwise>
                                    Unknown Movie
                                </c:otherwise>
                            </c:choose>
                        </h2>

                        <!-- Booking Details -->
                        <p class="text-gray-700 mb-1">
                            <strong>Show Date:</strong> ${booking.showDate}
                        </p>
                        <p class="text-gray-700 mb-1">
                            <strong>Show Time:</strong> ${booking.showTime}
                        </p>
                        <p class="text-gray-700 mb-1">
                            <strong>Seats:</strong> ${booking.numberOfSeats}
                        </p>
                        <p class="text-yellow-500 font-semibold text-lg mb-4">
                            <strong>Total Price:</strong> $${booking.totalPrice}
                        </p>

                        <!-- Delete Button -->
                        <a href="${pageContext.request.contextPath}/booking?action=delete&bookingId=${booking.bookingId}"
                           onclick="return confirm('Are you sure you want to delete this booking?');"
                           class="bg-red-500 hover:bg-red-700 text-white px-4 py-2 rounded inline-block text-center">
                            Delete
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<!-- Footer -->
<jsp:include page="/components/footer.jsp" />

</body>
</html>
