#include <stdio.h>
#include <string.h>

static const char base64_table[] =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

void base64_encode(const unsigned char *input, int len, char *output) {
    int i, j;
    for (i = 0, j = 0; i < len;) {
        // Take three bytes (24 bits)
        unsigned int octet_a = i < len ? input[i++] : 0;
        unsigned int octet_b = i < len ? input[i++] : 0;
        unsigned int octet_c = i < len ? input[i++] : 0;

        // Merge into 24-bit
        unsigned int triple = (octet_a << 16) | (octet_b << 8) | octet_c;

        // Extract 4 groups of 6 bits
        output[j++] = base64_table[(triple >> 18) & 0x3F];
        output[j++] = base64_table[(triple >> 12) & 0x3F];
        output[j++] = (i > len + 1) ? '=' : base64_table[(triple >> 6) & 0x3F];
        output[j++] = (i > len) ? '=' : base64_table[triple & 0x3F];
    }

    output[j] = '\0'; // Null terminate the output string
}

int base64_value(char c) {
  if (c >= 'A' && c <= 'Z') return c - 'A';
  if (c >= 'a' && c <= 'z') return c - 'a' + 26;
  if (c >= '0' && c <= '9') return c - '0' + 52;
  if (c == '+') return 62;
  if (c == '/') return 63;
  return -1;
}

void base64_decode(const char *input, int len, unsigned char *output) {
  int i, j;
  for (i = 0, j = 0; i < len;) {
    // Here we read 4 chars at a time and convert them into 6 bit values
    int sextet_a = (input[i] == '=') ? (0 & i++) : base64_value(input[i++]);
    int sextet_b = (input[i] == '=') ? (0 & i++) : base64_value(input[i++]);
    int sextet_c = (input[i] == '=') ? (0 & i++) : base64_value(input[i++]);
    int sextet_d = (input[i] == '=') ? (0 & i++) : base64_value(input[i++]);
    // Merging the sextets into 24 bits
    unsigned int triple = (sextet_a << 18) | (sextet_b << 12) | (sextet_c << 6) | sextet_d; 
    //Extract the byted
    if (input[i - 2] != '=') output[j++] = (triple >> 16) & 0xFF;
    if (input[i - 2] != '=') output[j++] = (triple >> 8) & 0xFF;
    if (input[i - 2] != '=') output[j++] = (triple) & 0xFF;
  }
  output[j] = '\0';
}

int main() {
    const char *text = "Man";
    char encoded[50];
    char decoded[50];
    base64_encode((const unsigned char *)text, strlen(text), encoded);
    printf("Input: %s\n", text);
    printf("Base64 Encoded: %s\n", encoded);
    base64_decode((const unsigned char *)encoded, strlen(encoded), decoded);
    printf("Decoded: %s\n", decoded);

    return 0;
}
