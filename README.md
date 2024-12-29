# QR-Code-App
Android app that scans different code types and displays the content.

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
# MainActivity
This class sets up the main view and functionality of the app. The functionality for the actual scan operation is set up here. Inside the onCreate() method, an onClickListener() method
is set to the "scan" button. When the button is clicked, the initiateScan() method is called, which opens the user's phone camera and looks for any code to scan:

    scan.setOnClickListener(v -> {

            IntentIntegrator intent = new IntentIntegrator(this);
            intent.setPrompt("Scan QR Code"); 
            intent.setOrientationLocked(true);
            intent.initiateScan();
        });

When a user clicked on the "list" button, an Intent object calls the ViewActivity class and displays the list of saved codes.
