# PillPlain
 
HEAD
Pierce Dugas
pdugas@xula.edu
pierredugs-spec

Terron McIntyre
tmcinty1@xula.edu
terronmcintyre31

PillPlain is a mobile app class project that helps users look up medication label information by name. Its goal is to make common
label sections easier to find and read on a phone

## Getting Started

1. Clone this team repository to your computer.
2. Open Android Studio and select Open.
3. Choose the cloned folder containing settings.gradle.kts.
4. Click Trust Project if prompted.
5. Obtain a free API key from https://open.fda.gov/apis/authentication/.
6. Open local.properties and add this on a new line:
   OPENFDA_API_KEY=your_key_here
7. Replace your_key_here with your actual key and save.
   local.properties is ignored by Git.
8. Click Sync Now if prompted and wait for Gradle sync to finish.
   Install any missing SDK components when prompted.
9. Select app in the run configuration dropdown.
10. Select an Android emulator. If none is available, open
    Tools > Device Manager and create one.
    We tested with Pixel 6 API 34.
11. Click Run and wait for PillPlain’s Home screen to appear.

### Verify the API Request

1. Start an emulator with internet access.
2. Open OpenFdaApiTest.kt under the androidTest package.
3. Right-click OpenFdaApiTest and select Run.
4. Confirm that the test passes.

## Getting Help

For setup questions or bug reports, contact a team member listed below

## Team

- [Terron McIntyre] · [tmcinty1@xula.edu] · @[terronmcintyre31] · Driver
- [Pierce Dugas] · [pdugas@xula.edu] · @[pierreduggs-spec] · Navigator

## Project Log

<!-- | Terron Ambrose McIntyre | 2026-11-12 | Revise the README to describe PillPlain and document how to get started and get help. | -->

| Who | Date | What                                                                                            |
| --- | --- |-------------------------------------------------------------------------------------------------|
| Pierce | 2026-11-14 | #1 Design Pillplain home screen, including search bar, navigation options and application logo. |
| Terron | 2026-11-14 | #2 Implement navigation between Home, Search, Saved Medications, and Information Screens.       |
| Pierce | 2026-11-14 | #5 Implement the search button to submit medication names.                                      |
| Pierce | 2026-11-17 | #6 Design medication results screen.                                                            |
| Pierce | 2026-11-17 | #7 Create a medication details screen to display medication information.                        |
| Terron | 2026-11-17 | #8 Create a saved medications screen.                                                           |
| Terron | 2026-11-17 | #9 Configure PillPlain’s color scheme, typography, and UI styling. |
| Pierce | 2026-11-17 | #10 Review openFDA Drug Label API documentation and response structure. |
| Pierce | 2026-11-17 | #11 Add required Ktor HTTP networking dependencies & internet permissions to AndroidManifest.xml. to Android Studio. |
| Terron | 2026-11-20 | #13–14 Configure openFDA Drug Label API key for development without committing to GitHub. Create Kotlin data classes matching openFDA results array and label fields. |
| Terron | 2026-11-20 | #15 Implement GET request to api.fda.gov/drug/label.json |
| Pierce | 2026-11-21 | #16 Add api key, search, and limit query parameters. Implement medication searches with openFDA field. Include brand-name searches with openFDA field.  |
