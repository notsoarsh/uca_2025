import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class FileSort {
  public void sort(File input, File output) throws IOException {
    ArrayList<String> data = new ArrayList<>();
    // Buffered Reader allows faster reading as it buffers, uses hard disk only but
    // uses faster part of it
    //try with resources automatically closes buffer
    try(BufferedReader br = new BufferedReader(new FileReader(input));) {
      String s = br.readLine();
      while (s != null) {
        data.add(s);
        s = br.readLine();
      }
    } 
    Collections.sort(data);

    BufferedWriter bw = new BufferedWriter(new FileWriter(output));
    for (String d : data) {
      bw.write(d);
      bw.newLine();
    }
    // bw.flush();
    bw.close(); // automatically flushes
  }
}