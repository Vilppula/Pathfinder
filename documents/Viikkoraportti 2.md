## Viikkoraportti 2

Viikko kaksi tarjosi sekä hyviä hetkiä projektin edistymisen kanssa, että erittäin turhauttavia hetkiä konfiguraatioon liittyvien ongelmien kanssa. Onneksi rutiininomaista 'Googlettamista' on niin monta tuntia takana, että ongelmista päästiin eteenpäin.

### Edistyminen
Käyttöliittymän suunnittelua jatkettiin toisen viikon tiistaina. Painikkeet eri algoritmien valinnalle on lisätty ja niitä vastaavat metodit kontrolleriin on luotu. Hiiren kursorina näytetään reitin haun alkupistettä vastaava 'A' tai maalia vastaava 'B'.

Viikonloppuna aloitin kirjoittamaan Dijkstran agoritmin ja A*:n toimintaa alustavia apuluokkia. Testiluokkia pyritään täydentämään sitä mukaan kun niitä vastaaviin luokkiin kirjoitetaan metodeja/ ominaisuuksia. Perustestien luomisessa ei ole ongelmaa. Mielenkiintoista nähdä kuinka hyvin isompien kokonaisuuksien testaaminen tulee onnistumaan.

Myös Javadoc on pyritty pitämään ajantasaisena koko kirjoittamisen ajan.

### Oppiminen
Viikon kaksi alussa oppiminen ei liittynyt juurikaan algoritmeihin vaan lähinnä JavaFX:n ominaisuuksiin. Viikonlopun lähestyessä pohdinta keskittyi enemmän haluttujen algoritmien toteuttamiseen. Tällä viikolla aloitan Dijkstrasta, joka ei sinänsä sisällä mitään uutta opittavaa toteuttamiseen vaaditaan vain aiempien asioiden kertaamista.

### Ongelmia
Gradlenprojektin konfigurointiin liittyviä ongelmia ratkottiin useamman tunnin ajan tällä viikolla. Suurin ongelma syntyi lopulta JUnitin mukaan tuomisessa. Verkosta löytyvien kysymysten ja ratkaisujen perkaaminen ei johtanut haluttuun lopputulokseen. Päätin lopulta luoda uuden Gradle-projektin tyhjästä ja sainkin halutut pluginit toimimaan. Suurin ongelma liittyi Javan moduuleihin, joiden toimintaan en ole ehtinyt kunnolla tutustua, ja jollainen projektilla oli alun perin käytössä. Onneksi ohjelma ei ollut edennyt vielä alkua pidemmälle ja uusi projekti luotiin melko helposti. Tämän seurauksena Githubin repositoriossa olevissa commiteissa saattaa lukea "uusi alku".

Kurssimateriaaliin voisi kenties lisätä tarkempia ohjeita Gradleen liittyen?

### Mitä seuraavaksi?
Tämä kirjoitetaan sunnuntaina kun voidaan todeta kuinka paljon ohjelma on edistynyt.