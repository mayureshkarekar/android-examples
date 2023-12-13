/*
--------------------------- Firebase App Distribution CI/CD using Gradle ---------------------------
Firebase provides a Gradle plugin to automate build deployment to Firebase App Distribution. In
build.gradle (app), we have added firebaseAppDistribution block that specifies the way to upload the
build to App Distribution.

Step 1: Create a Firebase project and connect your Android app to it.
Step 2: Enable App Distribution and add a testers group named "QA". The name of group can be
        different, make sure you add correct name in the YML file.
Step 3: Create a Google Cloud Project, add a new service account by navigating to IAM & Admin >
        Service Accounts > Create Service Account. Fill the appropriate account name eg. "Firebase
        App Distributor". Next, Select "Firebase App Distribution Admin" as role.
Step 4: Once the role is created click the more options on the role and click Manage Keys option.
        Click on create new key and select JSON. It will download a file to your system.
Step 5: Paste the downloaded file in step 4 to the root directory (ideally another safe position in
        local system). Assign the absolute path to serviceCredentialsFile in firebaseAppDistribution
        block in build.gradle (app).
Step 6: Add other required data to the block, sync and build the project.
Step 7: Open terminal and run ./gradlew assembleRelease appDistributionUploadRelease (The command
        can vary according to the flavour).

*/

package com.example.cicddemo_firebaseappdistributionusinggradle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}