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
            prev = current;
            current = current.next;
        }
        prev.next = newEntry;
        size++;

        if ((1.0 * size) / buckets.length >= 0.7) {
            Entry<K, V>[] temp = buckets;
            buckets = new Entry[temp.length * 2];
            size = 0;
            for (int i = 0; i < temp.length; i++){
                Entry<K, V> entry = temp[i];
                if(entry == null) continue;
                while(entry != null){
                    put(entry.key, entry.value);
                    entry = entry.next;
                }
            }       
        }
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
        int index = getIndex(key) % buckets.length;
        Entry<K, V> current = buckets[index];

        if(current == null){
            return defaultValue;
        }

        while(current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return defaultValue;
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
