package datastructures.dictionaries;

import com.sun.istack.internal.NotNull;

/**
 * Created by emmanuelortiguela on 12/29/14.
 *
 * This is a really simplistic implementation of a Hashtable. It has obvious limitations such fixed size, no Object
 * based search (i.e. no get(V object)), hashFunction() returns an int instead of long, etc. This implementation uses
 * an Entry[]. Each index of the array represents a "bucket" that will contain Entries based on the Entry key's hashCode.
 * Note that two keys might map to the same "bucket" on the array.
 */
public class HashTable<K, V> {

    //This is number should be a large prime number; it helps reduce collisions.
    private static final int N = 16908799;

    private Entry[] table;
    private int capacity;
    private int size;

    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    public int size() {
        return size;
    }

    /**
        Here we first look to see if there is an Entry for the key that was passed in inside our table[] of "buckets".
        We do so by generating the hashcode for the key and passing it to the compression function. This gives us the index
        of the bucket that contains our key (and all keys that generate the same hashcode as the one we are looking for; a.k.a collisions)
        If there is already an Entry for our key one of two things happened:

        1) We are entering a new value for an existing key.
        2) A collision occurred

        If we are on case 1, we create a new Entry and add it to the front of our "LinkedList". This HashTable implementation
        will only return the latest value added to a key. It will not return all values for one key.

        If we are on case 2, we also add the Entry to the "LinkedList". This is why we need to store both the key and value in our Entry
        Object; different keys can map to the same bucket in tables[] (although we try to minimize this scenario). If we do not have
        the key stored, we would not be able to implement a get() that returns the right Object for a given key.

        @param key The key that will reference value
        @param value The value to insert on the Hashtable

    */
    public void put(@NotNull K key, V value) {
        int hash = hashFunction(key.toString());
        int bucketIndex = compressionFunction(hash);
        if (table[bucketIndex] != null) {
            Entry<K, V> bucketEntry = table[bucketIndex];
            bucketEntry.next = new Entry<K, V>(key, value);
        } else {
            table[bucketIndex] = new Entry<K, V>(key, value);
        }
        size++;
    }


    /** Same bucket lookup as above. Note that the condition of our while loop does two things:

    1) It stops the loop if we have reached the end of our "LinkedList"
    2) It makes sure that we search until we find the first occurrance of the specific key whose value we are looking for.
       Remember two keys might produce the same hashcode, so just returning the value for the first key we find is wrong.

     @param key The key Object whose value we want to get
    */

    public V get(K key) {
        int hash = hashFunction(key.toString());
        int bucketIndex = compressionFunction(hash);
        Entry<K, V> entry = table[bucketIndex];
        //Loop while we have not reached the end of the "LinkedList" and key does not match the key for the current Entry
        while (entry != null && entry.key != key) {
            entry = entry.next;
        }
        //We might be asked to look for an Entry that has not been inserted in the Hashtable. In that case we return null.
        if (entry == null) {
            return null;
        }
        return entry.value;
    }

    /**
      Same bucket lookup as above.

      @param key The key of the Object to be removed.
     */

    public boolean remove(K key) {
        int hash = hashFunction(key.toString());
        int bucketIndex = compressionFunction(hash);
        Entry<K, V> entry = table[bucketIndex];
        //We might be asked to remove and Entry that was never added.
        if (entry == null) {
            return false;
        }

        Entry<K, V> prevEntry = null;
        //Loop while we have not reached the end of the "LinkedList" and key does not match the key for the current Entry
        while (entry.next != null && entry.key != key) {
            prevEntry = entry;
            entry = entry.next;
        }
        //At this point we reached the end of the "LinkedList" and there are no matches for our key, return.
        if(entry.key != key){
            return false;
        }

        //If prevEntry is null at this point, it means that there is only one Entry in our "bucket" and its key matches the one we are looking for.
        if (prevEntry == null) {
            //We set the bucket to null; we are guaranteed there are no other Entries in this bucket; if prevEntry is null it means the while loop did not run.
                table[bucketIndex] = null;
                size--;
                return true;
        }

//If we got to this point, it means we found the Entry we are looking for and we can therefore remove it from the "LinkedList"
        prevEntry.next = entry.next;
        size--;
        return true;
    }

    /**
      This method is one of the two most important methods of the Hashtable (the other being compressionFunction()).
      Here we create a unique, numeric representation for our key. Notice that this method will probably produce an int
      representation of our key that is larger than the capacity of our Hashtable.

      @param key A String representation of the key Object K.

    */
    private static int hashFunction(String key) {
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++) {
            hashVal = (127 * hashVal + key.charAt(i));
        }
        return hashVal;
    }

    /**
     This method is responsible for "shrinking" the int we get from hashFunction() so it "fits" inside our bucket. By
     "fits" we mean that the number returned from this function will be in the range [0,capacity) (i.e 0 to capacity-1).
     It is an interesting exercise to understand why % can be used to "compress" large numbers. N here is a large prime
     number; as it was said on lecture it is used to help reduce collisions.

     @param hash The hash function produced by {@link HashTable#hashFunction(String)}
     */

    private int compressionFunction(int hash) {

        return ((127 * hash + 256) % N) % capacity;
    }

    private static class Entry<K, V> {

        private Entry<K, V> next;

        private K key;

        private V value;

        public Entry(K key, V value) {

            this.key = key;
            this.value = value;
        }
    }

}
