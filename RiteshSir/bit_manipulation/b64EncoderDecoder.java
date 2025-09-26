import java.util.Arrays;

public class b64EncoderDecoder {
  public final static char[] b64_lookup = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
      .toCharArray();
  private static final int[] b64_reverse_lookup = new int[128];

  public static String encode64(String input) {
    StringBuilder res = new StringBuilder();
    int i = 0;

    int max_i = input.length() - (input.length() % 3);

    // Process only the full chunks
    for (; i < max_i; i += 3) {
      long triple = (input.charAt(i) << 16) | (input.charAt(i + 1) << 8) | input.charAt(i + 2);
      res.append(b64_lookup[(int) ((triple >> 18) & 0x3F)]);
      res.append(b64_lookup[(int) ((triple >> 12) & 0x3F)]);
      res.append(b64_lookup[(int) ((triple >> 6) & 0x3F)]);
      res.append(b64_lookup[(int) (triple & 0x3F)]);
    }

    if (input.length() % 3 == 1) {
      long triple = (input.charAt(i) << 16);
      res.append(b64_lookup[(int) ((triple >> 18) & 0x3F)]);
      res.append(b64_lookup[(int) ((triple >> 12) & 0x3F)]);
      res.append('=').append('=');
    } else if (input.length() % 3 == 2) {
      long triple = (input.charAt(i) << 16) | (input.charAt(i + 1) << 8);
      res.append(b64_lookup[(int) ((triple >> 18) & 0x3F)]);
      res.append(b64_lookup[(int) ((triple >> 12) & 0x3F)]);
      res.append(b64_lookup[(int) ((triple >> 6) & 0x3F)]);
      res.append('=');
    }

    return res.toString();
  }

  static {
    Arrays.fill(b64_reverse_lookup, -1);
    // mapping each b64 char to its 6bit value
    for (int i = 0; i < b64_lookup.length; i++) {
      b64_reverse_lookup[b64_lookup[i]] = i;
    }
  }

  public static String decode(String input) {
    if (input.length() % 4 != 0) {
      throw new IllegalArgumentException("Input string must have a length of multiple of 4!");
    }

    StringBuilder res = new StringBuilder();

    for (int i = 0; i < input.length(); i+= 4) {
      int val1 = b64_reverse_lookup[input.charAt(i)];
      int val2 = b64_reverse_lookup[input.charAt(i + 1)];

      // handling padding
      int val3 = (input.charAt(i + 2) == '=') ? 0 : b64_reverse_lookup[input.charAt(i + 2)];
      int val4 = (input.charAt(i + 3) == '=') ? 0 : b64_reverse_lookup[input.charAt(i + 3)];

      int triple = (val1 << 18) | (val2 << 12) | (val3 << 6) | val4;
      res.append((char) ((triple >> 16) & 0xFF));
      // If the third char was not padding, extract the second byte.
      if (input.charAt(i + 2) != '=') {
        res.append((char) ((triple >> 8) & 0xFF));
      }
      // If the fourth char was not padding, extract the third byte.
      if (input.charAt(i + 3) != '=') {
        res.append((char) (triple & 0xFF));
      }
    }
    return res.toString();
  }

  public static void main(String[] args) {
    System.out.println(encode64("Ma")); 
    System.out.println(decode("TWFu"));


  }
}
