<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/output.css">
    <script src="https://cdn.tailwindcss.com"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - ABC Cinema</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<script src="https://cdn.jsdelivr.net/npm/@material-tailwind/html@latest/dist/material-tailwind.min.js"></script>

<!-- Navbar -->
<nav>
    <jsp:include page="/components/navbar.jsp" />
</nav>

<div class="max-w-5xl mx-auto p-8 bg-white shadow-lg rounded-lg mt-12">
    <!-- Header Section -->
    <header class="text-center">
        <h1 class="text-4xl font-bold mb-6 text-gray-800">ABOUT ABC CINEMA</h1>
        <img src="${pageContext.request.contextPath}/assets/images/Aboutus.png" alt="Cinema Screen" class="w-full rounded-lg shadow-lg mb-6">
        <p class="text-lg text-gray-600">
            At ABC Cinema, we redefine the movie experience in Sri Lanka by combining cutting-edge technology with modern comfort and exceptional service. Join us in creating memories that last a lifetime!
        </p>
    </header>

    <!-- Mission and Vision Section -->
    <section class="mt-10">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <!-- Mission -->
            <div class="bg-gradient-to-br from-blue-500 to-blue-300 text-white p-6 rounded-lg shadow-md">
                <h2 class="text-2xl font-bold mb-4">MISSION</h2>
                <p class="text-lg">
                    To deliver unforgettable movie experiences by showcasing diverse films, embracing innovation, and creating a welcoming space where every visitor feels entertained, connected, and valued.
                </p>
            </div>
            <!-- Vision -->
            <div class="bg-gradient-to-br from-green-500 to-green-300 text-white p-6 rounded-lg shadow-md">
                <h2 class="text-2xl font-bold mb-4">VISION</h2>
                <p class="text-lg">
                    To become the most beloved destination for cinematic experiences, inspiring audiences with unparalleled storytelling, cutting-edge technology, and exceptional hospitality.
                </p>
            </div>
        </div>
    </section>

    <!-- Locations Section -->
    <section class="bg-gray-50 p-6 rounded-lg shadow-md mt-10">
        <h3 class="text-2xl font-bold mb-4 text-gray-800">Our Location</h3>
        <div class="space-y-4">
            <!-- Address -->
            <p class="text-lg flex items-center">
                <img src="${pageContext.request.contextPath}/assets/logos/location.png" alt="Location" class="w-6 h-6 mr-3">
                <span class="font-medium">ABC Cinema, Galle Road, Colombo</span>
            </p>
            <!-- Contact -->
            <p class="text-lg flex items-center">
                <img src="${pageContext.request.contextPath}/assets/logos/contact.png" alt="Contact" class="w-6 h-6 mr-3">
                <span class="font-medium">011 8 209 184</span>
            </p>
            <!-- Email -->
            <p class="text-lg flex items-center">
                <img src="${pageContext.request.contextPath}/assets/logos/email.png" alt="Email" class="w-6 h-6 mr-3">
                <span class="font-medium">abccinema@gmail.com</span>
            </p>
        </div>
    </section>

    <!-- Team Section -->
    <section class="mt-12">
        <h3 class="text-2xl font-bold text-gray-800 mb-6 text-center">Our Team</h3>
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
            <!-- Team Member -->
            <div class="text-center">
                <img src="${pageContext.request.contextPath}/assets/images/person1.jpeg" alt="John Doe" class="rounded-full w-24 h-24 mx-auto shadow-md mb-4">
                <h4 class="text-xl font-bold">John Doe</h4>
                <p class="text-gray-600">CEO</p>
            </div>
            <div class="text-center">
                <img src="${pageContext.request.contextPath}/assets/images/person2.jpeg" alt="Jane Smith" class="rounded-full w-24 h-24 mx-auto shadow-md mb-4">
                <h4 class="text-xl font-bold">Jane Smith</h4>
                <p class="text-gray-600">Manager</p>
            </div>
            <div class="text-center">
                <img src="${pageContext.request.contextPath}/assets/images/person4.jpeg" alt="Emily Davis" class="rounded-full w-24 h-24 mx-auto shadow-md mb-4">
                <h4 class="text-xl font-bold">Emily Davis</h4>
                <p class="text-gray-600">Customer Support</p>
            </div>
        </div>
    </section>
</div>

<!-- Footer -->
<footer class="mt-12">
    <jsp:include page="/components/footer.jsp" />
</footer>

</body>
</html>
