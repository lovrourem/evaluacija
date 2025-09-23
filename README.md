# Evaluacija
## Zadatak za procjenu

**UPUTE ZA POKRETANJE:**
* Klonirati GitHub repozitorij: `git clone https://github.com/lovrourem/evaluacija.git`
* Ući u lokalnu kopiju repozitorija : `cd evaluacija`
* Postaviti env varijable za spajanje na bazu podataka:
  * `set DB_URL="..."`
  * `set DB_USER="..."`
  * `set DB_PASS"..."`
* Pokrenuti backend:
  * `cd backend`
  * `mvnw.cmd spring-boot:run`
* U drugom prozoru ući u direktorij frontend
* Instalirati potrebne pakete: `npm install`
* Pokrenuti frontend: `npm run dev`
* Frontend dostupan na http://localhost:5173
* Backend dostupan na http://localhost:8080


##Implementacija
Implementirani su svi funkcionalni zahtjevi dani u zadatku

**FRONTEND**
* Login/registracija ekran
* Dashboard s listom zadataka
* CRUD operacije (dodaj, uredi, obriši)
* Označavanje zadatka kao završenog
* Filter: svi, završeni, nezavršeni
* Osnovno oblikovanje

**BACKEND**
* REST API koji podržava:
  * Autentikaciju
  * CRUD operacije nad zadacima
* PostgreSQL baza podataka
* Poštivanje REST konvencija (status kodovi, validacija, error handling)

Uz više vremena bavio bih se bonus zadacima, rubnim slučajevima i poboljšanjem sigurnosnih aspekata aplikacije.
