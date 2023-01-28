## Pathfinder/ Vaatimusmäärittely

-Tietojenkäsittelytieteen kandiohjelma. Projektin kieli: Suomi. 
-Ohjelmointi tapahtuu javalla ja vertaisarviointiin toivotaan Java. Jos tämä ei ole mahdollista, toivotaan vaihtoehdoksi ensin C++, sitten Python. Muihin kieliin en ole juurikaan tutustunut.

### Ohjelman tarkoitus
Toteutetaan kolme yleisessä käytössä olevaa reitinhakualgoritmia ja esitellä niiden tehokkuuteen liittyviä mittaustuloksia erilaisilla pikselikartoilla. Ohjelmalla on graafinen käyttöliittymä, jonka kautta käyttäjä määrittelee kartan ja reitin päätepisteet. Tulokset esitellään algoritmien rinnakkaisena vertailuna. Pisteiden valinta tapahtuu hiirellä. Muita mahdollisia painikkeita eri vaihtoehtojen valitsemiseksi ennen vertailun tekemistä.

### Hakualgoritmien vertailua
Reitinhakualgoritmeja on kolme. Perinteinen ___Dijkstran algoritmi___, ___A*___, sekä ___Fringe Search___.
    Pyritään tutkimaan mikä vaikutus A*:n heuristisella lähestymistavalla on suorituskykyyn Dijkstran algoritmiin verrattuna ja voiko nopeutta parantaa edelleen siirtymällä Fringe Searchiin. Pyritään toteuttamaan testialusta näiden algoritmien vertailuun.

### Aikavaativuus

Dijkstran algoritmissa aikavaativuus O(n+m log n) binäärikeolla.
A*:n laatimisessa heuristinen funktio palauttaa pisteiden välisen manhattanetäisyyden, tai painotetun verkon tapauksessa kenties euklidisen etäisyyden. Pyritään mahdollistamaan tarkastelu molemmissa tapauksissa. Keskimääräinen aikavaativuuden odotetaan olevan polynominen. Sama koskee Fringe Search -algoritmia, jonka kuitenkin odotetaan olevan A*:a nopeampi.

### Lähteitä
Muita algoritmien opiskeluun käytettyjä lähteitä ovat:


### Projektin alkumäärittely.
Projektin kielenä sekä dokumentoinnissa, että koodin kommentoinnissa on suomi.
Projekti toteutetaan Java-ohjelmointikielellä ja projektin hallintaan käytetään Gradle:a.
Graafinen ulkoasu rakennetaan JavaFX-alustalle FXML-dokumentein kuvailtuna.

