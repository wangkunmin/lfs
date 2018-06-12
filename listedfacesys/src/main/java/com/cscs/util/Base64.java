package com.cscs.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Base64 encoder and decoder.
 */
public class Base64 {

    /**
     * Base64 standard 6-bit alphabet (RFC 1521).
     */
    private static final char[] BASE64_CHARS = {'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '+', '/', '='};

    /**
     * Another set of alphabet, which is safe for web URL and cookies.
     */
    private static final char[] WEBSAFE_CHARS = {'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '_', '-', '.'};

    public static String encode(String str) {
        try {
            return Base64.encodeWebSafe(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // never happen
        }
        return str;
    }

    public static String decode(String str) {
        String result = null;
        try {
            result = new String(Base64.decodes(str), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // never happen
        }
        if (result == null || result.length() == 0) {
            result = str;
        }

        return result;
    }

    /**
     * Encode bytes to base64 string.
     *
     * @param bytes
     * @return
     */
    private static String encode(byte[] bytes) {
        return encode(bytes, BASE64_CHARS);
    }

    /**
     * Encode bytes to base64 string that is safe on web.
     *
     * @param bytes
     * @return
     */
    private static String encodeWebSafe(byte[] bytes) {
        return encode(bytes, WEBSAFE_CHARS);
    }

    private static String encode(byte[] bytes, char[] chars) {
        StringBuffer buf = new StringBuffer();
        int len = bytes.length;
        int i = 0, inp;
        while (len >= 3) {
            inp = ((int) bytes[i++] + 256) & 0xff;
            inp <<= 8;
            inp += ((int) bytes[i++] + 256) & 0xff;
            inp <<= 8;
            inp += ((int) bytes[i++] + 256) & 0xff;
            len -= 3;
            buf.append(chars[(inp >> 18) & 63]);
            buf.append(chars[(inp >> 12) & 63]);
            buf.append(chars[(inp >> 6) & 63]);
            buf.append(chars[inp & 63]);
        }
        switch (len) {
            case 0: // end of input
                break;
            case 1: // 1 byte input, 2 bytes output, and 2 pads
                inp = ((int) bytes[i++] + 256) & 0xff;
                inp <<= 16;
                buf.append(chars[(inp >> 18) & 63]);
                buf.append(chars[(inp >> 12) & 63]);
                buf.append(chars[64]);
                buf.append(chars[64]);
                break;
            case 2: // 2 bytes input, 3 bytes output, and 1 pad
                inp = ((int) bytes[i++] + 256) & 0xff;
                inp <<= 8;
                inp += ((int) bytes[i] + 256) & 0xff;
                inp <<= 8;
                buf.append(chars[(inp >> 18) & 63]);
                buf.append(chars[(inp >> 12) & 63]);
                buf.append(chars[(inp >> 6) & 63]);
                buf.append(chars[64]);
                break;
        }
        return new String(buf);
    }

    /**
     * Decode bytes from base64 string (both standard and web-safe).
     *
     * @param s
     * @return
     */
    private static byte[] decodes(CharSequence s) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            int[] ds = new int[4];
            int d, di = 0, group = 0, len = s.length();
            char c;

            decode:
            for (int i = 0; i < len; i++) {
                c = s.charAt(i);
                switch (c) {
                    case 'A':
                        d = 0;
                        break;
                    case 'B':
                        d = 1;
                        break;
                    case 'C':
                        d = 2;
                        break;
                    case 'D':
                        d = 3;
                        break;
                    case 'E':
                        d = 4;
                        break;
                    case 'F':
                        d = 5;
                        break;
                    case 'G':
                        d = 6;
                        break;
                    case 'H':
                        d = 7;
                        break;
                    case 'I':
                        d = 8;
                        break;
                    case 'J':
                        d = 9;
                        break;
                    case 'K':
                        d = 10;
                        break;
                    case 'L':
                        d = 11;
                        break;
                    case 'M':
                        d = 12;
                        break;
                    case 'N':
                        d = 13;
                        break;
                    case 'O':
                        d = 14;
                        break;
                    case 'P':
                        d = 15;
                        break;
                    case 'Q':
                        d = 16;
                        break;
                    case 'R':
                        d = 17;
                        break;
                    case 'S':
                        d = 18;
                        break;
                    case 'T':
                        d = 19;
                        break;
                    case 'U':
                        d = 20;
                        break;
                    case 'V':
                        d = 21;
                        break;
                    case 'W':
                        d = 22;
                        break;
                    case 'X':
                        d = 23;
                        break;
                    case 'Y':
                        d = 24;
                        break;
                    case 'Z':
                        d = 25;
                        break;
                    case 'a':
                        d = 26;
                        break;
                    case 'b':
                        d = 27;
                        break;
                    case 'c':
                        d = 28;
                        break;
                    case 'd':
                        d = 29;
                        break;
                    case 'e':
                        d = 30;
                        break;
                    case 'f':
                        d = 31;
                        break;
                    case 'g':
                        d = 32;
                        break;
                    case 'h':
                        d = 33;
                        break;
                    case 'i':
                        d = 34;
                        break;
                    case 'j':
                        d = 35;
                        break;
                    case 'k':
                        d = 36;
                        break;
                    case 'l':
                        d = 37;
                        break;
                    case 'm':
                        d = 38;
                        break;
                    case 'n':
                        d = 39;
                        break;
                    case 'o':
                        d = 40;
                        break;
                    case 'p':
                        d = 41;
                        break;
                    case 'q':
                        d = 42;
                        break;
                    case 'r':
                        d = 43;
                        break;
                    case 's':
                        d = 44;
                        break;
                    case 't':
                        d = 45;
                        break;
                    case 'u':
                        d = 46;
                        break;
                    case 'v':
                        d = 47;
                        break;
                    case 'w':
                        d = 48;
                        break;
                    case 'x':
                        d = 49;
                        break;
                    case 'y':
                        d = 50;
                        break;
                    case 'z':
                        d = 51;
                        break;
                    case '0':
                        d = 52;
                        break;
                    case '1':
                        d = 53;
                        break;
                    case '2':
                        d = 54;
                        break;
                    case '3':
                        d = 55;
                        break;
                    case '4':
                        d = 56;
                        break;
                    case '5':
                        d = 57;
                        break;
                    case '6':
                        d = 58;
                        break;
                    case '7':
                        d = 59;
                        break;
                    case '8':
                        d = 60;
                        break;
                    case '9':
                        d = 61;
                        break;
                    case '+':
                        d = 62;
                        break;
                    case '/':
                        d = 63;
                        break;
                    case '_':
                        d = 62;
                        break;
                    case '-':
                        d = 63;
                        break;
                    default:
                        // Any character not in Base64 alphabet is treated
                        // as end of data. This includes the '=' (pad) char.
                        break decode; // Skip illegal characters.
                }
                ds[di++] = d;
                if (di >= 4) {
                    di = 0;
                    group = ((ds[0] & 63) << 18) + ((ds[1] & 63) << 12)
                            + ((ds[2] & 63) << 6) + (ds[3] & 63);
                    out.writeByte(((group >> 16) & 255));
                    out.writeByte(((group >> 8) & 255));
                    out.writeByte(group & 255);
                }
            }
            // Handle the case of remaining characters and
            // pad handling. If input is not a multiple of 4
            // in length, then '=' pads are assumed.
            switch (di) {
                case 2:
                    // One output byte from two input bytes.
                    group = ((ds[0] & 63) << 18) + ((ds[1] & 63) << 12);
                    out.writeByte(((group >> 16) & 255));
                    break;
                case 3:
                    // Two output bytes from three input bytes.
                    group = ((ds[0] & 63) << 18) + ((ds[1] & 63) << 12)
                            + ((ds[2] & 63) << 6);
                    out.writeByte(((group >> 16) & 255));
                    out.writeByte(((group >> 8) & 255));
                    break;
                default:
                    // Any other case, including correct 0, is treated as
                    // end of data.
                    break;
            }

            out.flush();
            bytes.close();
            return bytes.toByteArray();

        } catch (IOException e) {
            // Won't happen. Return null if it does.
        }
        return null;
    }
//
//	public static void main(String[] args){
//		// 编码
//		String text = "万科企业股份有限公司";
//		String encoded = Base64.encode(text);
//		System.out.println(encoded);
//		System.out.println(Base64.decode(encoded));
//		System.out.println(Base64.decode(text));
//	}
}
