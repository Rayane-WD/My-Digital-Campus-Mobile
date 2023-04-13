import random

# NOM * Prénom * classe * email * mdp * notes(25)
f = open("file.txt")
tab_eleve=[]

def makeMdp(nom, prenom, classe):
   return nom[0]+prenom[0]+classe

def makeNote():
   x = round(random.uniform(4,24), 1)
   if x>20:
      x-=4
   return str(x)+"0"


for line in f:
   list_raw = list(line.strip('\n').split(" "))

   if len(list_raw)==5 :
      tab_eleve.append(  [list_raw[0]  , list_raw[1], list_raw[2], list_raw[-1], makeMdp(list_raw[0],list_raw[1],list_raw[2])])

   elif len(list_raw)==6 :
      nom = list_raw[0]+ " "+list_raw[1]
      tab_eleve.append([ nom   , list_raw[2]  ,   list_raw[3]  , list_raw[-1]   ,      makeMdp(nom,list_raw[2],list_raw[3])    ])

   else:
      nom = list_raw[0]+" "+list_raw[1]+" "+list_raw[2]
      tab_eleve.append([ nom   , list_raw[3], list_raw[4], list_raw[-1],   makeMdp(nom,list_raw[3],list_raw[4])   ])

f.close()


f = open("file_esme_database.txt", "w")
for eleve in tab_eleve:
   #24+1 notes aléatoires
   all_notes=""
   for k in range(24):
      all_notes += makeNote()+"*"

   f.write(eleve[0]+"*"+eleve[1]+"*"+eleve[2]+"*"+eleve[3]+"*"+eleve[4]+"*"+all_notes+makeNote()+"\n")

f.close()