from math import sqrt
#We implement th SCAN alrorithm to strucural graph clustering
#Modilarity Function
#In this function we make a a set of tribplet (node1, node2, 0) 
#And update this liste to calculate a similarity between each tuple in the list
def modilarity(triplets,neighbors):
   result=[]
   #We calculate the similarity between two nodes, and push this into triplets liste 
   for elm in triplets:
      n1=elm[0]
      n2=elm[1]
      #calculate the similarity
      sim=float(len(set(neighbors[n1]).intersection(neighbors[n2])))/sqrt(len(neighbors[n1])*len(neighbors[n2]))
      n=(n1,n2,sim)
      result.append(n)
   return result
#This function to get a list of neighbors foreach node
#It takes a set of edges, and produce a list of couple "node: [list of neighbors]"
def getNeighbors(edges):
   neighbors={}
   for e in edges:
   	   a,b=e
           if not neighbors.has_key(a):
              neighbors[a]=[a]
           if not neighbors.has_key(b):
              neighbors[b]=[b]
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

   #In this function we get a set of core node according the epsilon and mu values
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
def getStrongNeighborsOfCore(core,neighbors,sim,epsilon):
	neighbor =[]
	for v in neighbors[core]:
           if getSim(sim,core,v) >= epsilon:
              neighbor.append(v)
	return neighbor

def getBridgeVetices(clusters,vertices,neighbors):
	bridges=[]
        outlries=[]
        rst=[]
        for v in vertices:
           ngs=neighbors[v]
           i=0
           for y in clusters:
                 if len(list(set(y).intersection(set(ngs))))>0:
                    i=i+1
           if i>1:
              bridges.append(v)
           else:
              outlries.append(v)
        rst.append(bridges)
        rst.append(outlries)
	return rst

def getSim(similarity,n1,n2):
   	nb=0
   	for elm in similarity:
   	   	if (elm[0]==n1 and elm[1]==n2) or  (elm[0]==n2 and elm[1]==n1) :
   	   	   	return elm[2]
   	return nb

if __name__=='__main__' :
   #Load the graph from text file
   #dataset=open("dataset2","r")
   dataset=open("G2","r")
   #Get all lines of the dataset
   lines=dataset.readlines()
   #Dictionary to save for each vertices its neighbors
   adj={}
   #Liste of edges
   e=[]
   #liste of vertices
   v=[]
   #Fill the dictionary and the list
   for l in lines:
     pair=l.strip().split("\t")
     if len(pair)>1 :
        (a,b)=pair
        a=int(a)
        b=int(b)
        v.append(a)
        v.append(b)
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
   v=list(set(v))
   #get the neighbors matrix
   ng=getNeighbors(e)
   #calculate the similarity 
   sim= modilarity(e,ng)
   #get the core nodes of the graph with epsilon = 0.5 and lambda = 2
   cores=getCore(sim, ng,0.5,3)
   clusters=[]
   #show all cores nodes
   print "list of a cores vertices",cores
   print v
   while(len(cores)>0):
       n=cores[0]
       #for each node we get all it neighbors
       neighbors_n=getNeighborsOfCore([n],ng)
       cluster=[]
       cluster.append(n)
       for m in neighbors_n:
           if getSim(sim,n,m) >= 0.7:
               cluster.append(m)
               sn=[]
               if m in cores:
                  sn=getStrongNeighborsOfCore(m,ng,sim,0.7)
                  cluster=list(set(sn+cluster))
                  cores.remove(m)
                  v=list(set(v)-set(sn+cluster))
       cluster=list(set(sn+cluster))
       v=list(set(v)-set(sn+cluster))
       clusters.append(cluster)   
       cores.remove(cores[0])
   print "cluster mapping ",clusters
   b=getBridgeVetices(clusters,v,ng)
   print "list of bridges: ",b[0]
   print "list of outliers: ", b[1]

     
   	   
