<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Dates and Show Times</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 text-gray-800">

<div class="flex h-screen">
    <%-- Sidebar Include --%>
    <jsp:include page="/components/sidebar.jsp" />

    <%-- Content Area --%>
    <div class="w-3/4 bg-white p-6 overflow-auto">
        <h1 class="text-2xl font-bold mb-6 text-center">Add Dates and Show Times</h1>

        <%-- Add Date Form --%>
        <form action="${pageContext.request.contextPath}/admin/movie-management" method="post">
            <input type="hidden" name="action" value="addDatesTimes">
            <input type="hidden" name="movieId" value="${movieId}">

            <div class="mb-4">
                <label for="showDate" class="block font-bold text-gray-700">Add Show Date</label>
                <input type="date" id="showDate" name="showDate" class="w-full border rounded px-4 py-2" required>
            </div>

            <div class="mb-4">
                <label class="block font-bold text-gray-700">Select Show Times</label>
                <div class="space-y-2">
                    <label class="flex items-center">
                        <input type="checkbox" name="showTimes" value="09:00 AM" class="mr-2"> 09:00 AM
                    </label>
                    <label class="flex items-center">
                        <input type="checkbox" name="showTimes" value="01:00 PM" class="mr-2"> 01:00 PM
                    </label>
                    <label class="flex items-center">
                        <input type="checkbox" name="showTimes" value="04:00 PM" class="mr-2"> 04:00 PM
                    </label>
                    <label class="flex items-center">
                        <input type="checkbox" name="showTimes" value="09:00 PM" class="mr-2"> 09:00 PM
                    </label>
                </div>
            </div>

            <div class="flex justify-end mb-6">
                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">
                    Add Show Date & Times
                </button>
            </div>
        </form>

        <%-- Existing Dates and Show Times Section --%>
        <c:if test="${not empty showDates}">
            <h2 class="text-xl font-bold mb-4">Existing Show Dates</h2>
            <div class="space-y-4">
                <c:forEach var="date" items="${showDates}">
                    <div class="p-4 bg-gray-100 rounded-lg shadow">
                        <h3 class="text-lg font-bold mb-2">Show Date: ${date.showDate}</h3>
                        <c:if test="${not empty date.showTimes}">
                            <h4 class="text-md font-bold mt-2">Existing Show Times:</h4>
                            <ul class="list-disc list-inside">
                                <c:forEach var="time" items="${date.showTimes}">
                                    <li>${time.showTime}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </c:if>


    </div>
</div>

</body>
</html>
