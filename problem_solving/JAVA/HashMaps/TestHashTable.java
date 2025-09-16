class Foo {
  Integer i;
  Boolean b;
  String s;

  Foo(Integer i, Boolean b, String s) {
    this.i = i;
    this.b = b;
    this.s = s;
  }

  @Override
  public int hashCode() {
    int hash = 17;
    
    hash = i.hashCode() + 31 * hash;
    hash = b.hashCode() + 31 * hash;
    hash = s.hashCode() + 31 * hash;
    return hash;
  }

  @Override
  public boolean equals(Object o) {
    Foo f = (Foo)o;
    
    if (f.i.equals(i) && f.b.equals(b) && f.s.equals(s)) {
      return true;
    }
    return false;
  }
}

public class TestHashTable {
  public stativ void main(String[] args) {
    HashTable<Foo, Integer> map = new HashTable<Foo, Integer>();
    Foo foo1 = new Foo(5, false, "hello");
    Foo foo2 = new Foo(7, true, "hello1");
    Foo foo3 = new Foo(1, false, "hello67");

    map.put(foo1, 7);
    map.put(foo2, 6);
    map.put(foo3, 8);
 
    assert map.get(foo2) == 6;
    assert map.get(foo1) == 7;
    assert map.get(foo3) == 8;
  }
}
