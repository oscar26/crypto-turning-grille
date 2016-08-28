# crypto-turning-grille
Repo for the assignment 1 of the cryptography course

This is just the initial and already functional boilerplate for the assignment. Things
missing:

* The actual logic (rotate the matrices, do the cipher, etc.). Working on this.
* The front-end that will consume the services.

Things done:

* Setting an example REST service for having a starting point
(URI: localhost:8080/resources/cipher/test).
* Deploy tested locally with a Tomcat instance.

Please, use Apache Tomcat 8.0.33 for deploying as it's a lot lighter than full EE
application servers and it's the most used by the cloud services for Java web apps.
