

#lead graph from text file
dataset=open("dataset","r")
lines=dataset.readlines()
#list of vertices
v=[]
# liste of edge
e=[]
#get list of both edge and vertice from loaded graph
for l in lines:
   pair=l.strip().split("\t")
   if len(pair)>1 :
      (a,b)=pair
      e.append((a,b))
      v.append(a)
      v.append(b)
   else:
      v.append(pair[0])

#remove all duplicated vertcies in the list
v=list(set(v))
#create inital "CC"
cc=[]
for elm in v:
   cc.append(list([elm]))

for edge in e:
   print edge
   


   
