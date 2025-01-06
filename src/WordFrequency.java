import java.util.LinkedList;

//Class to represent a key-value pair node with Generics
class MyMapNode<K,V>{
    K key;
    V value;
    MyMapNode<K,V> next; //pointer to next node in Linked List

    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        return "MyMapNode{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

//Class to represent the Hash Table using Linked List and generics
class MyHashTable<K,V>{
    private LinkedList<MyMapNode<K,V>>bucketArray;
    private int numBuckets;

    public MyHashTable(int size) {
        this.numBuckets = size;
        bucketArray= new LinkedList<>();
        for(int i=0;i<size;i++){
            bucketArray.add(null);
        }
    }

    //Hash function to determine bucket index
    private int getBucketIndex(K key){
        int hashCode= key.hashCode();
        return Math.abs(hashCode%numBuckets);
    }

    //Add or update key-value pair
    public void put(K key, V value){
        int index=getBucketIndex(key);
        MyMapNode<K,V>head= bucketArray.get(index);

        //check if key already exists
        while(head!=null){
            if(head.key.equals(key)){
                head.value=value;
                return;
            }
            head=head.next;
        }

        //Insert new node at the beginning
        head = bucketArray.get(index);
        MyMapNode<K,V> newNode= new MyMapNode<>(key, value);
        newNode.next=head;
        bucketArray.set(index,newNode);
    }

    //get value by key
    public V get(K key){
        int index= getBucketIndex(key);
        MyMapNode<K,V>head = bucketArray.get(index);

        //search for key in the chain
        while(head!=null){
            if(head.key.equals(key)){
                return head.value;
            }
            head=head.next;
        }

        return null;
    }

    // Remove a key-value pair by key
    public void remove(K key) {
        int index = getBucketIndex(key);
        MyMapNode<K, V> head = bucketArray.get(index);
        MyMapNode<K, V> prev = null;

        // Search for the key in the chain
        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    bucketArray.set(index, head.next);
                } else {
                    prev.next = head.next;
                }
                return;
            }
            prev = head;
            head = head.next;
        }
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (MyMapNode<K, V> head : bucketArray) {
            while (head != null) {
                result.append(head.toString()).append(" ");
                head = head.next;
            }
        }
        return result.toString();
    }
}



public class WordFrequency {
    public static void main(String[] args){
        String paragraph = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";
        String[] words = paragraph.toLowerCase().split(" ");

        MyHashTable<String, Integer> wordFrequency = new MyHashTable<>(10);

        // Count the frequency of each word
        for (String word : words) {
            Integer count = wordFrequency.get(word);
            if (count == null) {
                wordFrequency.put(word, 1);
            } else {
                wordFrequency.put(word, count + 1);
            }
        }

        // Remove the word "avoidable"
        wordFrequency.remove("avoidable");

        // Print the word frequencies
        System.out.println("Word Frequencies: " + wordFrequency);

    }
}
