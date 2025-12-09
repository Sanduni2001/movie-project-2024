<footer class="bg-gray-900 text-gray-400">
    <div class="mx-auto w-full max-w-screen-xl p-4 py-6 lg:py-8">
        <div class="md:flex md:justify-between">
            <!-- Logo Section -->
            <div class="mb-6 md:mb-0">
                <a href="${pageContext.request.contextPath}/index.jsp" class="flex items-center gap-4">
                    <img class="h-12 w-auto" src="${pageContext.request.contextPath}/assets/logos/logo.png" alt="ABC Cinema Logo">
                    <span class="text-2xl font-semibold text-white">ABC CINEMA</span>
                </a>
            </div>

            <!-- Grid Section -->
            <div class="grid grid-cols-2 gap-8 sm:grid-cols-3">
                <!-- Resources -->
                <div>
                    <h2 class="mb-6 text-sm font-semibold text-white uppercase">Resources</h2>
                    <ul class="space-y-2">
                        <li><a href="${pageContext.request.contextPath}/views/client/aboutus.jsp" class="hover:text-white hover:underline">About Us</a></li>
                        <li><a href="#" class="hover:text-white hover:underline">Location</a></li>
                    </ul>
                </div>
                <!-- Follow Us -->
                <div>
                    <h2 class="mb-6 text-sm font-semibold text-white uppercase">Follow us</h2>
                    <ul class="space-y-2">
                        <li><a href="#" class="hover:text-white hover:underline">Facebook</a></li>
                        <li><a href="#" class="hover:text-white hover:underline">Instagram</a></li>
                        <li><a href="#" class="hover:text-white hover:underline">Twitter</a></li>
                    </ul>
                </div>
                <!-- Legal -->
                <div>
                    <h2 class="mb-6 text-sm font-semibold text-white uppercase">Legal</h2>
                    <ul class="space-y-2">
                        <li><a href="#" class="hover:text-white hover:underline">Privacy Policy</a></li>
                        <li><a href="#" class="hover:text-white hover:underline">Terms &amp; Conditions</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <hr class="my-6 border-gray-700" />

        <!-- Bottom Section -->
        <div class="sm:flex sm:justify-between sm:items-center">
            <span class="text-sm sm:text-center">© 2024 ABC CINEMA. All Rights Reserved.</span>
            <div class="flex mt-4 sm:justify-center sm:mt-0 space-x-6">
                <a href="#" aria-label="Facebook" class="hover:text-white">
                    <img class="h-8 w-8" src="${pageContext.request.contextPath}/assets/logos/fb.png" alt="Facebook">
                </a>
                <a href="#" aria-label="Instagram" class="hover:text-white">
                    <img class="h-8 w-8" src="${pageContext.request.contextPath}/assets/logos/inster.png" alt="Instagram">
                </a>
                <a href="#" aria-label="Twitter" class="hover:text-white">
                    <img class="h-8 w-8" src="${pageContext.request.contextPath}/assets/logos/twiter.png" alt="Twitter">
                </a>
            </div>
        </div>
    </div>
</footer>
