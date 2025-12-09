<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Navbar -->
<nav class="bg-gray-900">
    <div class="container mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center h-16">

            <!-- Logo Section -->
            <div class="flex-shrink-0 flex items-center">
                <a href="/" class="flex items-center">
                    <img src="${pageContext.request.contextPath}/assets/logos/logo.png" alt="Logo" class="h-12 w-auto">
                    <span class="ml-2 text-white text-lg font-semibold">ABC Cinema</span>
                </a>
            </div>

            <!-- Navigation Links -->
            <div class="hidden sm:flex space-x-6">
                <a href="${pageContext.request.contextPath}/" class="text-gray-300 hover:text-white px-3 py-2 rounded-md text-sm font-medium">
                    Home
                </a>
                <a href="${pageContext.request.contextPath}/movies" class="text-gray-300 hover:text-white px-3 py-2 rounded-md text-sm font-medium">
                    Movies
                </a>
                <a href="${pageContext.request.contextPath}/about-us" class="text-gray-300 hover:text-white px-3 py-2 rounded-md text-sm font-medium">
                    About Us
                </a>
            </div>

            <!-- User Section -->
            <div class="hidden sm:flex items-center space-x-4">
                <c:choose>
                    <c:when test="${empty sessionScope.username}">
                        <!-- Not Logged In: Show Login -->
                        <a href="${pageContext.request.contextPath}/auth/login"
                           class="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md text-sm font-medium">
                            Login
                        </a>
                    </c:when>
                    <c:otherwise>
                        <!-- Logged In: Show Welcome, Cart, and Logout -->
                        <a href="${pageContext.request.contextPath}/booking?action=my-booking" class="text-gray-300 hover:text-white flex items-center">
                            <img src="${pageContext.request.contextPath}/assets/logos/cart.png" alt="Cart" class="h-8 w-8 mr-2">
                        </a>
                        <span class="text-gray-300 text-sm font-medium">Welcome, ${sessionScope.username}</span>
                        <a href="${pageContext.request.contextPath}/logout"
                           class="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-md text-sm font-medium">
                            Logout
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Responsive Menu Button (Mobile) -->
            <div class="sm:hidden flex items-center">
                <button class="text-gray-300 hover:text-white focus:outline-none">
                    <!-- Mobile menu toggle icon -->
                    <svg class="h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16m-7 6h7" />
                    </svg>
                </button>
            </div>
        </div>
    </div>
</nav>
