Interview question
==================

This is a very basic spring-boot app which exposes two endpoints. One for storing array of numbers and one
to return permutation of stored array.

#### Store array of numbers
Sending `http://localhost:5000/store?numbers=2,1,3,4,6,5,7` should 
return an ID of the array (e.g., `1`)
#### Returns a random permutation of numbers
Sending `http://localhost:5000/permutation?id=1` should return 
a random permutation of the array (e.g., `2,3,6,7,1,3,5`)

#### How to run it
This app can be run with two different profiles.
* `in-memory` - storage is in-memory, it's cleared when app is restarted or terminated. You can run the app using this
profile with `mvn spring-boot:run -Dspring-boot.run.profiles=in-memory`
* `db` - database is used as storage. This is default profile, you can run the app using this profile with
 `mvn spring-boot:run`.
 
In similar way you can also run the tests.
 * `in-memory` - storage is in-memory, it's cleared when app is restarted or terminated. You can run the tests using this
   profile with `mvn test -Dspring.profiles.active=in-memory`
* `db` - database is used as storage. This is default profile, you can run the tests using this profile with
 `mvn test`.

