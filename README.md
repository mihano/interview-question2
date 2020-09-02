Interview question
==================

This is a very basic spring-boot app. Run it (using `mvn spring-boot:run`) or your favorite IDE.

#### Store array of numbers
Sending `http://localhost:5000/store?numbers=2,1,3,4,6,5,7` should 
return an ID of the array (e.g., `1`)
#### Returns a random permutation of numbers
Sending `http://localhost:5000/permutation?id=1` should return 
a random permutation of the array (e.g., `2,3,6,7,1,3,5`)
