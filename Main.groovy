import algorithms.MergeSort
import datastructures.dictionaries.HashTable
import datastructures.lists.LinkedList;

MergeSort.mergeSort([2, 1, 50, 24, 100, 21, 51, 33] as int[])

def linkedList = new LinkedList<String>()
linkedList.add("Hello, World!")
linkedList.recursivePrint()

def hashTable = new HashTable<String, Integer>(10)
hashTable.put("one", 1)
println hashTable.get("one")
