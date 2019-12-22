# SQLite-CRUD  
SQLite is a opensource SQL database that stores data to a text file on a device. Android comes in with built in SQLite database implementation.  

SQLite supports all the relational database features. In order to access this database, you don't need to establish any kind of connections for it like JDBC,ODBC e.t.c  

![SQLite](http://www.roweb.ro/blog/wp-content/uploads/2015/04/db.png)    

**SQLiteOpenHelper class**  
The `android.database.sqlite.SQLiteOpenHelper` class is used for database creation and version management.  For performing any database operation, you have to provide the implementation of `onCreate()` and `onUpgrade()` methods of SQLiteOpenHelper class.  


**SQLiteDatabase class**  
It contains methods to be performed on sqlite database such as `create`, `update`, `delete`, `select` etc.  

