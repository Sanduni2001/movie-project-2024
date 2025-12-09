<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Movie</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 text-gray-800">

<div class="flex h-screen">
    <%-- Sidebar Include --%>
    <jsp:include page="/components/sidebar.jsp" />

    <%-- Content Area --%>
    <div class="w-3/4 bg-white p-6 overflow-auto">
        <h1 class="text-2xl font-bold mb-6 text-center">Add New Movie</h1>

        <%-- Form for Adding Movie Details --%>
        <form action="${pageContext.request.contextPath}/admin/movie-management" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="addMovie">

            <div class="mb-4">
                <label for="movieName" class="block font-bold text-gray-700">Movie Name</label>
                <input type="text" id="movieName" name="movieName" class="w-full border rounded px-4 py-2" placeholder="Enter movie name" required>
            </div>

            <div class="mb-4">
                <label for="description" class="block font-bold text-gray-700">Description</label>
                <textarea id="description" name="description" class="w-full border rounded px-4 py-2" rows="4" placeholder="Enter movie description" required></textarea>
            </div>

            <div class="mb-4">
                <label for="releaseDate" class="block font-bold text-gray-700">Release Date</label>
                <input type="date" id="releaseDate" name="releaseDate" class="w-full border rounded px-4 py-2" required>
            </div>

            <div class="mb-4">
                <label for="directorName" class="block font-bold text-gray-700">Director Name</label>
                <input type="text" id="directorName" name="directorName" class="w-full border rounded px-4 py-2" placeholder="Enter director name" required>
            </div>

            <div class="mb-4">
                <label for="musicBy" class="block font-bold text-gray-700">Music By</label>
                <input type="text" id="musicBy" name="musicBy" class="w-full border rounded px-4 py-2" placeholder="Enter composer's name" required>
            </div>

            <div class="mb-4">
                <label for="produceBy" class="block font-bold text-gray-700">Produced By</label>
                <input type="text" id="produceBy" name="produceBy" class="w-full border rounded px-4 py-2" placeholder="Enter producer's name" required>
            </div>

            <div class="mb-4">
                <label for="ticketPrice" class="block font-bold text-gray-700">Ticket Price</label>
                <input type="number" id="ticketPrice" name="ticketPrice" class="w-full border rounded px-4 py-2" placeholder="Enter ticket price" required>
            </div>

            <div class="mb-4">
                <label for="movieStatus" class="block font-bold text-gray-700">Movie Status</label>
                <select id="movieStatus" name="movieStatus" class="w-full border rounded px-4 py-2" required>
                    <option value="Now Showing">Now Showing</option>
                    <option value="Upcoming">Upcoming</option>
                </select>
            </div>

            <div class="mb-4">
                <label for="image" class="block font-bold text-gray-700">Movie Image</label>
                <input type="file" id="image" name="image" class="w-full border rounded px-4 py-2" required>
            </div>

            <%-- Submit Button --%>
            <div class="flex justify-end">
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">Save Movie</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
