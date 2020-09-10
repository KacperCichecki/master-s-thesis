# master-s-thesis
This is project where I compare process of creating web appliaction with Spring Boot and Spring Framework projects

Instrukcja:
Na wstępie upewnij się, że:
- masz zainstalowaną javę w wersji minimum 11 (https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- ustawioną zmienną JAVA_HOME (https://docs.opsgenie.com/docs/setting-java_home)
- javę dodaną do PATHa (https://www.java.com/en/download/help/path.xml)

1. Pobrać kod aplikacji (zielony przycisk "Code" a później "Download ZIP")
2. Wypakować i przejść do folderu
3. W tym folderze odpalić konsolę (lub przejść w konsoli do tego folderu)
4. Wpisać w konsoli komendę: 
    ./mvnw spring-boot:run (linux)
    ./mvnw.cmd spring-boot:run (windows)
Jeśli pojawi się komunikat: "Error: JAVA_HOME not found in your environment." to 
    
5. Wpisać w przeglądarce dany adres: http://localhost:7777/main

Funkcjonalności:
- formatowanie jsona tak jak jest to opisane na stonie: http://localhost:7777/about
- logowanie, rejestracja, wylogowanie
- zapisywanie jsonów i usuwanie ich ze swojego profilu

