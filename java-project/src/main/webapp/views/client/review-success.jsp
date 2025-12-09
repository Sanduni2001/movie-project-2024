<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Review Submitted</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-800 text-gray-100 flex items-center justify-center h-screen">

<div class="bg-gray-900 rounded-lg shadow-lg p-8 max-w-md text-center">
    <!-- Success Icon -->
    <div class="flex justify-center mb-6">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16 text-green-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2l4-4M7 16l-4-4m0 0l4-4m-4 4h18" />
        </svg>
    </div>

    <!-- Success Message -->
    <h1 class="text-2xl font-bold text-green-400 mb-4">Review Submitted Successfully!</h1>
    <p class="text-gray-300 mb-6">
        Thank you for your feedback! Your review has been successfully submitted.
    </p>

    <!-- Redirect Button -->
    <a href="${pageContext.request.contextPath}/home" class="w-full inline-block bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
        Go to Home
    </a>
</div>

</body>
</html>
