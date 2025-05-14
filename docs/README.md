# 🐍 Ophidia – A Field-Ready Snake Identification App (Demo Repository)

**Ophidia** is an offline-first mobile app designed to support field herpetologists, conservation workers, and citizen scientists in identifying snake species based on observable traits. It combines custom data parsing, responsive UI architecture, and a dynamic filtering system grounded in taxonomic metadata.

The app is in active development and undergoing load testing for its local storage capacity. A modular data package system is in planning to ensure scalability across regions and larger datasets.

This repository is a **public demo**, created to showcase the application’s structure, flow, and interface design. The full implementation is private but available to trusted collaborators or potential employers upon request.

---

## 🎯 Project Origins

This app began as a portfolio project and evolved into a six-month collaborative build with **Dr. Peter Uetz**, curator of the Reptile Database and professor of systems biology at Virginia Commonwealth University, and **Dan Durback**, a researcher on Dr. Uetz’s team.

I was initially provided with raw taxonomic data—over 8,000 entries—and began the process of normalizing it into a structured model suitable for public-facing applications. That meant designing everything from the database layer to the UI from scratch, ensuring data clarity, performance, and usability in the field.

What started as a theoretical snake filter became a working prototype capable of narrowing 600+ species through compound field data—leveraging dynamic CSV ingestion, a custom Room schema, and multi-step user input.

Ophidia now sits at the intersection of:

- 🧠 Scientific taxonomic rigor  
- 🛠 Robust mobile development  
- 🎯 Applied field usability  
- ℹ️ Accessibility to the general public

---

## 🧩 What Makes Ophidia Different

Most ID apps rely on static filters, cloud lookups, or opaque machine learning. Ophidia is:

- 📴 **Offline-first** — All data and images are stored locally. No signal required.  
- 🧬 **Trait-driven** — Filters use human-observable input: country, color, pattern, ventral traits.  
- 📸 **Image-rich** — Each species includes up to three curated, credited images from public domain or CC sources (primarily from iNaturalist).  
- 🔍 **Scalable** — Built to handle 1,000+ species without bloating memory or slowing interaction.

---

## 🧠 Core Features

- **Multi-step Identification Flow**  
  Narrows species via compound user input: country → dorsal color → head/nape markings → ventral color/pattern. Results are scored using a custom (private) algorithm.

![Begin ID – Country & Primary Color](screenshots/IdSnake1.png)  
*Step 1 of multi-input filtering: user selects observed region and dominant color.*

![Color Filter Filled](screenshots/IdSnake2.png)  
*Search box supports fuzzy country input.*

![Secondary Color Selection](screenshots/IdSnake3.png)  
*Allows for additional visible colors to refine results.*

![Head & Nape Traits](screenshots/IdSnake4.png)  
*User can toggle visual markings on the head and nape area.*

![Pattern Selection](screenshots/IdSnake5.png)  
*Uses visual references for intuitive selection.*

![Ventral Color & Pattern](screenshots/IdSnake6.png)  
*Last step before species match: underside colors and patterns.*

![Species Results](screenshots/Results.png)  
*Species narrowed using a scoring algorithm (private).*

![Result Selection Toast](screenshots/AddedToMySnakes.png)  
*Confirmation after saving a match.*

- **Snake of the Day**  
  Randomly displays a species at launch, using local fallback if no image is available.

![Home Screen](screenshots/HomeScreenRefactor.png)  
*Randomly featured species with offline fallback logic.*

- **Explore All**  
  Infinite-scroll list of all available species, pulled from Room with adapter caching.

![Explore All View](screenshots/ExploreAllRefactor.png)  
*Scroll through all indexed species without filters.*

- **Information-Rich Detail View**  
  ViewPager2-powered image carousels with dynamic per-image attribution. Page populates with detailed information about the selected snake.

![DetailView – Part 1](screenshots/DetailView1Refactor.png)  
*Offline-accessible profile includes image, safety warning, and name data.*

![DetailView – Part 2](screenshots/DetailView2Refactor.png)  
*Country list, possible patterns, ventral traits, and placeholder map.*

![DetailView – Part 3](screenshots/DetailView3Refactor.png)  
*Source attribution with license notes (iNaturalist, CC-compliant).*

![DetailView – Venomous Species](screenshots/DetailViewVenomousRefactor.png)  
*Header and text formatting reflect venom status.*

- **Favorites (“My Snakes”)**  
  Users can save any snake they’ve ID’d. Entries are deduplicated and stored via SharedPreferences. The app checks for updates onResume to keep the UI in sync.

![Saved Snakes List](screenshots/MySnakesList.png)  
*User-selected favorites stored locally.*

---

## 🔬 Collaboration Details

Dr. Uetz and Dan Durback provided taxonomic data structure, scientific review, and naming standardization. All development was handled independently by **Aiden (Pax) Montoro**, including:

- CSV parsing pipeline and data sanitization  
- Room schema and normalized relational mapping  
- UI/UX design for rapid filter-driven workflows  
- Favorites system with SharedPreferences serialization  
- Full fragment routing, navigation, and lifecycle integration

The app was built with field survivability in mind:
- No cloud calls  
- No account system  
- No loading spinners  
- No fluff

If you're looking at a snake in Africa and holding a phone, this thing works.  

---

## 📁 This Demo Repo Contains

- `code-stubs/` – Kotlin file stubs showing structure, lifecycle flow, and intent. All logic is redacted.  
- `screenshots/` – Static examples of key fragments and feature screens  
- `architecture.md` – A breakdown of the app’s architecture and responsibilities per layer  
- `tech-stack.md` – Library and tool choices with reasoning  
- `README.md` – You're reading it

---

## 🔒 What’s Not Public

To protect both data integrity and licensing:

- Full implementation  
- Matching algorithm logic  
- Normalized metadata CSVs  
- Asset pipelines for image handling  
- Filter weight tuning logic  
- Schema migration history  
- Room type converters + relations  
- XML layout files  
- All production assets

---

If you're a recruiter, collaborator, or research partner who would like access to the full codebase, email:

📧 **cryptidwebdev@gmail.com**

---

## 🔧 Build & Environment

- **Language**: Kotlin  
- **Architecture**: MVVM  
- **Database**: Room (local only)  
- **State Management**: ViewModel + SharedPreferences  
- **View Layer**: FragmentManager, ViewPager2, RecyclerView  
- **No External Auth** • **No Cloud Sync** • **Fully Offline**  
- **Target**: Android API 24+

More details in [`tech-stack.md`](./tech-stack.md)

---

## ⚖ Licensing and Attribution

All species images used in development were sourced via public license (primarily iNaturalist). Attribution is displayed dynamically in-app and in screenshots. All other graphical assets are original work by Aiden Montoro.

This repo is for demonstration and educational use only.  
It may not be repackaged, resold, or redistributed.

© 2025 Aiden Montoro (Cryptid). All rights reserved.

---

> *“We designed this to work in the worst conditions—off-grid, low signal, no support. If you have a snake and a phone, you may have an answer.”*
