# T-FINAL

## Īss projekta apraksts
---

Vienkāršs projekts, ar kuru spēsiet saglabāt finanšu datus. Projektā ir izmantots Java, MySQL un Swing priekš GUI/UI. Projekts ir pēc iespējas vienkāršāks, lai minimizētu jautājumus un atvieglotu dzīvi, bez nevajadzīgām vai liekām funkcijām. Stingra uzmanība pievērsta pie katra koda bloka, norādot komentārus, kas paskaidro, ko kods dara vai kam tas ir paredzēts.

### Java klases projektā ietilpst:
- **DatabaseManager**: Savienojums ar SQL datubāzi, datu tabulām, kā arī izdevumu/datu šķirošana un citas ekstras.
- **Expense**: Primitīva datu izvade un funkcijas, kas savienojas ar DatabaseManager.
- **ExpenseManagerGUI**: Primitīvs grafiskais lietotāja interfeiss (UI/GUI).
- **Main**: Klase GUI palaišanai. :)

## Instalācijas instrukcijas
---

1. **Lejupielādē kodu** no GitHub un atver to IntelliJ IDEA. Droši var arī izmantot drag-and-drop metodi!
2. **Lejupielādē un pievieno** MySQL Connector JAR failu (`mysql-connector-java-x.x.x.jar`) projektam.
   - Izveido jaunu folderi ar nosaukumu `lib` projektā un ieliec tajā JAR failu (arī iespējams drag-and-drop). 
   - Kad JAR fails ir pievienots, labo klikšķi uz tā un izvēlies **"Open library settings"**, lai to pievienotu projekta bibliotēkai.
3. **Izveido MySQL datubāzi** ar nosaukumu `Izdevumi` un tabulu `expenses` ar kolonnām `datums` (date), `summa` (DOUBLE), un `apraksts` (VARCHAR).
   
   Piemērs SQL `expenses` tabulas izveidei:
   ```sql
   CREATE TABLE expenses (
     id INT AUTO_INCREMENT PRIMARY KEY,
     datums DATE NOT NULL,
     summa DOUBLE NOT NULL,
     apraksts VARCHAR(255)
   );
4. **Nomaini datubāzes paroli** DatabaseManager klasē uz `savējo` MySQL paroli.
5. **Palaid Main java failu** un viss! :)

* Vienkāršs datu bāzes shēmas apraksts
  -----------------------------------

  Kā iepriekš norādīts instalācijas instrukcijās, 3. punktā:

  - **`datums`**: Izdevuma datums, glabājas kā datums formātā `YYYY-MM-DD`.
  - **`summa`**: Izdevuma summa, glabājas kā decimāls skaitlis (`DOUBLE`).
  - **`apraksts`**: Īss izdevuma apraksts, glabājas kā teksts (`VARCHAR(255)`).

* Ekrānuzņēmums no lietotāja saskarnes
  ------------------------------------
![image](https://github.com/user-attachments/assets/6ab046bc-e4b7-4c68-aa97-d65de73a6f63)
