# Gym Training Generator

An Android application that generates personalized workout plans based on user fitness goals, level, and preferences. Built as a university assignment.

## Features

- **Personalized Workout Generation** — Choose your fitness goal, level, training days per week, and muscle focus to get a custom plan
- **Exercise Library** — Browse exercises with videos, categorized by muscle group
- **Workout History** — Track all completed and in-progress workouts
- **Progress Statistics** — View total workouts, completion rate, and favorite goals
- **User Profile** — Store age, height, weight, and fitness preferences
- **Multi-language** — Full support for English and Macedonian (Македонски)
- **Dark / Light / System Theme** — Configurable from Settings
- **Responsive Design** — Separate layouts for phones, tablets (sw600dp), portrait, and landscape

## Firebase Integration

| Module | Usage |
|--------|-------|
| Firebase Authentication | Anonymous, Email/Password, Google, Facebook login |
| Firebase Firestore | User profiles and generated workout plans |
| Firebase Messaging (FCM) | Push notifications |
| Firebase Analytics | Usage event tracking |

## Local Storage

Workout history is persisted locally using **Room** (Android Jetpack), with offline access to previously generated plans.

## Screenshots

See the [`screenshots/`](screenshots/) folder.

## Videos / Screencasts

See the [`videos/`](videos/) folder.

## Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM + Repository pattern
- **UI:** Material Design 3 (MaterialComponents), Navigation Component, View Binding
- **Database:** Room 2.6.1
- **Backend:** Firebase (Auth, Firestore, Messaging, Analytics)
- **Auth SDKs:** Google Sign-In, Facebook Login SDK

## Setup

1. Clone the repository
2. Open in Android Studio (Electric Eel or newer)
3. Add your `google-services.json` to `app/`
4. Replace Facebook credentials in `app/src/main/res/values/strings.xml`:
   - `facebook_app_id`
   - `facebook_client_token`
   - `fb_login_protocol_scheme`
5. Build and run on a device or emulator (minSdk 26)

## Requirements Met

- [x] Multiple Activities/Fragments (10+ fragments)
- [x] Phone and tablet layouts (portrait + landscape)
- [x] Internationalization: English + Macedonian, all strings in XML
- [x] Local database via Room
- [x] Firebase Auth (Anonymous, Email/Password, Gmail, Facebook)
- [x] Firebase Firestore
- [x] Firebase Messaging
- [x] Firebase Analytics
- [x] Public GitHub repository with multiple commits

## Author

Marija Mihajlovska — marija.mihajlovska9@gmail.com
