## Viikkoraportti 3

Dijkstran ja A*:n toteuttaminen on saatu valmiiksi. Myös käyttöliittymän kehitys on edennyt mukavasti. Hankalimman toteutuksen, eli Fringe Searchin kanssa ollaan vielä sen toiminnan tarkastelun vaiheessa.

### Edistyminen
Kahden edellä mainitun algoritmin toteutus on tehty. Näitä ei vielä tässä vaiheessa ole liitetty ohjelman runkoon, mutta ne kykenevät ratkaisemaan ainakin pienessä testiverkossa lyhyimmän reitin. Eniten työtä on vaatinut verkonmuodostamisesta vastaavan luokan laatiminen. Tässä on alustavasti otettu jo huomioon ohjelman käyttötarkoitus, eli algoritmien vertailu. Yksinkertaisen toteutuksen sijaan verkko on rakennettu melko raskaaksi. Jokaista karttapohjan pikseliä vastaa yksi Graphnode-luokan olio, joille pitäisi olla helppoa lisätä ominaisuuksia joilla tilastointi ja lopputulosten havainnollistaminen sujuu melko helposti.

### Oppiminen
Toistaiseksi algoritmiikan puolella A*:n toimintaperiaate on tullut tutuksi. Melko yksinkertaisella toteutuksella Dijkstran algoritmia voidaan tehostaa heuristiikkaa hyödyntäväksi. Käytännön toteutuksessa 

### Ongelmia
Käyttöliittymään on toteutettu kuvatiedoston lataamisominaisuus. Karttapohjan muuntaminen mustavalkomuotoon ei ollut aivan yksinkertaista. Yksiselitteistä ratkaisua tähän ei löytynyt ja päädyin yhdistämään omaan arviooni perustuvan harmaasävymuutoksen, jonka pikseleille asetettiin jokin raja-arvo jonka mukaisesti pikselien väri jaettiin joko mustaan tai valkoiseen. Tämä säätö saattaa osoittautua ongelmalliseksi joidenkin kuvatiedostojen tapauksessa. Tähän toki on todettava, että karttapohjien oletetaan olevan jo valmiiksi helposti muunnettavassa muodossa.

Melko alkeellinen virhe meinasi syntyä prioriteettijonon ja olioviitteiden kohdalla. Olioviite, kuten kurssin ohjaaja ystävällisesti vihjaisi, ei sellaisenaan sovi prioriteettijonon alkioksi. Graphnode-olion etäisyysarvoa tullaan säätämään reitinhaun aikana ja tämä aiheuttaisi ongelman jo jonossa olevien olioiden järjestyksessä. Tähän ratkaisuna luotiin prioriteettijono, jossa kolmiokoita muotoa <d, x, y>, jossa d tarkoittaa etäisyysarvoa joka solmulla (oliolla) oli jonoon lisäämisen aikaan.

### Mitä seuraavaksi?
Tärkein vaihe seuraavaksi suunnitella oma versioni Fringe Searchista. Tämän toivotaan onnistuvan kurssimateriaalissakin linkatun julkaisun (Fringe Search: Beating A* Pathfinding on Game Maps: Björnson, Enzenberger, Holte & Schaeffer) ja vastaavankaltaisista toteutukista.
Käyttöliittymän kehitys jatkuu ja algoritmien analysoitavien ominaisuuksien määrittelyä jatketaan, kenties jonkinlaista graafista ilmettä tätä varten.