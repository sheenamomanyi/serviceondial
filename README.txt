Android App: Service-on-Dial


Setup Instructions:
1. Requirements
 I. Android Studio IDE
   - installed with API 28 (Pie)
 II. Apache Server & MariaDB (or XAMPP/WAMPP/LAMPP)
   - You might need to change DB credentials (in server scripts) from the default to match your DB's
 III. Internet Connection
   - For downloading gradle dependecies (without which the project will NOT build successfully)
2. Pull the project from https://github.com/sheenamomanyi/serviceondial
3. Remove folder "Backend and Database" from the pulled folder "ServiceOnDials" and set it aside
4. From folder "Backend and Database", copy folder "sod_backend" and place it in "htdocs". It contains the server scripts.
   - Incase you need to edit DB credentials, go to 'sod_backend >> include >> db_connection.php'
5. From folder "Backend and Database", import SQL file "serviceondial_db.sql" into MariaDB. Server side is done.
6. Load (import) the pulled folder "ServiceOnDials" to Android studio IDE without 'the removed' folder "Backend and Database"
   - If the IDE and SDK are properly installed and there's internet connection to sync gradle dependencies,
     the project will build throwing only one gradle error: from local.properties requiring you to correct the location of
     the ndk.dir and sdk.dir - which will be simply changing the original computer account username to your current account username
     iff everything was left as default when installing the IDE.
7. Connect an Android phone API>=23(Marshmallow) and run app (Shift+F10) to install it in the phone. You might need to download and install
   appropriate IMEO drivers for your phone for Android Device Bridge (ADB) to be able to connect with it.
8. To test the app since it uses a local DB, you'll need to create a network and connect both the computer hosting the server scripts and the phone with the app
   - For example, create a hotspot and connect both devices. using 'ipconfig' or 'ifconfig' on the server computer, check the computer's IP address.
9. In android java code, find class "BgSync.java", change the IP (http://192.168.43.58) used to your server's IP. If everything was done right, the app 
   should work without a glitch.
README.txt
