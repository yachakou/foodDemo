          Pré-requis : 
            - Java 8
            - Maven

##  Pour démmarer l'application, lancer l'une des commandes suivantes :


- `mvn spring-boot:run` 
- `mvn package` puis `java -jar target/demo-0.0.1-SNAPSHOT.jar`

## Pour consulter la base de données une fois l'application démmarer :

Lien : http://localhost:8080/h2-console/

- JDBC url : jdbc:h2:mem:testdb
- user name : sa 
- (pas de mot de passe )

La base de donné est une base H2 en mémoire qui demarre avec l'application.
Elle est aussi remplie avec une fichier de configuration (LoadDatabase.java) à partir du fichier csv.
C'est une base de donné relationnelle, ce qui permet de requeter comme pout tout autre base de donné relationnel (mysql, oracle...).  

## Consulter les elements 

- Pour consulter toutes les entrées : 
**GET http://localhost:8080/foods/**
- Pour consulter un éléments avec son id : 
**GET http://localhost:8080/foods/ID/**
- Si l'élément existe, le service retourne 200 avec le detail de l'élément comme suit : 
` {
      "foodId": 1,
      "foodName": "Angelica",
      "scientificName": "Angelica keiskei",
      "foodGroup": "Herbs and Spices",
      "foodSubGroup": "Herbs"
  }` 
- Si l'élément n'éxiste pas, le service retourne une code http 404 sans body.  

## Modifier ou creer un element 

- Pour modifier un élements : 
**PUT http://localhost:8080/foods/ID/** avec un body de type : 
 ` {
              "foodName": "foodname",
              "scientificName": "scientificname",
              "foodGroup": "group",
              "foodSubGroup": "subgroup"
  }` 
- Si l'élément existe et qu'il est modifié correctement, le service retourne une code HTTP 200. 
-Si l'élément que vous voulez modifier n'existe pas, il sera creer et le service retournera tout le detail de l'élément creer avec un code HTTP 201. 
