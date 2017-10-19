
#define a function to get the best following vertice from a list of vertice
# the list of following vertice represented as a list of couple (v,distance) 
def getbest(liste,path):
   #triple to save tempral best distance it accordin the structure (v,d) (a,10)
   elm=()
   elm=liste[0]
   for x in liste:
       if int(elm[2]) > int(x[2]) and x[1] not in path :
          elm=x
   return elm

   
#lead graph from text file
dataset=open("dijkastra","r")
lines=dataset.readlines()
#list of vertices
v=[]
# liste of edge
e=[]
#get list of both edge and vertice from loaded graph
for l in lines:
   edge=l.strip().split("\t")
   if len(edge)>1 :
      e.append(edge)
      v.append(edge[0])
      v.append(edge[1])

#remove all duplicated vertcies in the list
v=list(set(v))
#inialise the begin vertice
s="a"
path=[]
path.append(s)
secc=[]
for elm in e:
   if elm[0]==s:
      secc.append(elm)
s=getbest(secc,path)
print secc
print s
"""
l=[]
path=[]
path.append('d')
l.append(('b',5))
l.append(('c',10))
l.append(('d',2))


print 'd' not in path

print getbest(l,path)  
"""

   
