from math import sqrt
#We implement th SCAN alrorithm to graph clustering
#Modilarity Function
#In this function we make a a set of tribplet (node1, node2, 0) 
# And update this liste to calculate the similarity between okk tupe in the list
def modilarity(triplets,neighbors):
   result=[]
   #We calculate the similarity between tow nodes, and push this into triplets liste to update zero value 
   for elm in triplets:
      n1=elm[0]
      n2=elm[1]
      print neighbors[n1]
      n=(n1,n2,2)
      result.append(n)
   return result
#this function to get a list of neighbors foreach node
#it takes a set of edges, and produce a list of couple "node: [list of neighbors]"
def getNeighbors(edges):
   neighbors={}
   for e in edges:
   	   a,b=e
   	   if neighbors.has_key(a):
   	        neighbors[a].append(b)
   	   else:
   	    	l=[b]
   	    	neighbors[a]=l
   	   if neighbors.has_key(b):
   	    	neighbors[a].append(a)
   	   else:
   	    	l=[a]
   	    	neighbors[b]=l
   return neighbors

if __name__=='__main__' :
   #load the graph from text file
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
        e.append((int(a),int(b)))
        v.append(int(a))
        v.append(int(b))
     else:
        v.append(int(pair[0]))

   #remove all duplicated vertcies in the list
   v=list(set(v))
   triplets=[]
   #generate the triplets store for the graph
   for i in xrange(len(v)):
      for j in xrange(len(v)):
         if i<j:
            t=(i,j,0)
            triplets.append(t)
   ng=getNeighbors(e)
   print modilarity(triplets,ng)
   
   #print set(ng[2]+ng[1])
   
   
