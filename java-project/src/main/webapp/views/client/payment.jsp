<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/output.css">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Payment Page</title>
</head>
<body class="bg-gray-100 min-h-screen flex flex-col">

<!-- Navbar -->
<nav>
    <jsp:include page="/components/navbar.jsp" />
</nav>

<!-- Main Content -->
<div class="flex items-center justify-center flex-grow">
    <div class="bg-white shadow-lg rounded-lg p-8 max-w-md w-full relative">
        <!-- Header Section -->
        <div class="bg-gradient-to-r from-blue-700 to-blue-900 rounded-t-lg py-6 text-center">
            <img src="${pageContext.request.contextPath}/assets/logos/atm.png"
                 alt="Card Icon" class="w-16 h-16 mx-auto">
            <h1 class="text-white font-bold text-xl mt-2">Secure Payment</h1>
        </div>

        <!-- Payment Form -->
        <form class="mt-6 space-y-6" action="${pageContext.request.contextPath}/pay-now" method="post">
        <!-- Email Input -->
            <div>
                <label for="email" class="block text-sm font-medium text-gray-700 mb-1">Email</label>
                <input
                        type="email"
                        id="email"
                        placeholder="Your Email"
                        class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                />
            </div>

            <!-- Card Details -->
            <div>
                <label for="card-details" class="block text-sm font-medium text-gray-700 mb-1">Card Number</label>
                <input
                        type="text"
                        id="card-details"
                        placeholder="1234 5678 9012 3456"
                        class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                />
            </div>

            <!-- Expiration Date and CVV -->
            <div class="grid grid-cols-2 gap-4">
                <div>
                    <label for="expiration-date" class="block text-sm font-medium text-gray-700 mb-1">Expiry Date</label>
                    <input
                            type="text"
                            id="expiration-date"
                            placeholder="MM/YY"
                            class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                    />
                </div>
                <div>
                    <label for="cvv" class="block text-sm font-medium text-gray-700 mb-1">CVV</label>
                    <input
                            type="text"
                            id="cvv"
                            placeholder="123"
                            class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                    />
                </div>
            </div>

            <!-- Card Holder Name -->
            <div>
                <label for="holder-name" class="block text-sm font-medium text-gray-700 mb-1">Card Holder Name</label>
                <input
                        type="text"
                        id="holder-name"
                        placeholder="e.g John Doe"
                        class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                />
            </div>

            <!-- Pay Now Button -->
            <button
                    type="submit"
                    class="w-full bg-gradient-to-r from-blue-700 to-blue-900 text-white py-3 rounded-lg font-semibold hover:from-blue-800 hover:to-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-600"
            >
                Pay Now
            </button>
        </form>

        <!-- Secure Payment Information -->
        <div class="mt-6 text-sm text-gray-500 flex items-center justify-center">
            <img src="${pageContext.request.contextPath}/assets/logos/lock.png" alt="Secure Icon" class="h-5 w-5 mr-2">
            <span>Your payments are secure and encrypted</span>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="bg-gray-800 text-gray-200 py-4 mt-auto">
    <jsp:include page="/components/footer.jsp" />
</footer>

</body>
</html>
