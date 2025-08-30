class BlackCat {
  int size;
  int color;
  
  BlackCat(int size, int color) {
    this.size = size;
    this.size = color;
  }

  public static void main(String[] args) {
    BLackCat c1 = new BlackCat(6,8);
    BlackCat c2 = new BlackCat(6,8);
    System.out.println(c1 == c2);
    System.out.println(c1.equals(c2));
  }

  public boolean equals(Object o) {
    BlackCat c = (BlackCat)o;
    if (c.size == this.size && c.color == this.color) {
      return true;
    }
    return false;
  }
 
}
