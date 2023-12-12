/*
------------------------------------ CI/CD using GitHub Actions ------------------------------------
GitHub Actions are the one of the ways to automate the deployment of the builds. In the current
project we have created a YML file to upload Debug build to Firebase App Distribution.

Step 1: Create a Firebase project and connect your Android app to it.
Step 2: Enable App Distribution and add a testers group named "QA". The name of group can be
        different, make sure you add correct name in the YML file.
Step 3: Create a Google Cloud Project, add a new service account by navigating to IAM & Admin >
        Service Accounts > Create Service Account. Fill the appropriate account name eg. "Firebase
        App Distributor". Next, Select "Firebase App Distribution Admin" as role.
Step 4: Once the role is created click the more options on the role and click Manage Keys option.
        Click on create new key and select JSON. It will download a file to your system.
Step 5: Go to your GitHub repository > Settings > Secrets and Variables > Actions > New Repository
        Secrete. Put name as "CREDENTIAL_FILE_CONTENT" and paste the content of JSON file downloaded
        in step 4.
Step 6: Create another secrete named "FIREBASE_APP_ID" and paste Firebase project's App ID in the
        content.
Step 7: Create a directory .github/workflows and add a .yml file with suitable name.
        eg. "deploy_to_firebase_app_distribution.yml". Add the necessary lines to perform build
        generation and deployment tasks. Push the changes to GitHub.
Step 8: Navigate to Actions in GitHub repository. You should see your defined action there.
        Click on Run Workflow, The script will run and upload build to Firebase App Distribution.
*/

package com.example.cicddemo_githubactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}