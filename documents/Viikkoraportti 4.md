## Viikkoraportti 4

### Edistyminen
Ohjelma sisältää kaksi toimivaa algoritmia, jotka voidaan aktivoida karttapohjalla etsimään lyhyintä reittiä. Lyhyin reitti esitetään punaisella viivalla. Algoritmien etenemistä havainnollistetaan animaatioiden avulla.

### Oppiminen
Fringe Search:in perusidea selvillä ja alustava luokka rakennettu. Testaamisen hyödyt tulevat esille kun ohjelma monimutkaistuu. Toteutuksessa esiintyneet mysteerivirheet saatiin selville kirjoittamalla lisää yksikkö/integraatio testejä.
Käyttöliittymän toteutuksessa tullut taas tutuksi muutama uusi JavaFX:n tarjoama ominaisuus, kuten esimerkiksi Timeline-luokka.

### Ongelmia
Testeissä ja käyttöliittymän rakentamisessa selvisi, että Dijkstran, A*:n ja apuluokkien logiikassa oli puutteita. Jouduin rakentamaan osia niistä uudelleen. Käytössäni on NetBeansin TMC versio jonka käyttö Gradlen kanssa ei ole osoittautunut kovin toimivaksi.

### Mitä seuraavaksi?
Fringe Search liitetään mukaan, jolloin kaikki tarvittavat algoritmit on esitelty ja visualisoitu. Tutkitaan java.lang.instrument -kirjastoa, jonka avulla voidaan selvittää olioiden suoritusaikaista muistivarausta. Tutkitaan algoritmien haluttuja ja saavutettuja aikavaativuuksia. Lisätään käyttöliittymään osia.
