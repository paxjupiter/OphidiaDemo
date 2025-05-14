# 🧰 Tech Stack – Ophidia

Ophidia is built entirely with native Android tooling and offline-first principles. Every library, pattern, and decision was chosen to balance performance, modularity, and usability in remote field conditions.

This doc explains what was used, why, and how it all fits together.

---

## 🧠 Language & Architecture

- **Kotlin**  
  → Modern, concise, null-safe. Enables sealed classes, coroutines, and clean ViewModel scoping.

- **MVVM (Model-View-ViewModel)**  
  → Promotes separation of concerns. Filters live in ViewModels, data stays clean, and UI never owns logic.

- **Jetpack Lifecycle Components**  
  → Prevents leaks, enables scoped observers, and guarantees safe UI/data sync across fragments.

---

## 🗃 Data Persistence

- **Room (SQLite ORM)**  
  → Used to store all snake records, including color metadata, common names, and image references.  
  → Custom DAO interfaces with coroutine support.  
  → Preloaded from parsed CSV on first app launch.

- **SharedPreferences**  
  → Used for:
    - Favorites (stored as JSON strings)
    - First-launch flags
    - Cached dropdown/spinner data
  → Lightweight, resilient, and reactive via `onResume()` checks.

- **CSV → Entity Pipeline**  
  → Custom parser reads normalized CSV, sanitizes inputs, and populates Room via batch inserts.

---

## 🖼 UI Layer

- **FragmentManager**  
  → Used exclusively for navigation. No NavGraph bloat. Transitions are explicit and controlled.

- **RecyclerView**  
  → Powers Explore and Results screens. Scrolls efficiently with adapter caching and no pagination limits (yet).

- **ViewPager2**  
  → Used in:
    - ID flow (multi-step ViewPager)
    - Image carousels (species galleries)
  → Swipe-based interaction = perfect for field usability.

- **FlexboxLayout**  
  → Used for color filters in `IdSnakeStep1Fragment`. Handles wrapping/flow for dynamic data (no scroll required).

- **Material Components**  
  → Used for buttons, colors, and feedback animations. Buttons respond to selection state dynamically.

---

## 🎨 Image Management

- **assets/**  
  → All images are stored locally. Image paths are dynamically built from CSV metadata.

- **Manual asset loading via `assets.open()`**  
  → Glide is not used here (yet) to minimize dependencies and retain full control.  
  → ViewPager2 handles swiping; fallbacks show placeholder UI if images are missing.

- **Attribution Logic**  
  → Each image has its own attribution line, synced via index with the ViewPager.  
  → iNaturalist is the primary source of snake images.

---

## 🛠 Development Tools

- **Android Studio (Hedgehog)**  
  → Main IDE

- **ADB + Emulators**  
  → Tested on API 24–34  
  → Pixel 4, Pixel 6, and Samsung Galaxy S10 tested locally

- **GitHub**  
  → Codebase hosted privately  
  → Demo repo includes stubbed files, screenshots, and documentation only

---

## 🔍 Testing & QA

- **Manual QA only**  
  → This app is built by a solo dev and researcher team. All testing has been real-device/manual.  
  → Primary test case is: “Does this work 100% without internet?”
  → User testing to be implemented in the near future combining scholars, experts, and folks with limited/no herpetology background.

- **Progressive dataset expansion (~66 snakes at present)**  
  Additional species added weekly (~50 per batch) to identify scalability thresholds under local storage constraints


- **Next steps** (post-release):
  - Unit tests for CSV parsing + ViewModel state  

---

## 🧭 Why This Stack?

- Everything in this stack supports **offline-first development**  
- It’s built for maintainability, not trend-chasing  
- No Firebase, no login walls, no APIs = **no brittle dependencies**  
- Codebase is optimized for modular expansion: data packages, search indexing, and feature toggles can be added incrementally

---

Ophidia is a lean, scalable Android app built for people who actually go outside.

For full code access or collaboration inquiries:  
📧 **cryptidwebdev@gmail.com**
