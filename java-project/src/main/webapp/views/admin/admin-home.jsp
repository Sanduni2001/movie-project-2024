<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

</head>
<body class="bg-gray-100 text-gray-800">

<div class="flex h-screen">
    <%-- Sidebar --%>
    <jsp:include page="/components/sidebar.jsp"/>

    <%-- Content Area --%>
    <div class="w-3/4 bg-white p-6 overflow-auto">
        <%-- Navbar --%>

        <%-- Movies Table Section --%>
        <div class="my-8">
            <div class="flex justify-between items-center mb-6">
                <h2 class="text-3xl font-bold text-gray-800">Movie Management</h2>
                <a href="${pageContext.request.contextPath}/admin/movie-management?action=addMovie"
                   class="flex items-center bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-700 transform hover:-translate-y-1 shadow-lg transition-all duration-300">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" class="w-6 h-6 mr-2">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M12 9v6m3-3H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"/>
                    </svg>
                    Add New Movie
                </a>
            </div>

            <div class="bg-white shadow-md rounded-lg overflow-hidden overflow-x-auto">
                <table class="w-full">
                    <thead class="bg-gray-100 border-b-2 border-gray-200">
                    <tr>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">#</th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Movie
                            ID
                        </th>
                        <th class="px-4 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
                        <th class="px-4 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Poster
                        </th>
                        <th class="px-4 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Ticket
                            Price
                        </th>
                        <th class="px-4 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                            Actions
                        </th>
                    </tr>
                    </thead>
                    <tbody class="divide-y divide-gray-200">
                    <c:forEach var="movie" items="${movies}" varStatus="status">

                        <tr class="hover:bg-gray-50 transition duration-200 ${status.index % 2 == 0 ? 'bg-white' : 'bg-gray-50'}">
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${status.index + 1}</td>
                            <td class="px-4 py-4 whitespace-nowrap text-sm text-gray-600">${movie.movieId}</td>
                            <td class="px-4 py-4 whitespace-nowrap">
                                <div class="text-sm font-medium text-gray-900">${movie.movieName}</div>
                            </td>
                            <td class="px-4 py-4 text-center">
                                <img src="${pageContext.request.contextPath}/DBImages/${movie.image}"
                                     alt="Movie Poster"
                                     class="w-16 h-24 object-cover rounded-md mx-auto shadow-md">
                            </td>


                            <td class="px-4 py-4 text-right text-sm text-gray-600">
                                $${movie.ticketPrice}
                            </td>
                            <td class="px-4 py-4 text-center">
                                <div class="flex justify-center space-x-2">
                                    <form action="${pageContext.request.contextPath}/admin/movie-management" method="post"
                                          onsubmit="return confirm('Are you sure you want to delete this movie?');">
                                        <input type="hidden" name="action" value="deleteMovie"/>
                                        <input type="hidden" name="movieId" value="${movie.movieId}"/>
                                        <button type="submit"
                                                class="text-red-600 hover:text-red-900 transition duration-300 ease-in-out">
                                            Delete
                                        </button>
                                    </form>


                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>


        </div>
    </div>
</div>
</body>
</html>