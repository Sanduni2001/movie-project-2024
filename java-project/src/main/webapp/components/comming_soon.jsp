<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Movie Slider</title>
</head>
<body class="bg-gray-100">
<div class="container mx-auto mt-10">
    <h2 class="text-3xl font-bold text-gray-800 text-center mb-6">Coming Soon</h2>
    <div class="relative flex justify-center">

        <button id="prev" class="absolute left-0 top-1/2 transform -translate-y-1/2 bg-gray-200 text-black p-2 rounded-full shadow hover:bg-gray-400 disabled:opacity-50" disabled>
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
            </svg>
        </button>
        <button id="next" class="absolute right-0 top-1/2 transform -translate-y-1/2 bg-gray-200 text-black p-2 rounded-full shadow hover:bg-gray-400 disabled:opacity-50">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
            </svg>
        </button>


        <div id="slider" class="flex overflow-x-scroll space-x-6 transition-transform scrollbar-hidden ml-20 mr-20">

            <div class="w-60 h-96 flex-shrink-0 bg-white rounded-lg shadow-lg flex flex-col">
                <div class="h-3/4">
                    <img src="./assets/images/cs1.png" alt="JOKER" class="rounded-t-lg w-full h-full object-cover">
                </div>
                <div class="p-4 h-1/4 flex flex-col justify-between">
                    <h2 class="text-lg font-bold mb-2 text-black text-center">JOKER</h2>
                    <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 open-coming-soon-modal" data-coming-soon-details="
                        <p>A dark, gritty origin story for Gotham's notorious villain.</p>
                        <p><strong>Release date:</strong> TBA</p>
                        <p><strong>Director:</strong> Todd Phillips</p>
                        <p><strong>Distributed by:</strong> Warner Bros.</p>
                    ">More Info</button>
                </div>
            </div>
            <div class="w-64 h-96 flex-shrink-0 bg-white rounded-lg shadow-lg flex flex-col">
                <div class="h-3/4">
                    <img src="./assets/images/cs2.png" alt="Kung Fu Panda" class="rounded-t-lg w-full h-full object-cover">
                </div>
                <div class="p-4 h-1/4 flex flex-col justify-between">
                    <h2 class="text-lg font-bold mb-2 text-black text-center">KUNG FU PANDA</h2>
                    <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 open-coming-soon-modal" data-coming-soon-details="
                        <p>The hilarious adventures of Po, the Dragon Warrior.</p>
                        <p><strong>Release date:</strong> TBA</p>
                        <p><strong>Director:</strong> Jennifer Yuh</p>
                        <p><strong>Distributed by:</strong> DreamWorks Animation</p>
                    ">More Info</button>
                </div>
            </div>
            <div class="w-64 h-96 flex-shrink-0 bg-white rounded-lg shadow-lg flex flex-col">
                <div class="h-3/4">
                    <img src="./assets/images/cs3.png" alt="Batman" class="rounded-t-lg w-full h-full object-cover">
                </div>
                <div class="p-4 h-1/4 flex flex-col justify-between">
                    <h2 class="text-lg font-bold mb-2 text-black text-center">BATMAN</h2>
                    <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 open-coming-soon-modal" data-coming-soon-details="
                        <p>The Caped Crusader's mission to protect Gotham.</p>
                        <p><strong>Release date:</strong> TBA</p>
                        <p><strong>Director:</strong> Matt Reeves</p>
                        <p><strong>Distributed by:</strong> Warner Bros.</p>
                    ">More Info</button>
                </div>
            </div>
            <div class="w-64 h-96 flex-shrink-0 bg-white rounded-lg shadow-lg flex flex-col">
                <div class="h-3/4">
                    <img src="./assets/images/cs4.png" alt="The Longest Ride" class="rounded-t-lg w-full h-full object-cover">
                </div>
                <div class="p-4 h-1/4 flex flex-col justify-between">
                    <h2 class="text-lg font-bold mb-2 text-black text-center">THE LONGEST RIDE</h2>
                    <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 open-coming-soon-modal" data-coming-soon-details="
                        <p>An emotional rollercoaster of love and loss.</p>
                        <p><strong>Release date:</strong> TBA</p>
                        <p><strong>Director:</strong> George Tillman Jr.</p>
                        <p><strong>Distributed by:</strong> 20th Century Fox</p>
                    ">More Info</button>
                </div>
            </div>
            <div class="w-64 h-96 flex-shrink-0 bg-white rounded-lg shadow-lg flex flex-col">
                <div class="h-3/4">
                    <img src="./assets/images/cs5.png" alt="Avatar" class="rounded-t-lg w-full h-full object-cover">
                </div>
                <div class="p-4 h-1/4 flex flex-col justify-between">
                    <h2 class="text-lg font-bold mb-2 text-black text-center">TALKING TO GHOSTS</h2>
                    <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 open-coming-soon-modal" data-coming-soon-details="
                        <p>Explore the stunning world of Pandora.</p>
                        <p><strong>Release date:</strong> TBA</p>
                        <p><strong>Director:</strong> James Cameron</p>
                        <p><strong>Distributed by:</strong> 20th Century Studios</p>
                    ">More Info</button>
                </div>
            </div>
            <div class="w-64 h-96 flex-shrink-0 bg-white rounded-lg shadow-lg flex flex-col">
                <div class="h-3/4">
                    <img src="./assets/images/cs6.png" alt="Inception" class="rounded-t-lg w-full h-full object-cover">
                </div>
                <div class="p-4 h-1/4 flex flex-col justify-between">
                    <h2 class="text-lg font-bold mb-2 text-black text-center">SUM 1</h2>
                    <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 open-coming-soon-modal" data-coming-soon-details="
                        <p>A thrilling dive into the world of dreams.</p>
                        <p><strong>Release date:</strong> TBA</p>
                        <p><strong>Director:</strong> Christopher Nolan</p>
                        <p><strong>Distributed by:</strong> Warner Bros.</p>
                    ">More Info</button>
                </div>
            </div>
            <div class="w-64 h-96 flex-shrink-0 bg-white rounded-lg shadow-lg flex flex-col">
                <div class="h-3/4">
                    <img src="./assets/images/cs7.png" alt="Frozen" class="rounded-t-lg w-full h-full object-cover">
                </div>
                <div class="p-4 h-1/4 flex flex-col justify-between">
                    <h2 class="text-lg font-bold mb-2 text-black text-center">ENGLISH VINGLISH</h2>
                    <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 open-coming-soon-modal" data-coming-soon-details="
                        <p>An icy tale of love and adventure.</p>
                        <p><strong>Release date:</strong> TBA</p>
                        <p><strong>Director:</strong> Chris Buck</p>
                        <p><strong>Distributed by:</strong> Disney</p>
                    ">More Info</button>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="coming_soon_popup_container" class="hidden fixed inset-0 z-50 flex items-center justify-center">
    <div id="coming_soon_overlay" class="absolute inset-0 bg-black bg-opacity-70" onclick="closeComingSoonPopup()"></div>
    <div id="coming_soon_popup" class="relative bg-white p-6 rounded-lg shadow-lg max-w-md mx-auto">
        <h2 id="coming_soon_popup_title" class="text-2xl font-bold mb-4 text-black"></h2>
        <p id="coming_soon_popup_description" class="text-gray-700 mb-6"></p>
        <button class="rounded bg-red-500 py-2 px-4 text-white hover:bg-red-600" onclick="closeComingSoonPopup()">Close</button>
    </div>
</div>

<script>
    const slider = document.getElementById("slider");
    const prevButton = document.getElementById("prev");
    const nextButton = document.getElementById("next");

    const cardWidth = 300; // Width of a single card including margin
    const maxScroll = slider.scrollWidth - slider.clientWidth;

    function updateButtons() {
        prevButton.disabled = slider.scrollLeft <= 0;
        nextButton.disabled = slider.scrollLeft >= maxScroll;
    }

    nextButton.addEventListener("click", () => {
        slider.scrollBy({ left: cardWidth, behavior: "smooth" });
        setTimeout(updateButtons, 300); // Update button states after scroll
    });

    prevButton.addEventListener("click", () => {
        slider.scrollBy({ left: -cardWidth, behavior: "smooth" });
        setTimeout(updateButtons, 300); // Update button states after scroll
    });

    slider.addEventListener("scroll", updateButtons);

    document.body.addEventListener("click", function (event) {
        if (event.target.classList.contains("open-coming-soon-modal")) {
            const movieDetails = event.target.getAttribute("data-coming-soon-details");
            const popupTitle = event.target.previousElementSibling.innerText;

            document.getElementById("coming_soon_popup_title").innerHTML = popupTitle;
            document.getElementById("coming_soon_popup_description").innerHTML = movieDetails;
            document.getElementById("coming_soon_popup_container").classList.remove("hidden");
        }
    });

    function closeComingSoonPopup() {
        document.getElementById("coming_soon_popup_container").classList.add("hidden");
    }
</script>
</body>
</html>