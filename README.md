# fetch-app
Fetch app

## Few Notes
- typical clean architecture with jetpack compose.
- build-logic folder contains the plugins used to build the rest of the app.
- Modularization by using core, data, feature to improve build speed.
  - Didn't need to do any multibinding to bind composables in other feature modules.
- Added unit test to test each one of the sorting and grouping lists.

## Steps to compile the app
- Make sure you're on jdk 18
- Make sure you're on gradle version 8.10.0
- Make sure you're on AGP 8.6

## Few notes
- This is on kotlin 2.0.20. Strong skipping mode is enabled, allowing me to skip some of the rememberedLambda's
- Wasn't sure if I was supposed to convert the name to Int via splitting the substring, so that `Item 28` comes before `Item 276`
- App should handle most of the configuration change. i.e darkmode, orientation, etc.
