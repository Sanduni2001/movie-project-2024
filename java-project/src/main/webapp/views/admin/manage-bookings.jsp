<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Bookings</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body class="bg-gray-100 text-gray-800">

<div class="flex h-screen">
    <%-- Sidebar --%>
    <jsp:include page="/components/sidebar.jsp"/>

    <%-- Content Area --%>
    <div class="w-3/4 bg-white p-6 overflow-auto">
        <%-- Navbar --%>

        <%-- Bookings Table Section --%>
        <div class="my-8">
            <h2 class="text-3xl font-bold text-gray-800 mb-6">Booking Management</h2>

            <%-- Bookings Table --%>
            <div class="bg-white shadow-md rounded-lg overflow-hidden overflow-x-auto">
                <table class="w-full">
                    <thead class="bg-gray-100 border-b-2 border-gray-200">
                    <tr>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">#</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">User</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Movie</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Show Date</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Show Time</th>
                        <th class="px-4 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Seats</th>
                        <th class="px-4 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Total Price</th>
                        <th class="px-4 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
                    </tr>
                    </thead>
                    <tbody class="divide-y divide-gray-200">
                    <c:forEach var="booking" items="${bookings}" varStatus="status">
                        <tr class="hover:bg-gray-50 transition duration-200 ${status.index % 2 == 0 ? 'bg-white' : 'bg-gray-50'}">
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${status.index + 1}</td>
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">
                                    ${user.username} <br>
                                <span class="text-xs text-gray-500">${user.email}</span>
                            </td>
                            <td class="px-4 py-4 whitespace-nowrap">
                                <div class="text-sm font-medium text-gray-900">${movie.movieName}</div>
                            </td>
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${booking.showDate}</td>
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${booking.showTime}</td>
                            <td class="px-4 py-4 text-center text-sm text-gray-600">${booking.numberOfSeats}</td>
                            <td class="px-4 py-4 text-right text-sm text-gray-600">$${booking.totalPrice}</td>
                            <td class="px-4 py-4 text-center">
                                <form action="${pageContext.request.contextPath}/admin/booking-management" method="post"
                                      onsubmit="return confirm('Are you sure you want to delete this booking?');">
                                    <input type="hidden" name="action" value="deleteBooking"/>
                                    <input type="hidden" name="bookingId" value="${booking.bookingId}"/>
                                    <button type="submit"
                                            class="text-red-600 hover:text-red-900 transition duration-300 ease-in-out">
                                        Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
