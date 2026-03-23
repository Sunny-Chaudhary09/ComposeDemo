# MyDemo (ComposeDemo)

A sample **Android** app built with **Jetpack Compose** and **Material 3**. It demonstrates a splash flow, email/password login with a test API, and an OTP verification screen with a top app bar.

**Repository:** [github.com/Sunny-Chaudhary09/ComposeDemo](https://github.com/Sunny-Chaudhary09/ComposeDemo)

---

## Features

- **Splash** — Initial screen before navigation to login.
- **Login** — Email and password fields, loading state, and error handling.
- **Test login API** — Uses [ReqRes](https://reqres.in/) `POST /api/login` for demo credentials; local test users bypass the network when using the built-in test account.
- **OTP screen** — 6-digit input, show/hide code, verify and resend (demo), **toolbar** with title **OTP** and **back** navigation.
- **Navigation** — `NavHost` with routes: Splash → Login → OTP (on successful login).

---

## Tech stack

| Area        | Details                                      |
|------------|-----------------------------------------------|
| Language   | Kotlin                                        |
| UI         | Jetpack Compose, Material 3                   |
| Navigation | Navigation Compose                            |
| State      | MVI-style (`BaseMviViewModel`, intents/state) |
| Min SDK    | 24                                            |
| Target SDK | 36                                            |

---

## Requirements

- [Android Studio](https://developer.android.com/studio) (recommended: latest stable)
- **JDK 11+** (project targets JVM 11)
- Android SDK as installed with Android Studio

---

## Getting started

### Clone

```bash
git clone https://github.com/Sunny-Chaudhary09/ComposeDemo.git
cd ComposeDemo
```

> If you work from a local copy named **MyDemo**, open that folder in Android Studio the same way.

### Run

1. Open the project in **Android Studio**.
2. Let Gradle sync finish.
3. Select a device or emulator.
4. Click **Run** ▶️ (or **Run → Run 'app'**).

### Build from terminal

```bash
./gradlew :app:assembleDebug
```

APK output: `app/build/outputs/apk/debug/`

---

## Test credentials

For **local demo login** (no ReqRes account needed):

| Field    | Value                 |
|----------|------------------------|
| Email    | `sanjeev@yopmail.com` |
| Password | `12345678`            |

On the login screen you can tap **Use test credentials** to fill these automatically. After a successful login, the app navigates to the **OTP** screen.

**ReqRes demo** (requires network): e.g. `eve.holt@reqres.in` / `cityslicka` — see [ReqRes login docs](https://reqres.in/).

---

## Project structure (high level)

```
app/src/main/java/com/example/mydemo/
├── MainActivity.kt
├── core/mvi/              # MVI base ViewModel
├── ui/
│   ├── login/             # Login screen + ViewModel
│   ├── otp/               # OTP screen
│   ├── splash/            # Splash screen + ViewModel
│   ├── navigation/        # Routes + NavHost
│   └── theme/             # Compose theme
└── res/                   # Strings, colors, dimensions, etc.
```

---

## Permissions

- **`INTERNET`** — Used for the optional ReqRes login API call.

---

## License

This project is provided as a **demo** for learning. Add a license file if you plan to distribute or open-source formally.

---

## Contributing

Issues and pull requests are welcome on [ComposeDemo](https://github.com/Sunny-Chaudhary09/ComposeDemo).
