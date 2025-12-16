import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CustomBufferReader {
  private String data;
  private BufferedReader br;
  boolean hasData = true;

  public CustomBufferReader(File f) throws FileNotFoundException {
    br = new BufferedReader(new FileReader(f));
    reload();
  }

  private void reload() throws IOException {
    data = br.readLine();
    if (data == null) {
      br.close();
      hasData = false;
    } else {
      hasData = true;
    }
  }

  public String peek() {
    return data;
  }

  public String pop() {
    if (hasData) {String t = data;
    reload();
    return t;
    }
    return null;
  }
  @Override
  public int compareTo(CustomBufferReader o) {
    return this.data.compareTo(o.data);
  }
}
