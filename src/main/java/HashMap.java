public class HashMap<K, V> {
    private Entry<K, V>[] buckets; // declare an array of entry;
    private int size = 0; //initial size is 0

    public HashMap(){
        buckets = new Entry[2]; // assign initial size of 2
    }

    private int getIndex(K key){
        return key.hashCode();
    }

    private void resize(){
        Entry<K, V>[] previousHashMap = buckets;
        buckets = new Entry[size * 2]; //double the size of array
        size = 0; //reset the size because put() method will increase the size

        for(int i = 0; i < previousHashMap.length; i++){ //loop through existing hash
            Entry<K, V> current = previousHashMap[i];
            if(current == null) continue;
            while(current != null){
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    public void put(K key, V value){
        if(size >= buckets.length){
            resize(); //if the size is larger than or equal to length of hashmap array, we will need to increase the size of the array
        }
        Entry<K, V> newEntry = new Entry<>(key, value, null);
        int index = getIndex(key) % buckets.length; //get the hash

        if(buckets[index] == null){
            buckets[index] = newEntry;
            size++;
            return;
        }
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = buckets[index];
        while(current != null){
            if(current.key.equals(key)){
                current.value = value;
                return;
            }
            current = current.next;
        }
        prev.next = newEntry;
        size++;
    }

    public V get(K key){
        int index = getIndex(key) % buckets.length;
        Entry<K, V> current = buckets[index];

        if(current == null){
            return null;
        }

        while(current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V getOrDefault(K key, V defaultValue){
        V value = get(key);
        if(value == null) return defaultValue;
        return value;
    }

    public Entry<K, V>[] entrySet(){
        Entry<K, V>[] set = new Entry[size];
        int i = 0;
        for(Entry<K, V> entry : buckets){
            while(entry != null){
                set[i++] = entry;
                entry = entry.next;
            }
        }
        return set;
    }

    public int size(){
        return size;
    }
}
