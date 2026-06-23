FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY ../pom.xml ./pom.xml
COPY PetFriends_Pedido/pom.xml ./PetFriends_Pedido/pom.xml
COPY PetFriends_Almoxarifado/pom.xml ./PetFriends_Almoxarifado/pom.xml
COPY PetFriends_Transporte/pom.xml ./PetFriends_Transporte/pom.xml
RUN mvn -pl PetFriends_Almoxarifado dependency:go-offline -B
COPY PetFriends_Almoxarifado/src ./PetFriends_Almoxarifado/src
RUN mvn -pl PetFriends_Almoxarifado package -DskipTests -B

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/PetFriends_Almoxarifado/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
