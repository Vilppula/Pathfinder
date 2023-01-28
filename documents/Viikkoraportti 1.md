## Viikkoraportti 1

Ensimmäisellä viikolla aiheenvalinta muodostui odotetusti melko vaikeaksi. Esimerkkiaiheisiin tutustuessa kävi selväksi, että jos tähtään harjoitustyössäni täysin uusien aiheiden oppimiseen ei aika välttämättä riitä. Tämän vuoksi valintani osui jo ennalta hieman tuttuihin reintinhakualgoritmien laatimiseen. Muihin aiheisiin tutustuttiin pintapuoleisesti valintaa tehtäessä.

### Edistyminen
Projekti aloitettiin tutustumalla Gradlen käyttöön Java-aplikaation koonnissa. Alustava projektinhallinta oli suunniteltu tehtäväksi Mavenilla, mutta Gradlen käyttöä ehdotettiin kurssin vetäjän puolelta. Aplikaatio sisältää graafisen käyttöliittymän, joten JavaFX:n ja Gradlen yhteensovittamista tuli harjoitella muutaman tunnin verran. Pohja tälle saatiin luotua ja NetBeansissa tapahtuvaan kehitystyöhön on nyt mahdollista tuoda uusia FXML-dokumentteja.

### Oppiminen
Projektissa toteutetaan esimerkkiaiheissa mainitut A* ja Fringe Search algoritmit. Näiden toimintaperiaatteen ja aikavaativuuksien tarkasteluun on kulunut useampi tunti aikaa.

### Ongelmia
Käytännöntoteutuksessa suurin ongelma oli saada JavaFX-alusta toimimaan Gradle-projektissa. Itse Gradlen käyttöönotto onnistui sujuvasti kurssimateriaalin ohjeiden avulla, mutta JavaFX:ää ei oltu materiaalissa käsitelty ollenkaan. Tähän löytyi kuitenkin apua StackOverflown keskustelukanavilta ja esimerkkirepositorioista.

Algoritmien toiminta ei ole vielä lainkaan selvää. On syytä palautella rauhassa mielleen Tira-kurssilta tuttu Dijkstran algoritmi, jonka erityistapaus A* on. Fringe Searchin osalta opiskelu on vielä alkuvaiheessa. Etenkin aika- ja tilavaativuuksien järkevä tarkastelu ja algoritmien kattava testaaminen tulevat uskoakseni olemaan haastavia.

### Mitä seuraavaksi?
Perusajatus ohjelman toiminnasta on melko selvä ja se on kirjattu määrittelydokumenttiin. Käyttöliittymän perustoimintojen laatiminen aloitetaan seuraavaksi. Tämän lisäksi algorit toteuttavia java-luokkia aletaan hahmottelemaan ja yritetään luoda järkeviä yksikkötestejä niitä vastaaviin testiluokkiin.