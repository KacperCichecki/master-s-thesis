# master-s-thesis
This is project where I compare process of creating web appliaction with Spring Boot and Spring Framework projects

Na wstępie upewnij się, że:
- masz zainstalowaną javę w wersji minimum 11 (https://www.techspot.com/downloads/downloadnow/5553/?evp=cc1002b8f24231deaf82043061a06561&file=1&fbclid=IwAR1IUeql6lu0NdsOFQLjh05mwZ1JjX-80KQPfWxWqQ6V5y6qMFkpi9Kwo6I)
- ustawioną zmienną JAVA_HOME (https://docs.opsgenie.com/docs/setting-java_home)
- javę dodaną do PATHa (https://www.java.com/en/download/help/path.xml)

Instrukcja:
1. Pobrać kod aplikacji (zielony przycisk "Code" a później "Download ZIP")
2. Wypakować i przejść do folderu
3. W tym folderze odpalić konsolę (lub przejść w konsoli do tego folderu)
4.a. Wpisać w konsoli komendę: 
    ./mvnw spring-boot:run (linux)
    ./mvnw.cmd spring-boot:run (windows)  
4.b. Zbudować obraz dockerowy, a następnie go urchumomić używając poniższych komend:
    docker build -t json-flattener:1.0.0 . 
    docker run -e "SPRING_PROFILES_ACTIVE=deploy" -p 7777:7777 json-flattener:1.0.0
5. Wpisać w przeglądarce dany adres: http://localhost:7777/main

Funkcjonalności:
- formatowanie jsona tak jak jest to opisane na stonie: http://localhost:7777/about
- logowanie, rejestracja, wylogowanie
- zapisywanie jsonów i usuwanie ich ze swojego profilu

