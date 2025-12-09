<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Booking - ${movie.movieName}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <!-- Stripe.js library -->
    <script src="https://js.stripe.com/v3"></script>
</head>
<body class="bg-gray-100">

<!-- Include Header/Navbar -->
<div>
    <jsp:include page="/components/navbar.jsp" />
</div>

<div class="container mx-auto px-4 py-10">
    <div class="bg-white p-8 rounded-lg shadow-lg max-w-4xl mx-auto">

        <!-- Error Section -->
        <c:if test="${empty movie or empty showTime}">
            <div class="text-center">
                <h2 class="text-xl font-bold text-red-500">
                    Error: Movie or Show Time is unavailable.
                </h2>
                <a href="${pageContext.request.contextPath}/"
                   class="mt-4 inline-block bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Return to Home
                </a>
            </div>
        </c:if>

        <!-- Booking Section -->
        <c:if test="${not empty movie and not empty showTime and not empty showDate}">
            <div class="flex flex-col md:flex-row">
                <!-- Movie Image -->
                <div class="md:flex-shrink-0">
                    <img src="${pageContext.request.contextPath}/DBImages/${movie.image}"
                         alt="${movie.movieName}"
                         class="rounded-lg w-full md:w-56 h-auto object-cover shadow-lg">
                </div>

                <!-- Movie and Booking Details -->
                <div class="mt-4 md:mt-0 md:ml-6">
                    <h2 class="text-2xl font-bold mb-2">${movie.movieName}</h2>
                    <p class="text-gray-600">${movie.description}</p>
                    <p class="mt-1">
                        <strong>Show Time:</strong>
                            ${showTime.showTime} on ${showDate.showDate}
                    </p>
                    <p class="mt-1">
                        <strong>Ticket Price:</strong> $${movie.ticketPrice}
                    </p>

                    <!-- Form to create booking (which will then lead to Stripe payment) -->
                    <form action="${pageContext.request.contextPath}/booking?action=create" method="post" class="mt-6">
                        <!-- Hidden fields -->
                        <input type="hidden" name="movieId" value="${movie.movieId}">
                        <input type="hidden" name="showDate" value="${showDate.showDate}">
                        <input type="hidden" name="showTime" value="${showTime.showTime}">
                        <input type="hidden" name="ticketPrice" id="ticketPrice" value="${movie.ticketPrice}">

                        <!-- Number of Seats -->
                        <div class="mb-4">
                            <label for="numberOfSeats" class="block text-gray-700 font-bold mb-2">
                                Number of Seats:
                            </label>
                            <input type="number"
                                   id="numberOfSeats"
                                   name="numberOfSeats"
                                   value="1"
                                   min="1"
                                   max="10"
                                   class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700
                                          leading-tight focus:outline-none focus:shadow-outline"
                                   oninput="calculateTotal()">
                        </div>

                        <!-- Total Price (auto-calculated client-side) -->
                        <div class="mb-4">
                            <label for="totalPrice" class="block text-gray-700 font-bold mb-2">
                                Total Price:
                            </label>
                            <input type="text"
                                   id="totalPrice"
                                   name="totalPrice"
                                   readonly
                                   class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700
                                          leading-tight focus:outline-none focus:shadow-outline"
                                   value="${movie.ticketPrice}">
                        </div>

                        <!-- Submit Button (Book Now) -->
                        <button type="submit"
                                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded
                                       focus:outline-none focus:shadow-outline">
                            Book Now
                        </button>
                    </form>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!-- Include Footer -->
<div>
    <jsp:include page="/components/footer.jsp" />
</div>

<!-- JS to update total price when seat count changes -->
<script>
    function calculateTotal() {
        const ticketPrice = parseFloat(document.getElementById("ticketPrice").value);
        const numberOfSeats = parseInt(document.getElementById("numberOfSeats").value);
        if (!isNaN(ticketPrice) && !isNaN(numberOfSeats) && numberOfSeats > 0) {
            const totalPrice = ticketPrice * numberOfSeats;
            document.getElementById("totalPrice").value = totalPrice.toFixed(2);
        } else {
            document.getElementById("totalPrice").value = ticketPrice.toFixed(2);
        }
    }
</script>

</body>
</html>
