<aside class="w-1/4 bg-gray-800 text-white flex flex-col justify-between">
    <div>
        <h1 class="text-3xl font-bold text-center py-6">Admin Panel</h1>
        <ul class="space-y-4 px-4">
            <li>
                <a href="${pageContext.request.contextPath}/admin/home"
                   class="block py-2 px-4 text-lg font-semibold hover:bg-gray-700 rounded-lg">Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/bookings"
                   class="block py-2 px-4 text-lg font-semibold hover:bg-gray-700 rounded-lg">Bookings</a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/admin/user-management"
                   class="block py-2 px-4 text-lg font-semibold hover:bg-gray-700 rounded-lg">Users</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/admin-management"
                   class="block py-2 px-4 text-lg font-semibold hover:bg-gray-700 rounded-lg">Admins</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/manage-reviews"
                   class="block py-2 px-4 text-lg font-semibold hover:bg-gray-700 rounded-lg">Reviews</a>
            </li>

        </ul>
    </div>
    <div class="p-4">
        <a href="${pageContext.request.contextPath}/logout" class="block py-2 px-4 text-lg font-semibold hover:bg-gray-700 rounded-lg">
          Logout
        </a>
    </div>
</aside>
