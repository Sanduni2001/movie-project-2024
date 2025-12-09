<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Reviews</title>
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

        <%-- Reviews Table Section --%>
        <div class="my-8">
            <h2 class="text-3xl font-bold text-gray-800 mb-6">Review Management</h2>

            <%-- Check if reviews exist --%>
            <c:choose>
                <c:when test="${not empty reviews}">
                    <%-- Reviews Table --%>
                    <div class="bg-white shadow-md rounded-lg overflow-hidden overflow-x-auto">
                        <table class="w-full">
                            <thead class="bg-gray-100 border-b-2 border-gray-200">
                            <tr>
                                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">#</th>
                                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Username</th>
                                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Message</th>
                                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Stars</th>
                                <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Created At</th>
                            </tr>
                            </thead>
                            <tbody class="divide-y divide-gray-200">
                            <c:forEach var="review" items="${reviews}" varStatus="status">
                                <tr class="hover:bg-gray-50 transition duration-200 ${status.index % 2 == 0 ? 'bg-white' : 'bg-gray-50'}">
                                    <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${status.index + 1}</td>
                                    <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${user.username}</td>
                                    <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${user.email}</td>
                                    <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${review.description}</td>
                                    <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${review.stars} ★</td>
                                    <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${review.createDate}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="text-center text-gray-500">No reviews found. Please check back later.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
