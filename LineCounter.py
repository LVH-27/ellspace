## Author: Luka Mesarić

## Generates "AllLines.txt" file, countng the number of lines in this project.


'''So that **fhusnjak** may boost his LinesAdded counter on Git.'''


from os import listdir, getcwd
from os.path import isfile, join

def main():

    export = open("AllLines.txt", "w", encoding="utf-8")
    FINAL = ""
    cwd = getcwd()
    
    mypathsDatabase = ["Database"]
    mypathsJava = ["Knjiznica\src\knjiznica",
                   "Knjiznica\src\knjiznica\model",
                   "Knjiznica\src\knjiznica\\view"]
    linesNumber = ['0', '0']

    mypathsAll = [mypathsDatabase, mypathsJava]
    i = 0

    for mypaths in mypathsAll:
        finalTemp = ""
        for mypath in mypaths:
            mypath = cwd+'\\'+mypath
            import_list = [mypath+'\\'+f for f in listdir(mypath) if isfile(join(mypath, f))]
                    
            for file in import_list:
                imp = open(file, "r", encoding="utf-8")
                text = imp.read()
                imp.close()
                finalTemp += text + '\n'
        FINAL += finalTemp
        linesNumber[i] = str(len(finalTemp.split('\n')))
        i += 1

    numberOfLines = str(len(FINAL.split('\n')))
    export.write(numberOfLines + '\n\n' + FINAL)

    export.close()
    
    print('''\nTXT file successfully generated.
There are {} lines!
{} in Database
{} in Java'''. format(numberOfLines, linesNumber[0], linesNumber[1]))

    return

start = input('Press Enter to start.')
main()
end = input('Press Enter to end.')
