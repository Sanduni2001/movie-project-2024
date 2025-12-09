<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Submit Your Review</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-800 text-gray-100">

<div class="container mx-auto py-12 px-4 lg:px-8">
    <div class="flex flex-col lg:flex-row bg-gray-900 rounded-lg shadow-lg overflow-hidden">
        <!-- Left-Side Image -->
        <div class="lg:w-1/2 flex items-center justify-center bg-gray-800">
            <img src="${pageContext.request.contextPath}/assets/images/feedback.jpg" alt="Review"
                 class="h-64 w-auto lg:h-auto lg:w-4/4 rounded-lg shadow-md">
        </div>

        <!-- Right-Side Form -->
        <div class="lg:w-1/2 p-10">
            <h2 class="text-3xl font-extrabold text-center mb-8 text-gray-100">Submit Your Review</h2>

            <!-- Review Form -->
            <form id="reviewForm" action="${pageContext.request.contextPath}/add-review" method="post">
                <!-- Description Field -->
                <div class="mb-8">
                    <label for="description" class="block text-lg font-semibold text-gray-300 mb-2">
                        Review Description
                    </label>
                    <textarea
                            id="description"
                            name="description"
                            class="block w-full rounded-lg border-2 border-blue-600 bg-gray-800 text-gray-200 text-lg p-4 focus:outline-none focus:ring-4 focus:ring-blue-400 focus:border-blue-500"
                            rows="5"
                            placeholder="Write your review here..."
                            required
                    ></textarea>
                </div>

                <!-- Star Rating Field -->
                <div class="mb-8">
                    <label for="stars" class="block text-lg font-semibold text-gray-300 mb-2">
                        Star Rating
                    </label>
                    <select
                            id="stars"
                            name="stars"
                            class="block w-full rounded-lg border-2 border-blue-600 bg-gray-800 text-gray-200 text-lg p-3 focus:outline-none focus:ring-4 focus:ring-blue-400 focus:border-blue-500"
                            required
                    >
                        <option value="" class="text-gray-400">Select a rating</option>
                        <option value="1">1 - Very Bad</option>
                        <option value="2">2 - Bad</option>
                        <option value="3">3 - Average</option>
                        <option value="4">4 - Good</option>
                        <option value="5">5 - Excellent</option>
                    </select>
                </div>

                <!-- Submit Button -->
                <button
                        type="submit"
                        class="w-full bg-blue-600 hover:bg-blue-700 text-white text-xl font-semibold py-3 px-6 rounded-lg shadow-md transition duration-300 focus:outline-none focus:ring-4 focus:ring-blue-500 focus:ring-offset-2"
                >
                    Submit Review
                </button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
