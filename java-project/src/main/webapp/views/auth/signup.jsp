<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/style.css">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Sign Up</title>
    <style>
        .error {
            color: red;
            font-size: 0.875rem;
            margin-top: 0.25rem;
        }
    </style>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<nav class="bg-[#070315] text-white py-3 shadow-lg">
    <div class="container mx-auto flex justify-between items-center">
        <div class="text-xl font-bold">ABC Cinema</div>
        <a href="<%= request.getContextPath() %>/auth/login" class="px-4 py-2 bg-blue-600 rounded-lg hover:bg-blue-500 focus:outline-none">Back</a>
    </div>
</nav>

<!-- Auth Container -->
<div class="flex items-center justify-center min-h-screen bg-gray-100">
    <div class="flex flex-col md:flex-row shadow-lg bg-white rounded-lg overflow-hidden w-full max-w-4xl fade">

        <!-- Register Form -->
        <div id="registerForm" class="w-full md:w-2/3 p-8">
            <h2 class="text-2xl font-semibold text-gray-700">Create Your Account</h2>
            <p class="text-sm text-gray-500 mb-4">Sign up to get started</p>

            <!-- Error Message -->
            <c:if test="${not empty error}">
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-2 rounded relative mb-4">
                    <strong class="font-bold">Error:</strong> <span>${error}</span>
                </div>
            </c:if>

            <!-- Signup Form -->
            <form id="registerFormElement" action="register" method="post">
                <div class="mb-4">
                    <label for="fullname" class="block text-sm font-medium text-gray-600">Full Name</label>
                    <input
                            type="text"
                            id="fullname"
                            name="fullname"
                            class="w-full px-4 py-2 mt-1 text-sm text-gray-700 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Full Name"
                            required
                    />
                </div>
                <div class="mb-4">
                    <label for="email" class="block text-sm font-medium text-gray-600">Email</label>
                    <input
                            type="email"
                            id="email"
                            name="email"
                            class="w-full px-4 py-2 mt-1 text-sm text-gray-700 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Email"
                            required
                    />
                </div>
                <div class="mb-4">
                    <label for="mobile" class="block text-sm font-medium text-gray-600">Mobile</label>
                    <input
                            type="text"
                            id="mobile"
                            name="mobile"
                            class="w-full px-4 py-2 mt-1 text-sm text-gray-700 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Mobile"
                            required
                    />
                </div>
                <div class="mb-4">
                    <label for="password" class="block text-sm font-medium text-gray-600">Password</label>
                    <input
                            type="password"
                            id="password"
                            name="password"
                            class="w-full px-4 py-2 mt-1 text-sm text-gray-700 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Password"
                            required
                    />
                </div>
                <div class="mb-4">
                    <label for="confirmPassword" class="block text-sm font-medium text-gray-600">Confirm Password</label>
                    <input
                            type="password"
                            id="confirmPassword"
                            name="confirmPassword"
                            class="w-full px-4 py-2 mt-1 text-sm text-gray-700 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                            placeholder="Confirm Password"
                            required
                    />
                </div>
                <button
                        type="submit"
                        class="w-full px-4 py-2 text-white bg-[#1F2B47] rounded-lg hover:bg-[#172139] focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-1"
                >
                    Sign Up
                </button>
            </form>
        </div>

        <!-- Sidebar -->
        <div class="w-full md:w-1/3 bg-[#1F2B47] text-white p-6 flex flex-col justify-center items-center">
            <h2 class="text-lg font-bold">Already a Member?</h2>
            <p class="text-sm mt-2">Login and continue!</p>
            <a
                    href="login"
                    class="mt-4 px-4 py-2 bg-white text-blue-800 rounded-lg shadow hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-white"
            >
                Sign In
            </a>
        </div>

    </div>
</div>

</body>
</html>
