## Viikkoraportti 2

Viikko kaksi tarjosi sekä hyviä hetkiä projektin edistymisen kanssa, että erittäin turhauttavia hetkiä konfiguraatioon liittyvien ongelmien kanssa. Onneksi rutiininomaista 'Googlettamista' on niin monta tuntia takana, että ongelmista päästiin eteenpäin.

### Edistyminen
Käyttöliittymän suunnittelua jatkettiin toisen viikon tiistaina. Painikkeet eri algoritmien valinnalle on lisätty ja niitä vastaavat metodit kontrolleriin on luotu. Hiiren kursorina näytetään reitin haun alkupistettä vastaava 'A' tai maalia vastaava 'B'.

Viikonloppuna aloitin kirjoittamaan Dijkstran agoritmin ja A*:n toimintaa alustavia apuluokkia, sekä Dijkstran algoritmin alustavan toteutuksen. Testiluokkia pyritään täydentämään sitä mukaan kun niitä vastaaviin luokkiin kirjoitetaan metodeja/ ominaisuuksia. Perustestien luomisessa ei ole ongelmaa. Mielenkiintoista nähdä kuinka hyvin isompien kokonaisuuksien testaaminen tulee onnistumaan.

Myös Javadoc on pyritty pitämään ajantasaisena koko kirjoittamisen ajan. Lisättiin gradle.buildiin määrittely jacoco-raportin tekemiseen.

### Oppiminen
Viikon kaksi alussa oppiminen ei liittynyt juurikaan algoritmeihin vaan lähinnä JavaFX:n ominaisuuksiin. Viikonlopun lähestyessä pohdinta keskittyi enemmän haluttujen algoritmien toteuttamiseen. Tällä viikolla aloitan Dijkstrasta, joka ei sinänsä sisällä mitään uutta opittavaa toteuttamiseen vaaditaan vain aiempien asioiden kertaamista.

### Ongelmia
Projektin konfigurointiin liittyviä ongelmia ratkottiin useamman tunnin ajan tällä viikolla. Suurin ongelma syntyi lopulta JUnitin mukaan tuomisessa. Verkosta löytyvien kysymysten ja ratkaisujen perkaaminen ei johtanut haluttuun lopputulokseen. Päätin lopulta luoda uuden Gradle-projektin tyhjästä ja sainkin halutut pluginit toimimaan. Suurin ongelma liittyi Javan moduuleihin, joiden toimintaan en ole ehtinyt kunnolla tutustua. Ensimmäisessä projektissa mukana ollut module-info.java jätettiin pois. Onneksi ohjelma ei ollut edennyt vielä alkua pidemmälle ja uusi projekti luotiin melko helposti. Tämän seurauksena Githubin repositoriossa olevissa commiteissa saattaa lukea "uusi alku".

Kurssimateriaaliin voisi kenties lisätä tarkempia ohjeita Gradleen liittyen?

### Mitä seuraavaksi?
Seuraava vaihe lienee lisätä käyttöliittymään tarvittavia ominaisuuksia. Ainakin karttapohjan lataaminen ImageView-olioon tullaan toteuttamaan. Kenties kuvan kokoa on syytä rajoittaa ettei kuvan perusteella luotavasta verkosta tule liian suuri. Tämän lisäksi Dijkstan algoritmin ja sen apuluokkien yhteistoimintaa varten kirjoitetaan testejä. Alustava A*:n toteutus ja jonkinlainen pohja vertailugrafiikalle/ mittauksille.