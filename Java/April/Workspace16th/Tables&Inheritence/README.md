PART ! :Implement Abstract data structures and take advantage of javas OOP to present clean, structured code I will implement a Triple ended Queue( Treque ) a Rootish Array Deque and a Table class that runs methods under a specificed runtime. 

Treque or Triple ended que implements MyArrayDeque, this is a a data structure that effectively adds to one end of the sequence and removes from the other end allowing for effective addition and removal at both ends. 
ArrayDeque RunTime:
get(i) and set(i,x) in O(1)
add(i,x) and Remove(i) in O(1+min{i,n-i})
Performing any sequence of m add(i,x) and remove(i) operatations results on total O(m) time spent resizing. 

Test the classes I created and make sure they work as intended. Testing for correctness and runtime. 

