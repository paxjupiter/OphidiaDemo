// script.js

document.addEventListener("DOMContentLoaded", () => {
  // Slideshow logic
  const slides = [
    {
      src: "screenshots/HomeScreen.png",
      caption: "Randomly featured species generated when home screen loads. User can tap to learn more.",
    },
    {
      src: "screenshots/IdSnake1.png",
      caption: "Fully interactive snake ID interface based on human-observable traits.",
    },
    {
      src: "screenshots/Results.png",
      caption: "Filtered match results based on user input and internal scoring algorithm.",
    },
    {
      src: "screenshots/DetailView1Refactor.png",
      caption: "Informative and digestible species detail view with offline-accessible images and metadata.",
    },
    {
      src: "screenshots/ExploreAll.png",
      caption: "Infinity scroll interface providing access to information about all snakes in the database.",
    },
    {
      src: "screenshots/MySnakesList.png",
      caption: "Users can save identified snakes to a favorites list that persists across sessions.",
    },
  ];

  let currentSlide = 0;
  const slideImage = document.getElementById("slideImage");
  const slideCaption = document.getElementById("slideCaption");
  const prevBtn = document.getElementById("prevSlide");
  const nextBtn = document.getElementById("nextSlide");

  function updateSlide(index) {
    slideImage.src = slides[index].src;
    slideCaption.textContent = slides[index].caption;
  }

  prevBtn.addEventListener("click", () => {
    currentSlide = (currentSlide - 1 + slides.length) % slides.length;
    updateSlide(currentSlide);
  });

  nextBtn.addEventListener("click", () => {
    currentSlide = (currentSlide + 1) % slides.length;
    updateSlide(currentSlide);
  });


  const toggleButton = document.getElementById("toggleDemo");
  const demoBox = document.getElementById("demoBox");

  if (toggleButton && demoBox) {
    toggleButton.addEventListener("click", () => {
        console.log("Toggling demo box");

      const isHidden = demoBox.classList.contains("hidden");
      demoBox.classList.toggle("hidden");

      toggleButton.innerText = isHidden
        ? "Hide ID Flow Explanation"
        : "Show ID Flow Explanation";
    });
  }
});
