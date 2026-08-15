class WordDictionary {
    //GRADING: Time is O(26^L) and Space is O(L), where L is target word's length
        //and worst time case is reached if all wildcards --> both optimal
    public TrieNode root;

    public WordDictionary() {root = new TrieNode();}

    public void addWord(String word) {
        char[] string = word.toCharArray();
        TrieNode current = root;
        for(char c : string){
            if(!current.children.containsKey(c)){
                current.children.put(c, new TrieNode());
            }
            current = current.children.get(c);
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        //major optimization: send the helper char[] instead of String word
            //so it doesn't have to do it at every call, saving O(L) time
        char[] target = word.toCharArray();
        return find(target, root, 0);
    }
}

//recursive helper for search()
private boolean find(char[] word, TrieNode parent, int start){
    TrieNode current = parent;
    //loop through remaining word
    for(int i = start; i < word.length; i++){
        char c = word[i];
        if(current == null){return false;}
        if(c != '.' && !current.children.containsKey(c)){return false;}
        if(c == '.'){
            //wildcard char protocol is to continue down all children paths
            List<Character> chars = new ArrayList<>(current.children.keySet());
            //error checking to ensure children present down wildcard
            if(chars.size() > 0){
                //MAJOR FIX: store parent TrieNode upon hitting WC
                TrieNode p = current;
                for(int j = 0; j < chars.size(); j++){
                    if(current == null){return false;}
                    //PREV: CURRENT = CURRENT.CHILDREN.GET(())'
                        //THIS DID NOT SEARCH ALL THE CHILDREN RECURSIVELY FROM THE WC AS PARENT
                    current = p.children.get(chars.get(j));
                    //if any recursive find() a legal path, true
                        //MAJOR FIX: the start for the recursive calls is i+1 not start+2
                        //since any # of chars may have been advanced before hitting the WC
                    if(find(word, current, i+1)){return true;}
                }
                //no legal path found after recursing down all children
                return false;
            }
        }
        current = current.children.get(c);
    }
    //MISTAKE: cannot simply return true after able to follow all chars inside target --> USE isEnd boolean!
    //return true;
    if(current != null && current.isEnd){return true;}
    else{return false;}
}

public class TrieNode{
    public Map<Character, TrieNode> children = new HashMap<>();
    public boolean isEnd;
}