# NotiLater Backend

NotiLater Backend is a Kotlin-based REST API built using **Ktor**. It provides AI-powered note summarization and quiz generation for the NotiLater Android application.

## Tech Stack

- Kotlin
- Ktor
- Netty
- Gemini API
- Gradle
- Kotlinx Serialization

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Root endpoint |
| GET | `/health` | Health check |
| POST | `/api/summary` | Generate an AI summary |

## Configuration

The backend requires a Gemini API key.

Set the following environment variable before starting the server:

```bash
GEMINI_API_KEY=your_api_key
```

Optionally, configure the model:

```bash
GEMINI_MODEL=your_model_name
```

The application reads these values from environment variables. Do not hardcode API keys or commit credentials.

## Running Locally

Navigate to the backend directory:

```bash
cd backend
```

Run the server:

```bash
./gradlew run
```

The server runs on port `8080` by default.

## Running Tests

```bash
./gradlew test
```

## Project Structure

```text
backend/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── gemini/
│   │   │   ├── model/
│   │   │   ├── plugins/
│   │   │   ├── routes/
│   │   │   └── service/
│   │   └── resources/
│   └── test/
├── build.gradle.kts
├── settings.gradle.kts
└── gradlew
```