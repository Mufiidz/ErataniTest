# Eratani - Kotlin Android Projects Collection

A collection of 4 Android applications built with Kotlin, showcasing different Android development concepts and patterns.

## Projects Overview

### 1. APICalling
A demonstration app for making API calls using modern Android architecture.

**Features:**
- RESTful API integration
- MVVM architecture with StateFlow
- Dependency injection using Dagger Hilt
- RecyclerView for displaying user data
- Loading and error state handling
- Clean architecture with ViewModels

**Tech Stack:**
- Kotlin
- Dagger Hilt
- ViewBinding
- Coroutines & Flow
- MVVM Pattern

**Package:** `id.my.mufidz.apicalling`

Go to the [project.](/ApiCalling)

---

### 2. HeartAnim
An interactive heart beat animation app with BPM (beats per minute) control.

**Features:**
- Animated heart beat visualization
- Real-time BPM adjustment (0-200 BPM)
- Smooth animations with Jetpack Compose
- Interactive slider control

**Tech Stack:**
- Kotlin
- Jetpack Compose
- Material Design 3
- Custom animations

**Package:** `id.my.mufidz.heartanim`

Go to the [project.](/HeartAnim)

---

### 3. SearchWord
A word search and management application with dynamic word list.

**Features:**
- Case-sensitive word search
- Add new words to the list
- Display available words in a scrollable list
- Animated UI transitions
- Real-time search feedback

**Tech Stack:**
- Kotlin
- Jetpack Compose
- Material Design 3
- State management with Compose

**Package:** `id.my.mufidz.searchword`

Go to the [project.](/SearchWord)

---

### 4. StockApp
A stock/inventory management application for tracking product transactions.

**Features:**
- Product stock summary view
- Transaction history tracking
- Sale and purchase transaction types
- Tab-based navigation
- JSON-based product data management
- Date-based transaction logging

**Tech Stack:**
- Kotlin
- Jetpack Compose
- Material Design 3
- Tab navigation
- JSON parsing

**Package:** `id.my.mufidz.stockapp`

Go to the [project.](/StockApp)

---

## Development Setup

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or later
- JDK 11 or higher
- Android SDK with minimum API 24
- Gradle 8.0+

### Building the Projects

Each project is independent and can be built separately:

1. **Clone or navigate to the project directory:**

2. **Open individual projects in Android Studio:**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the specific project folder (e.g., `APICalling`, `HeartAnim`, etc.)

3. **Build and Run:**
   ```bash
   # For command line build
   cd [ProjectName]
   ./gradlew assembleDebug
   
   # To install on device
   ./gradlew installDebug
   ```

---

## Project Structure

```
Eratani/
├── APICalling/          # API integration demo with MVVM
├── HeartAnim/           # Heart beat animation with BPM control
├── SearchWord/          # Word search and management app
├── StockApp/            # Stock/inventory management system
└── README.md
```

Each project follows standard Android project structure:
```
[ProjectName]/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/id/my/mufidz/[projectname]/
│   │       ├── res/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
└── build.gradle.kts
```

---

## About All Project

### APICalling
- Network layer implementation
- MVVM architecture pattern
- Dependency injection with Hilt
- StateFlow for reactive UI updates
- Error handling patterns

### HeartAnim
- Custom animations in Jetpack Compose
- State management in Compose
- Interactive UI components
- Material Design 3 theming

### SearchWord
- List manipulation and search algorithms
- Compose state management
- Conditional UI rendering
- User input validation

### StockApp
- Tab-based navigation in Compose
- Data modeling for business logic
- JSON data parsing
- Date and time handling
- Transaction management patterns

---

## Author

**Mufidz**
- Package: `id.my.mufidz`

---

## Notes

- All projects use Jetpack Compose except APICalling which uses traditional View-based UI with ViewBinding
- Projects demonstrate different architectural patterns and Android development techniques
- Each app is self-contained and can be studied independently

---
