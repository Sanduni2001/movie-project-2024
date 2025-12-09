<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 text-gray-900">

<div class="flex h-screen">
    <%-- Sidebar Include --%>
    <jsp:include page="/components/sidebar.jsp" />

    <%-- Content Section --%>
    <div class="w-3/4 bg-white p-6 overflow-auto">
        <%-- Page Title and Add Admin Button --%>
        <div class="flex justify-between items-center mb-6">
            <h1 class="text-3xl font-bold text-gray-800">Admin Management</h1>
            <button
                    class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition duration-300"
                    onclick="window.location.href='<%= request.getContextPath() %>/admin/admin-management?action=add'">
                Add New Admin
            </button>
        </div>

        <%-- Admins Table Section --%>
        <div class="bg-white shadow-md rounded-lg overflow-hidden overflow-x-auto">
            <table class="w-full">
                <thead class="bg-gray-100 border-b-2 border-gray-200">
                <tr>
                    <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">#</th>
                    <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Username</th>
                    <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                    <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Mobile</th>
                    <th class="px-4 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
                </tr>
                </thead>
                <tbody class="divide-y divide-gray-200">
                <%-- Iterate through admins if available --%>
                <c:forEach var="admin" items="${admins}" varStatus="status">
                    <tr class="hover:bg-gray-50 transition duration-200 ${status.index % 2 == 0 ? 'bg-white' : 'bg-gray-50'}">
                        <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${status.index + 1}</td>
                        <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${admin.username}</td>
                        <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${admin.email}</td>
                        <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${admin.mobile}</td>
                        <td class="px-4 py-4 text-center">
                            <div class="flex justify-center space-x-4">
                                    <%-- Update Button --%>
                                <button
                                        class="text-green-500 font-semibold hover:text-green-700 transition duration-300"
                                        onclick="window.location.href='<%= request.getContextPath() %>/admin/admin-management?action=edit&id=${admin.id}'">
                                    Update
                                </button>
                                    <%-- Delete Button --%>
                                <button
                                        class="text-red-500 font-semibold hover:text-red-700 transition duration-300"
                                        onclick="confirmDelete(${admin.id})">
                                    Delete
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <%-- No Admins Found --%>
                <c:if test="${empty admins}">
                    <tr>
                        <td colspan="5" class="text-center px-4 py-4 text-gray-600">
                            No admins found.
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    /**
     * Confirm delete and redirect with the correct admin ID.
     * @param {number} adminId - The ID of the admin to delete.
     */
    function confirmDelete(adminId) {
        if (confirm("Are you sure you want to delete this admin?")) {
            window.location.href = `<%= request.getContextPath() %>/admin/admin-management?action=delete&id=` + adminId;
        }
    }
</script>

</body>
</html>
