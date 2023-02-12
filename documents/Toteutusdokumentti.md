# Toteutusdokumentti

## Ohjelman rakenne
<img src="https://github.com/Vilppula/Pathfinder/blob/master/documents/Images/Rakenne.png" width=800>

#### Käyttöliittymä
Käyttöliittymän osat on tällä hetkellä kuvattu XML-dokumentissa Mainview. Käyttöliittymän logiikasta vastaa luokka MainviewController.
#### Apuluokat
- Apuluokkiin kuuluvat luokat MapHandler, joka tallentaa ohjelmalle annetun kuvatiedoston sekä alkuperäisessä muodossaan, että mustavalkoversiona. Mustavalkoversiota tarvitaan kaksiulotteisen taulukon muodostamisessa. Taulukko siirtyy luokalle GraphBuilder verkon muodostamista varten. 
- GraphBuilder tallettaa verkon solmuille (GraphNode) niiden vierussolmut ja etäisyydet näihin solmuihin. Lisäksi se laskee heuristiset etäisyydet maalisolmuun.
- Solver toimii paikkana algoritmien "kokoontumisajoille" sekä rajapintana käyttöliittymään. Täällä pidetään myös tallessa algoritmien statistiikkaa.
- Visualizer toimii käyttöliittymän jatkeena. Sillä on pääsy controllerin ImageView-instansseihin join se kuvaa reitin tai animoi algoritmien etenemistä kuvaavia pikseliesityksiä. Animaatioita varten on luokka NodeAnimation.
#### Domain-luokat
- GraphNode-luokka kuvaa yksittäistä verkon solmua. Se sisältää runsaasti oliomuuttujia statistiikkaa varten.
- NodeAnimation-luokka toteuttaa pikselianimaation joka esitetään piirtämällä pikseleitä JavaFX:n WritableImage- ja Timeline-luokkien avulla.
#### Algoritmit
- Dijkstran algortimi on toteutukseltaan oppikirjaversiota monimutkaisempi. GraphNode-olioita ei talleteta sellaisenaan prioriteettijonoon, vaan niistä luodaan jokaisella tarkastelukerralla kolmikko <d, y, x>, jossa d tarkoittaa etäisyyttä joka solmulla lisäyshetkellä oli.
- AStar-luokka toteuttaa A* algoritmin, joka on kuin Dijkstran algoritmi heuristiikalla paranneltuna. Siksi se perii luokan Dijkstra ja ylikirjoittaa sen osan jossa solmun jonoon/pinoon lisääminen tapahtuu.
- FringeSearch luokka toteuttaa saman nimisen algoritmin. Se on rakenteeltaan erilainen joten se ei peri muita luokkia.
- Calculable-rajapinta helpottaa ohjelma- ja testauslogiikan kanssa. Kaikki algoritmit toteuttavat tämän rajapinnan.
## Algoritmien saavutetut aikavaativuudet
Tarkentuu myöhemmin
## Puutteita/ parannusideoita
Verkon toteutus on erittäin raskas, joka onkin tarkoituksellista ohjelman perusajatukseen nähden. Optimaalisinta ratkaisua ei haeta, vaan yritetään havainnolistaa käyttäjälle näiden algortimien eroja. Logiikassa lienee tällä hetkellä paljonkin parantamisen varaa eikä tarkasteltavaa statistiikaa ole päätetty. Algoritmien toiminnassa saattaa olla tällä hetkellä pieniä puutteita.
## Projektissa käytetyt lähteet
- Fringe Search: [Björnson, Enzenberger, Holte, Schaeffer: Fringe Search: Beating A* at Pathfinding on Game Maps](https://webdocs.cs.ualberta.ca/~holte/Publications/fringe.pdf)
