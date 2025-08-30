class Cat {
  int size;
  int color;
  
  Cat(int size, int color) {
    this.size = size;
    this.size = color;
  }

  public static void main(String[] args) {
    Cat c1 = new Cat(6,8);
    Cat c2 = new Cat(6,8);
    System.out.println(c1 == c2);
    System.out.println(c1.equals(c2));
  }
}
