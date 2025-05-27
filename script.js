// script.js

document.addEventListener("DOMContentLoaded", () => {
  // Slideshow logic
  const slides = [
    //first slide
    {
      src: "screenshots/webm/LaunchtoTabNavTour.webm",
      caption: "From launch, the app opens on the snake identification interface. Navigation is intuitive with tap icons at the top of the screen.",
    },
    {
      src: "screenshots/webm/SnakeIDSequenceSmall.webm",
      caption: "Fully interactive snake ID interface based on human-observable traits. Filtered match results are generated based on user input and an internal scoring algorithm.",
    },
    {
      src: "screenshots/webm/MySnakestoSnakeDetailView.webm",
      caption: "Confirmed snakes are saved to My Snakes, which is an interactive list that persists across sessions. Users can then click on a saved snake to learn more.",
    },
    {
      src: "screenshots/webm/ExploreAll.webm",
      caption: "Explore All allows users to scroll through all snakes in the database, allowing them to click each snake to learn more. Venomous snakes are highlighted in red.",
    },
    {
      src: "screenshots/webm/HomeScreen.webm",
      caption: "The Home screen serves as a landing pad, inviting the user to learn more about a randomly featured snake, and also allowing them to access the snake ID interface at the press of a button.",
    },

  ];

  let currentSlide = 0;
  const slideContainer = document.getElementById("slideMediaContainer");
  const slideCaption = document.getElementById("slideCaption");
  const prevBtn = document.getElementById("prevSlide");
  const nextBtn = document.getElementById("nextSlide");

  function updateSlide(index) {
    const video = document.getElementById("slideVideo");
    const caption = document.getElementById("slideCaption");

    // Fade out
    video.style.opacity = 0;

    setTimeout(() => {
      video.src = slides[index].src;
      caption.textContent = slides[index].caption;
      video.load();
      video.play();

      // Fade back in
      video.style.opacity = 1;
    }, 150);

    document.getElementById("slideCounter").textContent = `${index + 1} of ${slides.length}`;

  }


  prevBtn.addEventListener("click", () => {
    currentSlide = (currentSlide - 1 + slides.length) % slides.length;
    updateSlide(currentSlide);
  });

  nextBtn.addEventListener("click", () => {
    currentSlide = (currentSlide + 1) % slides.length;
    updateSlide(currentSlide);
  });

  // Initialize first slide
  updateSlide(currentSlide);
});
