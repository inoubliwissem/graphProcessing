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
   #In this function we get a set of core node according the epsilon and lambda values
   # it takes the similarity triplets and the neighbors dictionnary
def getCore(simlarity, neighbors,e,l):
	nodes=neighbors.keys()
	for n in nodes:
		if len(neighbors[n]) > l:
			print n
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
   ng=getNeighbors(e)
   sim= modilarity(e,ng)
   for elm in sim:
   	   print elm
   #print float(len(set(adj[10]).intersection(adj[11])))/sqrt(len(set(adj[10]+adj[11])))
   getCore(sim,ng,0.7,2)
