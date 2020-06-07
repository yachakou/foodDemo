          Pré-requis : 
            - Java 8
            - Maven

##  Pour démarrer l'application, lancer l'une des commandes suivantes :

- `mvn package` obligatoirement.
- `mvn spring-boot:run` 
ou `java -jar target/demo-0.0.1-SNAPSHOT.jar`/.

## Pour consulter la base de données une fois l'application démarrée :
Lien : http://localhost:8080/h2-console/

- JDBC url : jdbc:h2:mem:testdb
- user name : sa 
- (pas de mot de passe )

La base de donné est une base H2 en mémoire qui démarre avec l'application. 
Elle est aussi remplie avec un fichier de configuration (Load Database.java) à partir du fichier Csv.
 C'est une base de donné relationnel, ce qui permet de requêter comme pout tout autre base de donné relationnel (mysql, oracle...).
## Consulter les éléments 

- Pour consulter toutes les éléments : 
**GET http://localhost:8080/foods/**
- Pour consulter un élément avec son id : 
**GET http://localhost:8080/foods/ID/**
- Si l'élément existe, le service retourne 200 avec le detail de l'élément comme suit : 
` {
      "foodId": 1,
      "foodName": "Angelica",
      "scientificName": "Angelica keiskei",
      "foodGroup": "Herbs and Spices",
      "foodSubGroup": "Herbs"
  }` 
- Si l'élément n'existe pas, le service retourne un code HTTP 404 sans body.  

## Modifier ou créer un element

- Pour modifier un élement : 
**PUT http://localhost:8080/foods/ID/** avec un body de type : 
 ` {
              "foodName": "foodname",
              "scientificName": "scientificname",
              "foodGroup": "group",
              "foodSubGroup": "subgroup"
  }` 
-Si l'élément existe et qu'il est modifié correctement, le service retourne une code HTTP 200. 
-Si l'élément que vous voulez modifier n'existe pas, il sera créer et le service retournera tout le detail de l'élément créer avec un code HTTP 201. 
