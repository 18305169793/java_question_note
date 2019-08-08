package examples;

import java.nio.charset.Charset;
import java.util.Arrays;

public class StringUsage {
    public static final String STR = "citrix001";

    public static void main(String[] args) throws Exception {
        String s1 = "citrix001";
        String s2 = "citrix" + "001";
        String subS1 = "citrix";
        String subS2 = "001";
        System.out.println("STR==s1: " + String.valueOf(s1 == STR));
        System.out.println("STR==s2: " + String.valueOf(s2 == STR));//编译器优化，""+""会优化，而String+String不会优化
        System.out.println("STR==subS1+subS2: " + String.valueOf((subS1 + subS2) == STR));

        //编码 byte是字节数据类型、有符号型的、占1个字节、大小范围为-128——127
        //
        //char是字符数据类型、无符号型的、占2个字节（unicode码）、大小范围为0-65535
        //实际上编码的过程是char数组映射为byte数组(字符->字节)，而解码的过程就是byte数组映射为char数组(字节->字符)
        String value = "I have 1 doge";
        byte[] bytes = value.getBytes("ASCII");
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes).replace(",", "").trim());
        System.out.println(new String(bytes, "ASCII"));
        byte[] bytes1 = value.getBytes("UTF-8");
        byte[] bytes2 = value.getBytes("UTF-16");
        byte[] bytes3 = value.getBytes("UTF-32");
        System.out.println("bytes1 lenth: "+bytes1.length);
        System.out.println("bytes2 lenth: "+bytes2.length);
        System.out.println("bytes3 lenth: "+bytes3.length);
    }
}
