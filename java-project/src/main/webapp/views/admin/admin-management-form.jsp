<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${action == 'update' ? 'Update Admin' : 'Add New Admin'}</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 text-gray-900">

<div class="flex h-screen">
    <%-- Sidebar Include --%>
    <jsp:include page="/components/sidebar.jsp" />

    <%-- Content Section --%>
    <div class="w-full md:w-3/4 p-6 bg-white overflow-auto">
        <h1 class="text-2xl font-bold mb-6 text-center">
            ${action == 'update' ? 'Update Admin' : 'Add New Admin'}
        </h1>

        <%-- Error Handling --%>
        <c:if test="${not empty errorMessage}">
            <div class="bg-red-100 text-red-700 p-4 rounded-md mb-4">
                    ${errorMessage}
            </div>
        </c:if>

        <%-- Admin Form --%>
        <form action="<%= request.getContextPath() %>/admin/admin-management" method="post" class="space-y-4">
            <%-- Hidden Input for Action --%>
            <input type="hidden" name="action" value="${action}" />

            <%-- Hidden Input for Admin ID in Update Context --%>
            <c:if test="${action == 'update'}">
                <input type="hidden" name="id" value="${admin.id}" />
            </c:if>

            <%-- Username Field --%>
            <div>
                <label for="username" class="block text-sm font-medium text-gray-700">Username</label>
                <input
                        type="text"
                        id="username"
                        name="username"
                        class="w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        value="${action == 'update' ? admin.username : ''}"
                        required
                />
            </div>

            <%-- Email Field --%>
            <div>
                <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                <input
                        type="email"
                        id="email"
                        name="email"
                        class="w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        value="${action == 'update' ? admin.email : ''}"
                        required
                />
            </div>

            <%-- Mobile Field --%>
            <div>
                <label for="mobile" class="block text-sm font-medium text-gray-700">Mobile</label>
                <input
                        type="text"
                        id="mobile"
                        name="mobile"
                        class="w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        value="${action == 'update' ? admin.mobile : ''}"
                        required
                />
            </div>

            <%-- Password Field --%>
            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">
                    Password <c:if test="${action == 'update'}">(Leave blank to keep current password)</c:if>
                </label>
                <input
                        type="password"
                        id="password"
                        name="password"
                        class="w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                <%-- Make it optional in update context --%>
                <%-- required="${action == 'add'}" --%>
                />
            </div>

            <%-- Submit Button --%>
            <div class="flex justify-end">
                <button
                        type="submit"
                        class="bg-blue-500 text-white px-6 py-2 rounded-lg hover:bg-blue-600"
                >
                    ${action == 'update' ? 'Update Admin' : 'Add Admin'}
                </button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
