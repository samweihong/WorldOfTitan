public class HashMap<K, V> {
    private Entry<K, V> head;
    private int size = 0;

    public void put(K key, V value){
        Entry<K, V> newNode = new Entry<>(key, value, null); //initial a new node
        if(head == null){
            head = newNode; //if head is null, assign newNode to head
            size++; //increase size
        }
        else{
            Entry<K, V> current = head; 
            Entry<K, V> prev = head;
            while(current != null){
                if(current.key.equals(key)){ //if the key already exist, just change the value instead of creating a new node
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newNode; //if the key is not found, insert the node as the last node
            size++; //increase the size
        }
    }

    public V get(K key){
        Entry<K, V> current = head; 
        while(current != null){
            if(current.key.equals(key)){ //if the key is found, return it's respective value
                return current.value;
            }
            current = current.next;
        }
        return null; //if the key is not found, return null
    }

    public V getOrDefault(K key, V defaultValue){
        V current = get(key);
        if(current == null){
            return defaultValue;
        }
        return current;
    }
    
    public boolean containsKey(K key){
        Entry<K, V> current = head;
        while(current != null){
            if(current.key.equals(key)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Entry<K, V>[] entrySet(){
        Entry<K, V>[] set = new Entry[this.size];
        Entry<K, V> current = head;
        int i = 0;
        while(current != null){
            set[i++] = current;
            current = current.next;
        }
        return set;
    }

    public int size(){
        return this.size;
    }
    
    
    
    //getKey
}
