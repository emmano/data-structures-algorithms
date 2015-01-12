import algorithms.MergeSort
import datastructures.dictionaries.HashTable
import datastructures.lists.LinkedList;
import datastructures.lists.Stack;

MergeSort.mergeSort([2, 1, 50, 24, 100, 21, 51, 33] as int[])

def linkedList = new LinkedList<String>()
linkedList.addEnd("Hello, World!")
linkedList.recursivePrint()

def hashTable = new HashTable<String, Integer>(10)
hashTable.put("one", 1)
println hashTable.get("one")

def stack = new Stack<String>()
stack.push("1")
stack.push("2")
stack.push("3")
stack.recursivePrint()