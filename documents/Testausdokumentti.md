## Testausdokumentti

### Testauskattavuus
Testauskattavuutta tarkistellaan Jacoco:n avulla.  

<img src="https://github.com/Vilppula/Pathfinder/blob/master/documents/Images/Jacoco.jpg" width=800>

Testauskattavuuden tarkistus suoritetaan aina testien yhteydessä, jonka jälkeen testauskattavuus löytyy html-muodossa polulta /build/jacocoHtml/index.html.

### Testattavat asiat
Testauksen keskiössä on verkonmuodostus, reitinhakualgoritmit sekä apuluokat.
Testausta ei ole tällä hetkellä toteutettu käyttöliittymäelementeille, tai JavaFX:n Node-tyyppejä sisältäville luokille.

Verkonmuodostamisesta vastaa luokka [GraphBuilder](https://github.com/Vilppula/Pathfinder/blob/master/src/main/java/Pathfinder/utility/GraphBuilder.java) jolla on vastaava [testiluokka](https://github.com/Vilppula/Pathfinder/blob/master/src/test/java/Pathfinder/utility/GraphBuilderTest.java).  
Testeissä keskitytään virheellisten syötteiden karsimiseen ja verkon muodostamiseen. Verkon solmua kuvaa luokka [Graphnode](https://github.com/Vilppula/Pathfinder/blob/master/src/main/java/Pathfinder/domain/Graphnode.java) jota testataan vastaavasti luokassa [GraphnodeTest](https://github.com/Vilppula/Pathfinder/blob/master/src/test/java/Pathfinder/domain/GraphnodeTest.java). Vaikka Graphnode-luokka sisältääkin enimmmäkiseen get- ja set-motodeja, se toimii myös tilastointitiedon tallentamisessa, joten testit pyrkivät ohjelman edetessä tarkastelemaan tiedon oikeellisuutta.

Algoritmien testaaminen on integraatiotestausta. Algoritmien testit väistämättä kohdistuvat myösalgortimin toiminnan perustana olevaan verkkoon ja siitä vastaaviin luokkiin. Testiluokka [DijkstraTest](https://github.com/Vilppula/Pathfinder/blob/master/src/test/java/Pathfinder/algorithms/DijkstraTest.java) testaa [Dijkstran algoritmin](https://github.com/Vilppula/Pathfinder/blob/master/src/main/java/Pathfinder/algorithms/Dijkstra.java) kykyä löytää lyhyin reitti. Koska [A*](https://github.com/Vilppula/Pathfinder/blob/master/src/main/java/Pathfinder/algorithms/AStar.java) on erikoistapaus Dijkstran algoritmista, toteutetaan samat [testit](https://github.com/Vilppula/Pathfinder/blob/master/src/test/java/Pathfinder/algorithms/AStarTest.java) myös kyseiselle luokalle.
### Syötteet
Syötteitä tarkastellaan tulevassa päivityksessä.

### Testien toistaminen
Testit voidaan toistaa komennolla
```bash
gradle build test
```

### Tuloksia
