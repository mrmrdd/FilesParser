This REST application receives files and parses them in parallel way.
The result of parsing is map with lines as the keys and number of their occurrences as the values.
Both source files and maps with result of parsing are stored into the database.

How to RUN:

To run this application you have to create new databes with 'fileparser' name and execute query that
you can find in 'resources' package. To upload files you can use 'Postman' application or something same.
You should use 'localhost:8080/upload' URL to upload files and 'localhost:8080/parse' to see result.

