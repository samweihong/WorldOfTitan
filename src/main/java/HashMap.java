public class HashMap<K, V> {
    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
    
        public Node(K key, V value, Node<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V> head;
    //private int size = 0;

    public void put(K key, V value){
        Node<K, V> newNode = new Node<>(key, value, null); //initial a new node
        if(head == null){
            head = newNode; //if head is null, assign newNode to head
        }
        else{
            Node<K, V> current = head; 
            Node<K, V> prev = head;
            while(current != null){
                if(current.key.equals(key)){ //if the key already exist, just change the value instead of creating a new node
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newNode; //if the key is not found, insert the node as the last node
        }
    }

    public V get(K key){
        Node<K, V> current = head; 
        while(current != null){
            if(current.key.equals(key)){ //if the key is found, return it's respective value
                return current.value;
            }
            current = current.next;
        }
        return null; //if the key is not found, return null
    }

    /*public boolean containsKey(K key){
        Node<K, V> current = head;
        while(current != null){
            if(current.key.equals(key)){
                return true;
            }
            current = current.next;
        }
        return false;
    }*/
}
