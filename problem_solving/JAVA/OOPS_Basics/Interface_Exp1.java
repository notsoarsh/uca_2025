Interface I1 {
  public void foo();
}

Interface I2 {
  public void bar();
}

class B implements I1, I2 {
  @Override 
  public void foo() {
    System.out.println("Hello");
  }

  @Override 
  public void bar() {
    System.out.println("Bye");
  }

  public static void main(String[] args) {
    B b = new B();
    b.foo();
  }
}
