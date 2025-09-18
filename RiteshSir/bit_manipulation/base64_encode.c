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

int main() {
    const char *text = "Man";
    char encoded[50];

    base64_encode((const unsigned char *)text, strlen(text), encoded);
    printf("Input: %s\n", text);
    printf("Base64 Encoded: %s\n", encoded);

    return 0;
}
