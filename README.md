# Inside Out App
An Android App for users who had a specific mood and want to perform some action to improve it

# Why I've developed it
This app was developed at the end of the "Mobile Application Development" course at the University of Pisa.
It was a way to put into practice the arguments treated during the course and to do experience with Android Studio,
Java for Android, Google APIs, and so forth, and especially to have a first approach with mobile applications on Android devices.

My basic idea for this app has been: "How many times we are happy, sorrow, anger, disgust, fear or hungry and we don't know what to do?"
And again: "How many times during this we have a mobile phone in our hands and we navigate around the thousand apps we had installed?"
I thought to propose some action to do to the people with no idea about what to do.
The four actions are only an exemplifying of a real app that can be put on the Play Store, but with other professional figures and with
more time to develop it (maybe adding some AI features), it could become an interactive app to help people to decide what to do when
they are in a specific mood.

# Internal Structure 
In this project, there are different classes, activities and fragments used to perform the user's action.
Most relevant are: 
  
  -MainActivity: Use of service provided by google to sign a client to the app. 
                   GoogleSignInOptions, GoogleSignInClient, GoogleSignInAccount classes used.
                   Previous classes are often used in the app to take logged user informations.
  
  -HomeActivity: Preparation of BottomNavigationView to move across the three fragments. 
                  (HomeFragment, CalendarFragment, ProfileFragment)
  
  -HomeFragment: There are two CardView (Manual Mood Selection, ML Face Detection).
                  Only first is clickable and it brings the user to "MoodSelectionActivity".
                  [*****] Second CardView when clicked shows a toast to say the is not available.    
  
  -CalendarFragment: Use of DataPicker class. It's used to select a date and than, 
                      clicking the "Show Note" button, start "NoteFragment" for that selected date.
                      NoteFragment: use of Room (SQLLite Database) to show in a recycleview all notes
                                    that match with previously selected date.
  
  -ProfileFragment: Use of GoogleSignIn* classes to show user informations. Clicking "Sign Out" button
                     user sign-out from app and go back to Main Activity.
  [*****]
  
  -MoodSelectionActivity: Use of "CardAdapter" to handle six "MoodCard" inside the recycle view.
                          Clicking on one of them we go inside "MoodActivity".
                          With use of bundle, mood string is given to MoodActivity and by now given to all next activity.
  
  -MoodActivity: It displays a quote picked from string resource and manage a RecycleView with "ActionCardAdapter" class.
                 This adapter has an "OnClickListener" to handle clicks on four cards (MUSIC, FILM, GPS, NOTE).
                 Each of this click start the relative activity (MusicActivity, FilmActivity and so forth).
  
  -MusicActivity: Use of ExoPlayer (instaed of MediaPlayer) to play music and use of
                  Firebase Storage to avoid loading mp3 files into device memory.
                  Use of "MoodPlaylistCreator" class to initialize data structures used on this activity.
  
  -FilmActivity: It displays, inside a recycleView, a list of movies and clicking the "WatchTrailer" button start
                 the youtube app to show the relative clicked card trailer (url of each trailer are in string resources).
                 Use of "MoodFilmListCreator" class to initialize data.  
  
  -GPSActivity: Use of "Maps SDK for Android" and "Places API" services.
                Use of two AsyncTask ("PlacesTask" and "ParserTask" inner classes) to perform
                background operation and then execute the operation.
                Use of "Place_JSON" class to handle JsonObject given.
                "AddressBuilder" class build the uri to request this object according the "types" choosed for the various moods.
  
  -NoteActivity: Use of Room Database to save note user wrote (NoteDao, Note Card)
                 Custom dialogs to handle action when clicking various button (TitleBar backbutton, onBackPressed, "Save Note" button).
                 Saved notes are readable in Calendar Fragment clicking show note for a date.
               
# Navigation flow of Inside Out app         
  Clicking the next two links with references to two images can find the navigation flow of the app,
  showing all the screens of the app and their links due to clicks.
          
          "https://user-images.githubusercontent.com/81512899/193647585-417a3d18-00f0-47e3-a469-f888ed620dcf.png"
          "https://user-images.githubusercontent.com/81512899/193649635-18c8bd31-44ab-4d36-8665-046edcde7bf3.png"

# WARNING
This project can contain errors and some bad practices in developing an android app!
It was my first experience and I've got a lot to improve on this.
Remember: "You learn by making mistakes."
 
                      
   
                                 
