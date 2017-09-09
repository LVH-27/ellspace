## Author: Luka Mesarić

## Generira "PopulateAll.sql" file. Overwrita postojeći,
## ali se slobodno može pokretat ponovno i ponovno jer se generirani
## sql ne smije mijenjat. Tj. ako treba, radimo to preko ovog generatora.
## Tko se hoće žalit da je loše napisano, slobodno može,
## ali ovo dovoljno brzo radi :P


def main():

    export = open("PopulateAll.sql", "w", encoding="utf-8")

    import_list = ["ModelKnjiznica_postgres_create.sql",
                   "PopulateTableCity.sql",
                   "PopulateTableEventTypeList.sql",
                   "PopulateTableGenreList.sql",
                   "PopulateTableLanguageList.sql",
                   "PopulateTableLocationList.sql"]

    FINAL = ""
    
    for file in import_list:
        imp = open(file, "r", encoding="utf-8")
        text = imp.read()
        imp.close()
        FINAL += text + '\n\n'

    export.write(FINAL)

    export.close()
    
    print('\nSQL file successfully generated.\n')

    return


main()
end = input('Press Enter to end.')


