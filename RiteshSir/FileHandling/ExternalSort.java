import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

import javax.management.RuntimeErrorException;

public class ExternalSort {
  public void sort(File input, File output) {
    long fileSize = input.length();
    long freeRam = Runtime.getRuntime().freeMemory() / 2;
    
    //size of the buffer
    int blockSize = (int)fileSize;
    if (freeRam < fileSize) {
      int fileCount = (int)(fileSize / freeRam) + 1;
      if (fileCount > 1024) throw new RuntimeErrorException("Please increase RAM"); 
      blockSize = (int)freeRam;
    }

    List<File> tempFiles = createSortedTempFiles(input, blockSize);
    PriorityQueue<CustomBufferReader> pq = new PriorityQueue<>();
    for(File tempFile : tempFiles) {
      pq.add(new CustomBufferReader(tempFile));
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
      while (!pq.isEmpty()) {
        CustomBufferReader customBufferReader = pq.poll();
        bw.write(customBufferReader.pop());
        bw.newLine();
        if (customBufferReader.peek() != null) {
          pq.add(customBufferReader);
        }
      }
    }
  }

  private List<File> createSortedTempFiles(File input, int maxBlockSize) throws IOException {
    List<Strin> data = new ArrayList<>();
    int currentBlockSize = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(input))) {
      String s = br.readLine();
      while (s!=null) {
        data.add(s);
        currentBlockSize += s.length();
        if (currentBlockSize > maxBlockSize) {
          File f = new File(UUID.randomUUID()+".txt");
          try (BufferedReader bw = new BufferedReader(new FileReader(f))) {
            for (String d : data) {
              bw.write(d);
              bw.newLine();
            }
          }
          data.clear();
          currentBlockSize = 0;
          tempFiles.add(f);
        }
      }
      return null;
    }
  }
}
