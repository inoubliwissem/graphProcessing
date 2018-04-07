#we implement th SCAN alrorithm to graph clustering
#modilarity function
#in this function we make a a set of tribplet (node1, node2, 0) 
# and update this liste to calculate the similarity between okk tupe in the list
def modilarity(triplets):
   result=[]
   result.append(triplets.get(0))
   return result
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
   print modilarity(triplets)
