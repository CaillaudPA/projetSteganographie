from numpy.random import randint
from collections import Counter

sObs = [sum(randint(1,7,size = 2)) for k in range (144000)]
cObs = Counter(sObs)

print cObs

from pygal import *

toscane = HorizontalBar()

for v in cObs:
	toscane.add(str(v), cObs[v])

toscane.render_to_file("toscane.svg")

sTheo = [x + y for x in range(1,7) for y in range(1,7)]
cTheo = {x : v*len(sObs)/len(sTheo) for x, v in Counter(sTheo).items()}
khi2Details = [(x, ((cObs[x] - cTheo[x]) ** 2) /cTheo[x]) for x in cTheo]
khi2 = sum([((cObs[x] - cTheo[x]) ** 2) /cTheo[x] for x in cTheo])

print sTheo
print cTheo
print khi2Details
print khi2
