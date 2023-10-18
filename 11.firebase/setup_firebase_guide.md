## Offical documentation for Android:
add firebase to app: https://firebase.google.com/docs/android/setup

database: https://firebase.google.com/docs/database/android/start
## Video: 
https://www.youtube.com/watch?v=jbHfJpoOzkI

### Step 1: Create a Firebase project

### Step 2: Register your app with Firebase


    1.Go to the Firebase console.

    2.In the center of the project overview page, click the Android icon (plat_android) or Add app to launch the setup workflow.

    3.Enter your app's package name in the Android package name field.

    Make sure to enter the package name that your app is actually using. The package name value is case-sensitive, and it cannot be changed for this Firebase Android app after it's registered with your Firebase project.

    4.(Optional) Enter other app information: App nickname and Debug signing certificate SHA-1.


    5.Click Register app.

### Step 3: Add a Firebase configuration file

    1.Download and then add the Firebase Android configuration file (google-services.json) to your app:

    a.Click Download google-services.json to obtain your Firebase Android config file.

    b.Move your config file into the module (app-level) root directory of your app.

    2.To make the values in your google-services.json config file accessible to Firebase SDKs, you need the Google services Gradle plugin (google-services).
    a.In your root-level (project-level) Gradle file (<project>/build.gradle.kts or <project>/build.gradle), add the Google services plugin as a dependency:

``` 
Kotlin

plugins {
  id("com.android.application") version "7.3.0" apply false
  // ...

  // Add the dependency for the Google services Gradle plugin
  id("com.google.gms.google-services") version "4.4.0" apply false
}
```

    b.In your module (app-level) Gradle file (usually <project>/<app-module>/build.gradle.kts or <project>/<app-module>/build.gradle), add the Google services plugin:
```
Kotlin
plugins {
  id("com.android.application")

  // Add the Google services Gradle plugin
  id("com.google.gms.google-services")
  // ...
}
```
### Step 4: Add Firebase SDKs to your app
    1.In your module (app-level) Gradle file (usually <project>/build.gradle.kts or <project>/build.gradle), add the dependencies for the Firebase products that you want to use in your app. We recommend using the Firebase Android BoM to control library versioning.
```
dependencies {
  // ...

  // Import the Firebase BoM
  implementation(platform("com.google.firebase:firebase-bom:32.3.1"))

  // When using the BoM, you don't specify versions in Firebase library dependencies

  // TODO: Add the dependencies for Firebase products you want to use
  // See https://firebase.google.com/docs/android/setup#available-libraries
  // For example, add the dependencies for Firebase Authentication and Cloud Firestore
  implementation("com.google.firebase:firebase-auth-ktx")
  implementation("com.google.firebase:firebase-firestore-ktx")
}
```

    2.After adding the dependencies for the products you want to use, sync your Android project with Gradle files.

### Step 5: Create a Database
    Navigate to the Realtime Database section of the Firebase console. You'll be prompted to select an existing Firebase project. Follow the database creation workflow.

    Select a starting mode for your Firebase Security Rules:

Test mode

    Good for getting started with the mobile and web client libraries, but allows anyone to read and overwrite your data. After testing, make sure to review the Understand Firebase Realtime Database Rules section.
    Note: If you create a database in Test mode and make no changes to the default world-readable and world-writeable Rules within a trial period, you will be alerted by email, then your database rules will deny all requests. Note the expiration date during the Firebase console setup flow.

    To get started with the web, Apple, or Android SDK, select testmode.
Locked mode

    Denies all reads and writes from mobile and web clients. Your authenticated application servers can still access your database.

    3.Choose a location for the database.

    Depending on the location of the database, the URL for the new database will be in one of the following forms:

    DATABASE_NAME.firebaseio.com (for databases in us-central1)

    DATABASE_NAME.REGION.firebasedatabase.app (for databases in all other locations)

    4.Click Done.
### Step 6: Add the Realtime Database SDK to your app
    In your module (app-level) Gradle file (usually <project>/<app-module>/build.gradle.kts or <project>/<app-module>/build.gradle), add the dependency for the Realtime Database Android library. We recommend using the Firebase Android BoM to control library versioning. 
```
Kotlin

dependencies {
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))

    // Add the dependency for the Realtime Database library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-database-ktx")
}
```