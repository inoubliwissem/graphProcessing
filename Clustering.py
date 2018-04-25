from math import sqrt
#We implement th SCAN alrorithm to graph clustering
#Modilarity Function
#In this function we make a a set of tribplet (node1, node2, 0) 
#And update this liste to calculate the similarity between okk tupe in the list
def modilarity(triplets,neighbors):
   result=[]
   #We calculate the similarity between tow nodes, and push this into triplets liste to update zero value 
   for elm in triplets:
      n1=elm[0]
      n2=elm[1]
      #calculate the similarity
      sim=float(len(set(adj[n1]).intersection(adj[n2])))/sqrt(len(set(adj[n1]+adj[n2])))
      n=(n1,n2,sim)
      result.append(n)
   return result
#This function to get a list of neighbors foreach node
#It takes a set of edges, and produce a list of couple "node: [list of neighbors]"
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
   	    	neighbors[b].append(a)
   	   else:
   	    	l=[a]
   	    	neighbors[b]=l
   return neighbors
def getStrongNeighbors(similarity,n,e):
   	nb=0
   	for elm in similarity:
   	   	if (elm[0]==n or elm[1]==n) and  elm[2]>=e :
   	   	   	nb=nb+1
   	return nb   	   	  

   #In this function we get a set of core node according the epsilon and lambda values
   #it takes the similarity triplets and the neighbors dictionnary
def getCore(simlarity, neighbors,e,l):
	nodes=neighbors.keys()
	c=[]
	for n in nodes:
		if len(neighbors[n]) >= l:
			StrongNeighbors=getStrongNeighbors(simlarity,n,e)
			if StrongNeighbors >= l:
				c.append(n)
	return c
def getNeighborsOfCore(cluster,neighbors):
	neighbor =[]
	for c in cluster:
		for n in neighbors[c]:
			neighbor.append(n)
	return neighbor
def getSim(similarity,n1,n2):
   	nb=0
   	for elm in similarity:
   	   	if (elm[0]==n1 and elm[1]==n2) or  (elm[0]==n2 and elm[1]==n1) :
   	   	   	return elm[2]
   	return nb
if __name__=='__main__' :
   #Load the graph from text file
   dataset=open("dataset2","r")
   #Get all lines of the dataset
   lines=dataset.readlines()
   #Dictionary to save for each vertices its neighbors
   adj={}
   #Liste of edges
   e=[]
   #Fill the dictionary and the list
   for l in lines:
     pair=l.strip().split("\t")
     if len(pair)>1 :
        (a,b)=pair
        a=int(a)
        b=int(b)
        e.append((a,b))
        if adj.has_key(a):
        	l=adj[a]
        	l.append(b)
        	adj[a]=l
        else:
        	adj[a]=[b]
        if adj.has_key(b):
        	l=adj[b]
        	l.append(a)
        	adj[b]=l
        else:
        	adj[b]=[a]
   #get the neighbors matrix
   ng=getNeighbors(e)
   #calculate the similarity 
   sim= modilarity(e,ng)
   #get the core nodes of the graph with epsilon = 0.5 and lambda = 2
   cores=getCore(sim, ng,0.5,2)
   print cores
   clusters=[]
   i=0
   #clusters.append([cores[0]])
   #cores.remove(cores[0])
   #show all cores nodes
   while(len(cores)>0):
       n=cores[0]
       #for each node we get all it neighbors
       neighbors_n=getNeighborsOfCore([n],ng)
       cluster=[]
       cluster.append(n)
       for m in neighbors_n:
       	   print n,m, getSim(sim,n,m)
           if getSim(sim,n,m) >= 0.5:
               cluster.append(m)
               #cores.remove(cores[m])
       clusters.append(cluster)
               
       cores.remove(cores[0])
   print clusters

     
   	   