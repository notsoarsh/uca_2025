public class Test {
  public static void main(String[] args) {
    FileSort f = new FileSort();
    f.sort(new File("./input.txt"), new File("./output.txt"));
  }
}
