package com.example.leetcode;

import com.example.leetcode.utils.Reader4;

public class p158_ReadNGivenRead4II extends Reader4 {

  // Use local cache the persist result of previous read4 calls
  char[] buffer = new char[4];

  int ptr = 0;
  int count = 0;

  /**
   * @param dest Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
  public int read(char[] dest, int n) {
    int destPtr = 0;

    while(destPtr < n) {
      if (ptr  == 0) {
        // If this is the first call or all characters of the local
        // buffer have been consumed previously, then fetch the next
        // block of data
        count = read4(buffer);
      }
      if (count == 0) {
        // Reader source is empty, no more data
        break;
      }
      while (destPtr < n && ptr < count) {
        // Copy data from cache to destination array
        dest[destPtr++] = buffer[ptr++];
      }
      if (ptr == count) {
        // All characters copied from cache to the destination,
        // reset pointer and wait next iteration to fetch more
        // data from the API if necessary.
        ptr = 0;
      }
    }
    // return the total amount of characters read from the API,
    // this can be equal or less than N
    return destPtr;
  }
}
