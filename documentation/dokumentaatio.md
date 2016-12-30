# Wepakalenteri

Eero Kuurne (014467642)


Sami Bäckroos (014450565)


Marianne Närhi (014339356)



## Yleistä

Tarkoituksena on luoda web-pohjainen kalenterisovellus, jossa käyttäjä voi itse valita näkyvillä olevan ajanjakson pituuden vapaasti. Kalenteriin voi lisätä tapahtumia, joilla on alku- ja loppuajankohdat sekä muuta tarpeellista infoa. Nämä ajankohdat näytetään kalenterissa, jos ne osuvat näytetylle aikavälille. 


Käyttäjät voivat myös lisätä toisiaan kavereiksi. Tämän tehtyään käyttäjät voivat lisätä kavereitaan omiin tapahtumiinsa osallistujiksi ja mikäli lisätty käyttäjä hyväksyy osallistumisen näytetään tapahtuma myös hänen kalenterissaan. Tapahtuman omistaja sekä osallistujat pystyvät myös lisäämään tapahtumaan kommentteja.


### Linkkejä

Sovellus löytyy osoitteesta: https://wepakalenteri.herokuapp.com.


Lähdekoodi löytyy Githubista: https://github.com/eekuurne/wepakalenteri.


## Järjestelmän tietosisältö

###Käsitekaavio
![Käsitekaavio](relations.png)


## Yleiskuva järjestelmästä

###Käyttötapauskaavio
![Käyttötapauskaavio](use_case.png)


### Käyttäjäryhmät

**Anonyymi käyttäjä**

Käyttäjätunnukseton käyttäjä, joka on olemassa vain login- ja rekisteröitymissivuilla.


**USER**

Tavallinen kirjautunut käyttäjä, joka voi käyttää kaikkia sivuston toimintoja lukuunottamatta admintoimintoja. Rekisteröidyt tunnukset ovat kaikki tällaisia.


**ADMIN**

Adminilla on kaikki *USER*in oikeudet, mutta pystyy lisäksi käyttämään admintoimintoja sivulla "/admin".


### Käyttötapauskuvaukset

**Anonyymi käyttäjä**

- Muut käyttötapaukset: login, rekisteröityminen


Kaikki *USER* ja/tai *ADMIN* oikeudet vaativat käyttätapaukset vaativat kirjautumisen (ja rekisteröitymisen).


**USER**

- Kalenterin näyttöalueen muuttaminen:
  * Käyttäjä navigoi kalenterisivulle (login uudellenohjaa tänne).
  * Käyttäjä valitsee haluamansa aikavälin sivun ylälaidan. valitsimilla (joillakin selaimilla 2 input laatikkoa) ja painaa "Show" -nappia.
  * Jos käyttäjä valitsi validit päivät (alku ennen loppua) näytetään käyttäjälle valitsimen alla olevassa kalenterissa haluttu aikaväli ja siihen liittyvät tapahtumat kokonaisina viikkoina. Jos käyttäjä ei syöttänyt valideja arvoja ohjelma käyttää valmiita defaulttiarvoja.
- Kaverien hallinta:
  * Käyttäjä navigoi profiilisivulle
  * Käyttäjä käyttää profiilisivulla "Friends" osion nappeja ja kaverinlisäysformia kavereiden lisäykseen ja poistoon
  * Huomio: Kaverin lisäämisen jälkeen toisen käyttäjän on hyväksyttävä kaveripyyntö
- Eventin osallistujien hallinta
  * Esivaatimukset: eventin lisääminen, eventtien katselu
  * Kun käyttäjä on navigoinut jonkin omistamansa eventin sivulle, hän voi "Participants" -osion napeilla ja inviteformin avulla hallita eventin osallistujia
  * Huomio: Kutsuttujen osallistujien on hyväksyttävä kutsu proofiilin "Pending invites" -osiosta nähdäkseen tapahtuman kalenterissaan. Kommentointi ja eventin sivulla vierailu on silti mahdollista hyväksymättömän kutsun kanssa, kunnes kutsu hylätään.
- Omien osallistumisten hallinta
  * Käyttäjä voi hyväksyä kutsuja tapahtumiin profiilin "Pending invites osiosta"
  * Hyväksyttyään kutsun käyttäjä voi poistaa itsensä tapahtuman osallistujista suorittamalla käyttötapauksen *Eventtien katselu* ja painamalla "Remove" -nappia oman nimensä perästä "Participants" -osiossa.
- Kommentointi
  * Esivaatimus: eventtien katselu
  * Jos käyttäjä on kutsuttuna tapahtumaan voi hän kommentoida sitä kirjoittamalla kommenttinsa tapahtuman sivun alalaidassa olevaan tekstilaatikkoon ja painamalla "Send" -nappia.
- Eventtien katselu
  * Käyttäjä voi katsoa haluamansa eventin sivua, joko profiilin "Pending invites" -osion kautta, tai kalenterin kautta eventin otsikkoa klikkaamalla.
  * Huomio: Jos päivällä on enemmän kuin 3 tapahtumaa (alkua ja/tai loppua) Käyttäjän täytyy painaa haluamansa päivän kohdalta "..." -nappia, nähdäkseen kaikki päivän tapahtumat.
- Eventin lisääminen
  * Käyttäjä voi lisätä eventin kalenteriinsa painamalla navbaarista "New event" -nappulaa tai painamalla haluamansa päivän kohdalta "+" -nappia.
  * Käyttäjälle näytetään tämän jälkeen eventin luomislomake, johon käyttäjä täyttää haluamansa tiedot (title ja ajat ovat pakollisia).
  * Käyttäjä ohjataan juuri luomansa tapahtuman sivulle.
- Eventin poistaminen ja editointi
  * Esivaatimus: eventtien katselu
  * Muokatakseen tai poistaakseen tapahtuman käyttäjä voi painaa tapahtuman sivulla "Edit" -nappia.
  * Käyttäjälle näytetään editointi näkymä, jossa käyttäjä voi muokata tapahtuman tietoja.
  * Tallentaakseen muutokset käyttäjän on painettava "Save changes" -nappia. Poistaakseen tapahtuman käyttäjän on painettava "Delete" -nappia.
- Muut käyttötapaukset: Kalenterin katsominen, logout


**ADMIN**
- Käyttäjien poistaminen
  * Admin navigoi selaimensa kautta "/admin" -sivulle (tälle sivulle ei ole UI:ssä nappia).
  * Admin käyttää käyttäjien poistolomaketta poistaakseen käyttäjän ja kaiken tämän sisällön sivulta.
  * Huomio: Admin voi halutessan poistaa itsensä. Tämä ei kuitenkaan ole suositeltavaa sillä uusien admin tunnusten luominen on tehtävä DB:n kautta.
- Muut käyttötapaukset: Käyttäjien selaaminen


### Toteutetut käyttötapaukset

- Rekisteröityminen
- Login/logout
- Kalenterin katsominen
- Kalenterin näyttöalueen muuttaminen
- Kaverien hallinta
- Eventin osallistujien hallinta
- Omien osallistumisten hallinta
- Kommentointi
- Eventtien katselu
- Eventtien lisääminen
- Eventtien poistaminen ja editointi
- Admintoiminnot (käyttäjien poistaminen)


### Toteuttamattomat käyttötapaukset

- Muut admintoiminnot


## Testaamiseen

### Käyttäjätunnuksia testausta varten

- User1, Password
- User2, Password
- User3, Password
- Admin, Admin


## Paranneltavaa ja mietteitä




