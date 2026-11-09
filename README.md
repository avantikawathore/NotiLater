# NotiLater

NotiLater is a personal Android portfolio project that helps users create and manage notes with AI-powered note summarization.

The project consists of an Android application built with Kotlin and Jetpack Compose, along with a Kotlin-based REST API built using Ktor to handle AI summarization.

## Features

- Create and manage notes.
- View and edit note details.
- Generate AI-powered summaries for notes.
- Organize notes with sorting options:
  - Last Modified
  - Alphabetical (A–Z).
- Modern UI built with Jetpack Compose and Material 3.

## Tech Stack

### Android Application
- Kotlin
- Jetpack Compose
- Material 3
- MVVM Architecture
- Clean Architecture
- Hilt
- Kotlin Coroutines

### Backend
- Kotlin
- Ktor
- Netty
- Gemini API
- Kotlinx Serialization
- Gradle

## Project Structure

```text
NotiLater/
├── app/
├── core/
├── data/
├── domain/
├── feature/
├── backend/
│   ├── src/
│   ├── build.gradle.kts
│   └── README.md
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

The Android application and backend are maintained as separate Gradle projects within the same repository.

## Backend API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Root endpoint |
| GET | `/health` | Health check |
| POST | `/api/summary` | Generate an AI summary |

For backend setup and configuration, see [Backend README](backend/README.md).

## Getting Started

### Android Application

1. Clone the repository.
2. Open the project in Android Studio.
3. Allow Gradle synchronization to complete.
4. Run the application on an Android device or emulator.

### Backend

1. Navigate to the backend directory:

   ```bash
   cd backend
   ```

2. Configure your Gemini API key using the `GEMINI_API_KEY` environment variable.
3. Optionally configure `GEMINI_MODEL`.
4. Start the backend:

   ```bash
   ./gradlew run
   ```

For detailed backend instructions, refer to [backend/README.md](backend/README.md).

## Project Information

NotiLater is a personal portfolio project developed to demonstrate Android application development and AI integration using Kotlin, Jetpack Compose, and Ktor.

The application is not currently published on the Google Play Store.

## Author

**Avantika Wathore**
