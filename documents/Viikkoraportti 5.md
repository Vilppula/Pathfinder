## Viikkoraportti 5

### Edistyminen
Fringe Search algoritmi saatiin toimintakuntoon. Tämä edellytti hieman debuggausta Netbeanssilla, sillä koodin logiikkaan oli jäänyt muutama piilevä virhe. Jäljellä on enää olemasa olevien ominaisuuksien pientä hiomista sekä statistiikkanäkymän luominen. Muutettiin heuristista funktiota Manhattan-etäisyydestä Euklidiseen. Tämä mahdollistaa nyt A*:n Fringe Searchin oikeanlaisen toiminnan.

### Oppiminen
Fringe Search on saatu toimimaan. Algoritmin toimintaperiaate on nyt selkeästi mielessä ja sen toteutus on saatu tehtyä ohjelman vaatimuksiin sopivaksi. Koodin vertaisarvioinnin kautta on myös ollut mielenkiintoista nähdä miten eritavalla Javaa voidaan käyttää.

### Ongelmia
Viimeisen puuttuvan algortitmin mukaan tuominen ei sujunut aivan ongelmitta. Haku ei ensimmäisellä yrityksellä onnistunut löytämään maalisolmua, havaittiin että jonoon ei tullut lisää solmuja. Toisella yrityksellä algoritmi jäi ikuiseen silmukkaan ja havaittiin, että samoja solmuja lisättiin jatkuvasti uudelleen jonoon. Kaikki osoittautui lopulta johtuvan pienestä virheestä, jossa naapurisolmun lisääminen sallittiin siinäkin tapauksessa, että uusi etäisyysarvio oli yhtä suuri kuin edellisellä lisäyskerralla. Parin tunnin debuggaus-session jälkeen ongelmat olivat selvillä ja algoritmi toimi halutulla tavalla.

### Mitä seuraavaksi?
Luodaan näkymä, jossa algoritimin läpikäynnin aikaisia solmuihin liittyviä arvoja esitellään. Tähän kuuluu myös algoritmien aikavertailu ja toivottavasti myös tilankäytön vertailu. Tehdään Fringe Searchin graafinen esitys havainnollistavaksi. Yksittäisten solmujen/pikselien piirtämisen sijaan piirretään yhden iteraation aikainen 'solmurintama' yhdellä kertaa ja mahdollisesti havainnollistetaan tätä vielä muuttamalla piirtovärin arvoja, jolloin jokainen iteraatio on helpompi erottaa kartalta.