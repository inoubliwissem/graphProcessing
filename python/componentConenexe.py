

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
#create inital "CC" component connexe
cc=[]
for elm in v:
   cc.append(list([elm]))
print "initial compenent connex",cc
#Matching of all edges 
for a in e:
   # for each edge get start  and the end from curent edge
   x=a[0]
   y=a[1]
   #matching of cc list
   i=-1
## indice whene the x and y is show 
   cc_x=-1
   cc_y=-1
   for elm in cc:
      i=i+1
      if x in elm:
#if x in the curent cc, we save the indice of this cc
         cc_x=i
      if y in elm:
#if y in the curent cc, we save the indice of this cc
         cc_y=i
#if x and y on the different cc
   if cc_y != cc_x :
#we memorise the centent of second cc (in genaral shu as lsit of nodes)
      cc_i=cc[cc_y]
      for x in cc_i:
#merge the second cc with the fisrt
         cc[cc_x].append(x)
#delet the second cc, because its saved on the fisrt cc
      del cc[cc_y]
print "we have ", len(cc)," component connexe"
print "as show : ",cc



   


   
