<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 text-gray-900">

<div class="flex h-screen">
    <%-- Sidebar Include --%>
    <jsp:include page="/components/sidebar.jsp" />

    <%-- Content Section --%>
    <div class="w-full md:w-3/4 p-6 bg-white overflow-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">User Management</h1>

        <%-- No Users Message --%>
        <c:if test="${empty clients}">
            <div class="bg-gray-100 p-6 rounded-lg shadow-md text-center">
                <p class="text-gray-600 text-lg">No users found.</p>
            </div>
        </c:if>

        <%-- Users Table Section --%>
        <c:if test="${not empty clients}">
            <div class="bg-white shadow-md rounded-lg overflow-hidden overflow-x-auto">
                <table class="w-full">
                    <thead class="bg-gray-100 border-b-2 border-gray-200">
                    <tr>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">#</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Username</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Mobile</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Role</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                    </tr>
                    </thead>
                    <tbody class="divide-y divide-gray-200">
                    <c:forEach var="user" items="${clients}" varStatus="status">
                        <tr class="hover:bg-gray-50 transition duration-200 ${status.index % 2 == 0 ? 'bg-white' : 'bg-gray-50'}">
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${status.index + 1}</td>
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${user.username}</td>
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${user.email}</td>
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${user.mobile}</td>
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${user.role}</td>
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">
                                <c:choose>
                                    <c:when test="${user.isActive}">
                                        <span class="text-green-500">Active</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-red-500">Inactive</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                           </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>

</body>
</html>
