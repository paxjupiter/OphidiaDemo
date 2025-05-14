# 🏗 Architecture Overview – Ophidia

Ophidia is structured as a clean, fragment-driven Android app using MVVM architecture and a Room-backed local database. The entire experience is offline-first and built for real-world usability in low-signal environments.

This document outlines the high-level design of the system—how data moves, how the UI reacts, and how the app stays responsive and scalable without relying on cloud services.

---

## 🔄 App Lifecycle Overview

**SplashActivity**  
- Triggers Room database instantiation  
- Parses CSV data into structured lists (on first launch only)  
- Hands off to `LoginActivity` after a timed delay

**LoginActivity**  
- Minimal stub UI that transitions immediately to the main app  
- Sets the tone, but avoids actual auth complexity
- Login/account creation may be implemented later

**MainActivity**  
- Central router for all content  
- Hosts fragment container and top navigation logic  
- Initializes DAO for shared access across fragments

---

## 🧭 Fragment Structure

| Fragment | Purpose |
|----------|---------|
| `HomeFragment` | Displays a "Snake of the Day" with random pull from Room and local asset fallback |
| `IdSnakeFragment` | Hosts a ViewPager2-powered multi-step filtering UI |
| `IdSnakeStep1Fragment` | Country + primary/secondary color filter with FlexboxLayout |
| `IdSnakeStep2Fragment` | Head/nape pattern toggles using MaterialButton groups |
| `IdSnakeStep3Fragment` | Ventral color and pattern inputs via spinner + color buttons |
| `ResultsFragment` | Displays matching species list from ViewModel-filtered Room data |
| `ExploreAllFragment` | Infinite-scroll list of all available species |
| `MySnakesFragment` | Local favorites using SharedPreferences, with dedupe logic |
| `SnakeDetailFragment` | Full profile view with swipeable gallery, per-image attribution |
| `AboutOphidiaFragment` | Project info and collaboration credits |

All fragments communicate via:
- Shared `ViewModel` instances (`IdSnakeViewModel`, `SnakeAdapter` binding)
- Explicit fragment-to-fragment handoffs using `Bundle` arguments and `.newInstance()`

---

## 🧠 ViewModel Use

ViewModels store temporary filter state across the multi-step identification flow. They’re scoped to `MainActivity`, ensuring persistence through fragment transitions without memory leaks or jank.

No data is written to disk until the user explicitly saves a snake—state is ephemeral until committed.

---

## 🗃 Data Persistence

- **Room Database** stores full snake records, including:
  - Species name  
  - Common name  
  - Geographic range  
  - Color + pattern metadata  
  - Image file paths  
  - Licensing + attribution info

- **SharedPreferences** handles:
  - User favorites (as JSON strings)  
  - First-launch state  
  - Cached filter input lists (colors, patterns, countries)

CSV parsing is performed once (on first launch) via a custom utility and cached permanently.

---

## 🖼 Image Handling

All images are:
- Stored in the local `/assets` directory  
- Mapped to species via filenames from normalized CSV fields  
- Loaded manually using `assets.open()` (not Glide) to avoid library overhead

Images are served into `ViewPager2` galleries or RecyclerViews, and fallback logic ensures app stability even if images are missing or malformed.

---

## 🚦 Navigation & State

Fragment swaps are handled through `FragmentManager` transactions with:
- `.replace()` into a container view  
- `.addToBackStack()` where appropriate  
- Direct fragment instantiation with `.newInstance()` and argument bundles

No `NavGraph`, no fragment tags, no UI binding leaks.

---

## 🧪 Edge Case Handling

- **Missing images**: default fallback art with attribution suppressed  
- **Empty filter results**: Toast message + reroute prompt  
- **Empty favorites list**: Empty-state TextView shown instead of RecyclerView  
- **SharedPrefs corruption**: `try/catch` deserialization fallback  

---

## 🔄 Future Optimization Plans

- Modular “data package” system for downloading only relevant regional snakes  
- Glide-based lazy image loading for gallery optimization  
- Paging3 implementation for Explore + Results
- Possible SQLite full-text search (FTS) support for common name lookups
- Map assets depicting recorded observation locations

---

## 💡 Why This Matters

This architecture shows:
- Application works **entirely offline** with a responsive, stateful UI  
- Demonstrates **separation of concerns** and respect for lifecycle boundaries  
- Performance tradeoffs are made **deliberately**, not accidentally  
- **Production-level architecture** built solo from CSV to UX

---

For detailed library use, see [`tech-stack.md`](./tech-stack.md)
