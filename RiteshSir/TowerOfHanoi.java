import java.util.*;
import java.io.*;
import java.math.*;
class toh {
  static void toh(int n, char source,char aux, char dest) {
    if (n == 0) {
      return;
    }
    toh(n - 1, source, dest, aux);
    System.out.println("Move disk " + n + " from rod " +source + " to rod " + dest);
    toh(n - 1, aux, source, dest);
  }

  public static void main(String args[]) {
    int n = 4; // Number of disks
    toh(n, 'A', 'B', 'C'); // A, B and C are names of rods
  }
} 