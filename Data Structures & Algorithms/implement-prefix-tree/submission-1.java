class PrefixTree {
    public TrieNode root;

    public PrefixTree() {
        root = new TrieNode();    
    }

    public void insert(String word) {
        char[] string = word.toCharArray();
        TrieNode current = root;
        //loop through char, adding each char if non-existent
        for(char c : string){
            //insert char if not along path
            if(!current.children.containsKey(c)){
                current.children.put(c, new TrieNode());
            }
            current = current.children.get(c);
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        char[] string = word.toCharArray();
        TrieNode current = root;
        for(char c : string){
            if(!current.children.containsKey(c)){return false;}
            else{
                current = current.children.get(c);
            }
        }
        //search only returns true if the param was a full word
        if(current.isEnd){return true;}
        else{return false;}
    }

    public boolean startsWith(String prefix) {
        char[] string = prefix.toCharArray();
        TrieNode current = root;
        for(char c : string){
            if(!current.children.containsKey(c)){return false;}
            else{
                current = current.children.get(c);
            }
        } 
        return true;
    }
}

public class TrieNode{
    public Map<Character, TrieNode> children = new HashMap<>();
    public boolean isEnd; //needed to differentiate search() and 
        //startsWith() methods
}