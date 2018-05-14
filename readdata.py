import pandas as pd
import numpy as np
#import matplotlib.pyplot as plt
import requests
from elasticsearch import Elasticsearch
import json
#es = Elasticsearch([{'host': 'localhost', 'port': 9200}])
df_num = pd.read_csv('num.csv',sep=",",error_bad_lines=False, dtype='unicode')
#print df_num.columns
#print df_num.shape

df_sub = pd.read_csv('sub.csv',sep=",",error_bad_lines=False, dtype='unicode',encoding="utf-8")
#print df_sub.columns
#print df_sub.shape


df_pre = pd.read_csv('pre.csv',sep=",",error_bad_lines=False, dtype='unicode')
#print df_pre.columns
#print df_pre.shape

df_tag = pd.read_csv('tag.csv',sep="\t",error_bad_lines=False, dtype='unicode')
"""
print "num",df_num.columns
print "sub",df_sub.columns
print "pre",df_sub.columns
print "tag",df_tag.columns
"""
num_sub=pd.merge(df_num, df_sub,  how='left', left_on=['adsh'], right_on = ['adsh'])
#num_sub_tag=pd.merge(num_sub, df_tag,  how='left', left_on=['tag', 'version'], right_on = ['tag', 'version'])
num_sub= num_sub.replace(np.nan, '0', regex=True)
i=1
for index, row in num_sub.iterrows():
    data = {}

    try:
       data['adsh']=row['adsh']
       data['tag']=row['version']
       data['version']=row['version']
       data['ddate']=row['ddate']
       data['qtrs']=float(row['qtrs'])
       data['uom']=row['uom']
       data['coreg']=float(row['coreg'])
       data['value']=float(row['value'])
       data['footnote']=row['footnote']
       data['adsh']=row['adsh']
       data['cik'] = row['cik']
       data['name'] =row['name']
       data['countryba'] = row['countryba']
       data['stprba'] = row['stprba']
       data['cityba'] = row['cityba']
       data['zipba'] = row['zipba']
       data['bas1'] = row['bas1']
       data['bas2'] = row['bas2']
       data['countryma'] = row['countryma']
       data['stprma'] = row['stprma']
       data['cityma'] = row['cityma']
       data['zipma'] = row['zipma']
       data['mas1'] = row['mas1']
       data['mas2'] = row['mas2']
       data['countryinc'] = row['countryinc']
       data['stprinc'] = row['stprinc']
       data['ein'] = row['ein']
       data['former'] = row['former']
       data['changed'] = row['changed']
       data['afs'] = row['afs']
       data['wksi'] = row['wksi']
       data['fye'] = row['fye']
       data['form'] = row['form']
       data['period'] = row['period']
       data['fy'] = row['fy']
       data['fp'] = row['fp']
       data['filed'] = row['filed']
       data['accepted'] = row['accepted']
       data['prevrpt'] = row['prevrpt']
       data['detail'] = row['detail']
       data['instance'] = row['instance']
       data['nciks'] = row['nciks']
       data['tag'] = row['tag']
       data['version'] = row['version']
       data['custom'] = row['custom']
       data['abstract'] = row['abstract']
       data['datatype'] = row['datatype']
       data['iord'] = row['iord']
       data['crdr'] = row['crdr']
       data['tlabel'] = row['tlabel']
       data['foc'] = row['foc']
       json_data = json.dumps(data)
       print json_data
       #es.index(index='eco2', doc_type='num', id=i, body=json.dumps(data,encoding='latin1'))
       i=i+1
       print "saved",i
    except:
       print "erreur" 
       
    #print json.dumps(data,encoding='latin1')
#num_sub_tag_pre=pd.merge(num_sub_tag, df_pre,  how='left', left_on=['adsh'], right_on = ['adsh'])
#print num_sub_tag.shape
#print num_sub_tag.columns[0]
"""
sub
[u'adsh', u'cik', u'name', u'sic', u'countryba', u'stprba', u'cityba',
       u'zipba', u'bas1', u'bas2', u'baph', u'countryma', u'stprma', u'cityma',
       u'zipma', u'mas1', u'mas2', u'countryinc', u'stprinc', u'ein',
       u'former', u'changed', u'afs', u'wksi', u'fye', u'form', u'period',
       u'fy', u'fp', u'filed', u'accepted', u'prevrpt', u'detail', u'instance',
       u'nciks', u'aciks'
pre
adsh        object
report      object
line        object
stmt        object
inpth       object
rfile       object
tag         object
version     object
plabel      object
negating    object

"""
"""
df1 = df_num.replace(np.nan, '0', regex=True)
i=1
for index, row in df1.iterrows():
    data = {}
    try:
       data['adsh']=row['adsh']
       data['tag']=row['version']
       data['version']=row['version']
       data['ddate']=row['ddate']
       data['qtrs']=float(row['qtrs'])
       data['uom']=row['uom']
       data['coreg']=float(row['coreg'])
       data['value']=float(row['value'])
       data['footnote']=row['footnote']
       es.index(index='eco2', doc_type='num', id=i, body=json.dumps(data,encoding='latin1'))
       i=i+1
       print "saved",i
    except:
       print "erreur" 
    #print json.dumps(data,encoding='latin1')
"""
"""
i=1
df1 = df_pre.replace(np.nan, '0', regex=True)
for index, row in df_pre.iterrows():
    data = {}
    try:
       data['adsh']=row['adsh']
       data['report']=float(row['report'])
       data['line']=float(row['line'])
       data['stmt']=row['stmt']
       data['inpth']=row['inpth']
       data['rfile']=row['rfile']
       data['tag']=row['tag']
       data['version']=row['version']
       data['plabel']=row['plabel']
       data['negating']=row['negating']
       es.index(index='eco', doc_type='pre', id=i, body=json.dumps(data,encoding='latin1'))
       i=i+1
       print "saved",i
    except:
       print "erreur"

"""
    #print json.dumps(data,encoding='latin1')
#df0=pd.concat([df_num,df_sub],axis=1)
#print df_pre.dtypes
"""
for index, row in df_sub.iterrows():
    data = {}
    data['adsh']=row['adsh']
    data['cik'] = row['cik']
    data['name'] =row['name']
    data['countryba'] = row['countryba']
    data['stprba'] = row['stprba']
    data['cityba'] = row['cityba']
    data['zipba'] = row['zipba']
    data['bas1'] = row['bas1']
    data['bas2'] = row['bas2']
    data['countryma'] = row['countryma']
    data['stprma'] = row['stprma']
    data['cityma'] = row['cityma']
    data['zipma'] = row['zipma']
    data['mas1'] = row['mas1']
    data['mas2'] = row['mas2']
    data['countryinc'] = row['countryinc']
    data['stprinc'] = row['stprinc']
    data['ein'] = row['ein']
    data['former'] = row['former']
    data['changed'] = row['changed']
    data['afs'] = row['afs']
    data['wksi'] = row['wksi']
    data['fye'] = row['fye']
    data['form'] = row['form']
    data['period'] = row['period']
    data['fy'] = row['fy']
    data['fp'] = row['fp']
    data['filed'] = row['filed']
    data['accepted'] = row['accepted']
    data['prevrpt'] = row['prevrpt']
    data['detail'] = row['detail']
    data['instance'] = row['instance']
    data['nciks'] = row['nciks']
    data['aciks'] = row['aciks']
    json_data = json.dumps(data)
    #print json_data
    #es.index(index='eco', doc_type='sub', id=i, body=json.loads(json_data))
    #print "saved",str(i)
    #i=i+1
    
"""
"""
b=json.loads({"fp": "FY", "bas2": "SUITE 300", "bas1": "170 GREEN VALLEY PARKWAY", "period": "20151231", "countryba": "US", "adsh": "1599385", "stprma": "NV", "cityma": "HENDERSON", "zipma": "89012", "aciks": NaN, "detail": "1", "countryma": "US", "instance": "rxsf-20151231.xml", "mas2": "SUITE 300", "mas1": "170 GREEN VALLEY PARKWAY", "afs": "5-SML", "cityba": "HENDERSON", "zipba": "89012", "form": "10-K", "fye": "1231", "fy": "2015", "stprba": "NV", "filed": "20160328", "stprinc": "NV", "accepted": "2016-03-28 17:22:00.0", "former": NaN, "nciks": "1", "prevrpt": "0", "name": " INC.", "wksi": "0", "cik": "RX SAFES", "changed": NaN, "countryinc": "US", "ein": "272928918"})
rst=es.index(index='eco', doc_type='sub', id=444, body=b)
print rst
"""

#print df_sub.head()
#df_num['value'].plot().show()
#df0=pd.merge(df_num,df_sub,on='adsh')
#df1=pd.merge(df0,df_pre,on='adsh')
#df1=pd.merge(df_num,df_sub, on='adsh', how='outer')
#df1.to_csv("data2.csv", encoding='utf-8', index=False,sep=',')
#df1.to_csv("data.csv", encoding='utf-8', index=False,sep=',')


"""
n=open("pre.txt","r")
lines=n.readlines()
line_inc=[]
for elm in lines:
   if len(elm.split('\t')) == 10:
      line_inc.append(elm)
      
#print line_inc[0].split("\t")
#print len(lines)
#print len(line_inc)

for elm in line_inc:
   print elm.replace("\t",",").replace("\n","")
"""
