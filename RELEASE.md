# Google Play Release Guide

## 1. Generate a Keystore (one-time)

Run this command from the project root:

```bash
keytool -genkey -v -keystore gym_training_generator.jks \
  -alias gym_release \
  -keyalg RSA -keysize 2048 -validity 10000
```

Keep the `.jks` file safe — you can never re-upload to Play Store with a different keystore.

## 2. Create keystore.properties

Create a file called `keystore.properties` in the **project root** (same folder as `build.gradle.kts`):

```
storeFile=/absolute/path/to/gym_training_generator.jks
storePassword=YOUR_STORE_PASSWORD
keyAlias=gym_release
keyPassword=YOUR_KEY_PASSWORD
```

Add `keystore.properties` and `*.jks` to `.gitignore` — never commit them.

## 3. Build the Release Bundle

```bash
./gradlew bundleRelease
```

The signed `.aab` will be at:
`app/build/outputs/bundle/release/app-release.aab`

## 4. Upload to Google Play

1. Go to [play.google.com/console](https://play.google.com/console)
2. Create a new app (requires $25 one-time developer fee)
3. Go to **Production → Create new release**
4. Upload `app-release.aab`
5. Fill in store listing:

### Store Listing (English)
- **App name:** Gym Training Generator
- **Short description:** AI-powered personalized workout plans with Firebase sync
- **Full description:**
  Gym Training Generator creates personalized workout plans based on your fitness goals,
  level, and available training days. Track your progress, browse 80+ exercises, count
  your daily steps, and find gyms near you.

  Features:
  • Personalized workout plan generation
  • Firebase sync across devices
  • Exercise library with 80+ exercises
  • Daily step counter
  • Find nearby gyms via GPS
  • Dark mode support
  • English and Macedonian language support

### Store Listing (Macedonian / mk)
- **App name:** Генератор на тренинг
- **Short description:** Персонализирани тренинг планови со Firebase синхронизација
- **Full description:**
  Генераторот на тренинг креира персонализирани тренинг планови врз основа на твоите
  фитнес цели, ниво и достапни денови за тренинг.

  Функции:
  • Генерирање персонализиран тренинг план
  • Firebase синхронизација
  • Библиотека со 80+ вежби
  • Дневен бројач на чекори
  • Пронаоѓање теретани со GPS
  • Поддршка за темен режим
  • Англиски и македонски јазик
