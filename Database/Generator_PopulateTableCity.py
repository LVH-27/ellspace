## Author: Luka Mesarić

## Generira "PopulateTableCity.sql" file. Overwrita postojeći,
## ali se slobodno može pokretat ponovno i ponovno jer se generirani
## sql ne smije mijenjat. Tj. ako treba, radimo to preko ovog generatora.
## LONGER i SHORTER su kopirani s weba, ova skripta ih
## samo mergea i konvertira u SQL.
## Tko se hoće žalit da je loše napisano, slobodno može,
## ali ovo dovoljno brzo radi :P


def main():

    importLonger = open("ResourceCityLonger.txt", "r", encoding="utf-8")
    importShorter = open("ResourceCityShorter.txt", "r", encoding="utf-8")

    LONGER = importLonger.read()
    SHORTER = importShorter.read()

    importLonger.close()
    importShorter.close()

    LONGER_set = set()
    SHORTER_set = set()
    TOTAL = []
    LONGER_id = 0

    for item in LONGER.split('\n'):
        LONGER_set |= {item[:5]}
        
        temp = item.split('\t')
        
        if LONGER_id != temp[0]:
            TOTAL.append([temp[0], temp[2]])
            LONGER_id = temp[0]

    del temp, item

    for item in SHORTER.split('\n'):
        SHORTER_set |= {item[:5]}
        
        temp0 = item[:5]
        temp1 = item[6:]
        
        for element in TOTAL:
            if element[0] == temp0:
                break

        else:
            TOTAL.append([temp0, temp1])
            
    FINISHED = sorted(TOTAL, key = lambda x: int(x[0]))

    for item in FINISHED:
        if item[0] == '10110':
            item[1] = 'Zagreb-Jarun'
            break
    
    print('Extra in SHORTER: {}\n'. format(SHORTER_set - LONGER_set))

    check = set()
    for pair in FINISHED:
        x = pair[1]
        if x in check:
            print("DUPLO!", x)
        check |= {x}

    db = open("PopulateTableCity.sql", "w", encoding="utf-8")

    for item in FINISHED:
        db.write("INSERT INTO \"City\" VALUES({}, '{}');\n". format(item[0], item[1]))

    db.close()
    
    print('\nSQL file successfully generated.\n')

    return


main()

