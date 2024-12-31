# QR-Code-App
Android app that scans different code types and displays the content. The app also gives access to a grid list of all codes (in QR Format and QR Content) that were scanned.

# activity-main.xml

This activity file displays two text views and two buttons.

The Text Views are:
1. A text view that displays the format of the code that was scanned.
2. A text view that displays the contents of the code scanned.

The Buttons are:
1. A button that calls a function to initiate a scan using the ZXing Java library.
2. A button that opens a second view that contains a list from an SQLite database containing all successful scans.

# ViewActivity and view_activity.xml
view_activity.xml is essentially an empty view. Any data that is displayed in this page is created programmatically using the ViewActivity Java class.
In VIewActivty, a RelativeLayout, Scrollview and GridVeiw are created programmatically. An SQLite database manager object is also created and used
within this view, which is used to display the stored content. The store content includes the QR Format and the QR Content.

The update() method call:
This method, using the SelectAll method and the dynamically resizable ArrayList<>, adds every stored entry from the DatabaseManager.
A range based loop iterates over every entry (As long as a scanned code isn't empty). Two TextViews are programmatically created,
and the data recieved from each entry is set to the TextViews(namely, the QR format and the QR content).

    content.setAutoLinkMask(Linkify.WEB_URLS);
    content.setMovementMethod(LinkMovementMethod.getInstance());

This portion makes it so links within the content are clickable.
# MainActivity.java
This class sets up the main view and functionality of the app. The functionality for the actual scan operation is set up here. Inside the onCreate() method, an onClickListener() method
is set to the "scan" button. When the button is clicked, the initiateScan() method is called, which opens the user's phone camera and looks for any code to scan:

    scan.setOnClickListener(v -> {

            IntentIntegrator intent = new IntentIntegrator(this);
            intent.setPrompt("Scan QR Code"); 
            intent.setOrientationLocked(true);
            intent.initiateScan();
        });

When a user clicked on the "list" button, an Intent object calls the ViewActivity class and displays the list of saved codes.

# Codes.java
This class is relatively simple. It consists of the default constructor Codes that has three parameters: an Integer id, a String format (for the QR Format) and a String content (for the QR Content).
The rest of the code consists of getters and setters for these variables. The default constructor simply assigns the variables given.

# DatabaseManager.java
Using SQLite, this class implements a database for the app. It contains three variables for the app, all private static final Strings: a variable for the ID, a varialbe for the format, and a variable for the content.
The onCreate() method creates a table that stores the three String variables for the ID, format and content. This method is created the first time the app is run and used successfully.

