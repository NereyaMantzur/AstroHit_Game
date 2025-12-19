https://github.com/user-attachments/assets/77a1a5aa-7c9f-4b88-8088-be91d6ca5478
# Spaceship Dodger 🚀☄️

An Android arcade game developed in Kotlin where players navigate a spaceship through a grid to avoid incoming asteroids.

> **Note:** This project was developed as part of a **mobile development course** at **Afeka College**.

## 📱 App Overview

The application is a grid-based survival game. The player controls a spaceship restricted to three specific lanes (rows). Asteroids spawn and move across the screen in a tick-based loop. The goal is to survive as long as possible without losing all three lives.

### Key Features
* **Lane-Based Movement:** Navigate a spaceship across 3 distinct lanes using Floating Action Buttons (FABs).
* **Dynamic Obstacles:** Asteroids spawn randomly and move across a grid system.
* **Health System:** The player starts with **3 hearts**. Collisions reduce health and trigger visual UI updates.
* **Haptic & Visual Feedback:**
    * Vibration feedback upon collision (supports modern and legacy Android SDKs).
    * Toast messages for "OUCH" (hit) and "GAME OVER".
* **Animated Background:** Uses **Glide** to load a GIF background for an immersive space atmosphere.

## 🛠️ Tech Stack & Architecture

* **Language:** Kotlin
* **Platform:** Android (View System/XML)
* **Architecture:** MVC-inspired (Model-View-Controller)
    * **Model:** `GameManager` handles logic (collisions, game state).
    * **View/Controller:** `MainActivity` handles UI rendering and user input.
    * **Utils:** `SignalManager` (Thread-safe Singleton) handles system services like Vibration and Toasts.
* **Libraries:**
    * [Glide](https://github.com/bumptech/glide) - For efficient image and GIF loading.

## 🎮 How to Play

1.  Launch the application.
2.  Use the **Right Arrow** button to move the ship down a lane, and the **Left Arrow** button to move up a lane.
3.  Avoid the incoming asteroids.
4.  If you hit an asteroid, your phone will vibrate and you will lose a heart.
5.  If you get hit 3 times, the game ends and restarts.

## 📂 Project Structure

* `model/`: Contains `GameManager.kt` which manages the game state, hits, and collision logic.
* `utils/`:
    * `AsteroidState.kt`: Data class tracking asteroid coordinates and movement logic.
    * `SignalManager.kt`: A Singleton for handling Vibrations and Toasts.
    * `Constants.kt`: Holds configuration values (e.g., Game Loop Timer delay).
* `MainActivity.kt`: The main entry point containing the `Handler`/`Runnable` game loop and UI view bindings.
