import java.util.*;

public class Trie {
  private final TrieNode root;

  public void insert(String key, String value) {
    TrieNode t = root;
    for (char c : key.toCharArray()) {
      int index = c - 'a';
      if (t.children[index] == null) t.children[index] = new TrieNode();
      t = t.children[index];
    }
    t.value = value;
  }

  private TrieNode find(String key) {
    TrieNode t = root;
    for (char c : key.toCharArray()) {
      int index = c - 'a';
      if (t.children[index] == null) return null;
      t = t.children[index];
    }
    return t;
  }

  public String search(String key) {
    TrieNode t = find(key);
    return t == null ? null : t.value;
  }

  public boolean startsWith(String key) {
    TrieNode t = find(key);
    return t != null;
  }

  public void delete(String key) {
    delete(root, key, 0);
  }  

  private boolean deletable(TrieNode t) {
    return t.value == null && !hasChild(t);
  }

  private boolean hasChild(TrieNode t) {
    for (TrieNode n : t.children) {
      if (n != null) return false;
    }
    return true;
  }

  private boolean delete(TrieNode n, String key, int level) {
    if (n == null) return false;
    if (key.length() == level) {
      n.value = null;
      return deletable(n);
    }  
    int index = key.charAt(level) - 'a';
    boolean canDelete = delete(n.children[index], key, level + 1);
    if (canDelete) {
      n.children[index] = null;
      return deletable(n);
    }
    return false;
  }

  public Trie() {
    this.root = new TrieNode();
  }

  private static class TrieNode {
    private String value;
    private TrieNode[] children;

    public TrieNode() {
      children = new TrieNode[26];
    }
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    System.out.println(trie.startsWith("app"));
    System.out.println(trie.startsWith("apple"));
    System.out.println(trie.search("app"));
    System.out.println(trie.search("apple"));
    System.out.println("**************************");

    trie.insert("apple", "a fruit");
    System.out.println(trie.startsWith("app"));
    System.out.println(trie.startsWith("apple"));
    System.out.println(trie.search("app"));
    System.out.println(trie.search("apple"));
    System.out.println("**************************");

    trie.insert("app", "application");
    System.out.println(trie.startsWith("app"));
    System.out.println(trie.startsWith("apple"));
    System.out.println(trie.search("app"));
    System.out.println(trie.search("apple"));    
  }
}
