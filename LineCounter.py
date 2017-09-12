## Author: Luka Mesarić

## Generates "AllLines.txt" file, countng the number of lines in this project.


'''So that **fhusnjak** may boost his LinesAdded counter on Git.'''


from os import listdir, getcwd
from os.path import isfile, join

def main():

    export = open("AllLines.txt", "w", encoding="utf-8")
    FINAL = ""
    cwd = getcwd()
    mypaths = ["Database",
               "Knjiznica\src\knjiznica",
               "Knjiznica\src\knjiznica\model",
               "Knjiznica\src\knjiznica\\view"]

    for mypath in mypaths:
        mypath = cwd+'\\'+mypath
        import_list = [mypath+'\\'+f for f in listdir(mypath) if isfile(join(mypath, f))]
                
        for file in import_list:
            imp = open(file, "r", encoding="utf-8")
            text = imp.read()
            imp.close()
            FINAL += text + '\n'

    numberOfLines = str(len(FINAL.split('\n')))
    export.write(numberOfLines + '\n\n' + FINAL)

    export.close()
    
    print('\nTXT file successfully generated.\n' +
          'There are {} lines!\n'. format(numberOfLines))

    return

start = input('Press Enter to start.')
main()
end = input('Press Enter to end.')
