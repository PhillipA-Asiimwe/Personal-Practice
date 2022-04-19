PART ! :Implement Abstract data structures and take advantage of javas OOP to present clean, structured code I will implement a Triple ended Queue( Treque ) a Rootish Array Deque and a Table class that runs methods under a specificed runtime. 

Treque Fast Deque operations using an array
Triple ended que implements MyArrayDeque, this is a a data structure that effectively adds to one end of the sequence and removes from the other end allowing for effective addition and removal at both ends. 
ArrayDeque RunTime:
get(i) and set(i,x) in O(1)
add(i,x) and Remove(i) in O(1+min{i,n-i})
Performing any sequence of m add(i,x) and remove(i) operatations results on total O(m) time spent resizing. 

Rootish Array Deque 
Performance requirements
size(), get(i) and set(i,x) take constant time
remove(i) and add(i,x) O(1+min{i,n-i})

PhilsTable implements myabstract table implementation. 
Get() and set() take constant time
addRows(i) and removeRows(i) take O(1+row()-i) Time 
addCols(i) and removeCols(i) take O((1+cols()-i)*rows()) Time


Test the classes I created and make sure they work as intended. Testing for correctness and runtime. 

