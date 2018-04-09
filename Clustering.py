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
      #calculate the similarity
      sim=float(len(set(adj[n1]).intersection(adj[n2])))/sqrt(len(set(adj[n1]+adj[n2])))
      n=(n1,n2,sim)
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
def getStrongNeighbors(similarity,n,e):
   	nb=0
   	for elm in similarity:
   	   	if (elm[0]==n or elm[1]==n) and  elm[2]>=e :
   	   	   	nb=nb+1
   	return nb   	   	  

   #In this function we get a set of core node according the epsilon and lambda values
   # it takes the similarity triplets and the neighbors dictionnary
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
if __name__=='__main__' :
   #load the graph from text file
   dataset=open("dataset2","r")
   #get all lines of the dataset
   lines=dataset.readlines()
   #dictionary to save for each vertices its neighbors
   adj={}
   #liste of edges
   e=[]
   # fill the dictionary and the list
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
   clusters=[]
   i=0
   clusters.append([cores[0]])
   cores.remove(cores[0])
   while(len(cores)>1):
   	  n=cores[0]
   	  added=0
   	  for c in clusters:
   	  	neighCores=getNeighborsOfCore(c,ng)
   	  	if n in neighCores:
   	  		c.append(n)
   	  		cores.remove(n)
   	  		added=1
   	  	if added==1:
   	  		break

   	   
