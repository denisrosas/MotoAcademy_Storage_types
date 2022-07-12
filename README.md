# MotoAcademy_Storage_types
This APK is part of a Embedded Android Training to understand better how you can see and change by ADB command the data stored by an application.

The users will install the app on a test device with root permission.

Pre-steps:
adb install MotoAcademy_Storage_types.apk

Basically they will:
 1. adb root
 2. adb shell
 3. cd /data/data/com.example.motoacademy_storage_type
 4. ls -> see the database and shared_pref folders
 5. Open the app -> write a shared pref value and press "Write Shared Pref" button 
 6. cat shared_pref/com.example.motoacademy_storage_types0.xml -> confirm the new shared pref value was stored.
 7. Write a name and phone number -> Press "Store in .db"
 7. sqlite3 database/MotoAcademy
 8. sqlite> .tables
 9. sqlite> SELECT * FROM users; -> confirm the values were stored
 10. .quit -> to exit sqlite
 11. setprop dev.motoacademy.value 30
 12. Press "Read prop value" button
