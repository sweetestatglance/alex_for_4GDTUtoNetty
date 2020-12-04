package com.rxkj.util;

/**
 * 字节转换
 * @author alex
 * @date 2020年11月16日
 * @Description
 *
 */
public class CharacterConvert {

    /**
     * byte转换成int
     * @param number
     * @return
     */
    public static int byteToInt(byte number){
        return number & 0xff;
    }

    /**
     * short转成int
     * @param number
     * @return
     */
    public static int shortToInt(short number){
        return number & 0xff;
    }

    /**
     * int型数据，低八位获取
     * @param data
     * @return
     */
    public static int getLow8(int number){
        //转换成二进制的字符串形式
        //Integer.toBinaryString(number & 0xff)
        return number & 0xff;
    }

    /**
     * int型数据，取低四位
     * @param number
     * @return
     */
    public static int getLow4(int number){
        return number & 0x0f;
    }

    /**
     * bytes2HexString
     * 字节数组转16进制字符串
     * @param b 字节数组
     * @return 16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        String hex;
        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     * string2HexString
     * 字符串转16进制字符串
     * @param strPart  字符串
     * @return 16进制字符串
     */
    public static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    /**
     * hexString2Bytes
     * 16进制字符串转字节数组
     * @param src  16进制字符串
     * @return 字节数组
     */
    public static byte[] hexString2Bytes(String src) {
        int len = src.length() / 2;
        byte[] ret = new byte[len];
        for (int i = 0; i < len; i++) {
            ret[i] = (byte) Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }


    /**
     * hexString2String
     * 16进制字符串转字符串
     * @param src
     * 16进制字符串
     * @return 字节数组
     * @throws
     */
    public static String hexString2String(String src) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < src.length() / 2; i++) {
            str.append((char)Integer.valueOf(src.substring(i * 2, i * 2 + 2),16).byteValue());
        }
        return str.toString();
    }

    /**
     * char2Byte
     * 字符转成字节数据char-->integer-->byte
     * @param src
     * @return
     * @throws
     */
    public static Byte char2Byte(Character src) {
        return Integer.valueOf((int)src).byteValue();
    }

    /**
     * intToHexString
     * 10进制数字转成16进制
     * @param a 转化数据
     * @param len 占用字节数
     * @return
     * @throws
     */
    public static String intToHexString(int a,int len){
        len<<=1;
        String hexString = Integer.toHexString(a);
        int b = len -hexString.length();
        if(b>0){
            for(int i=0;i<b;i++)  {
                hexString = "0" + hexString;
            }
        }
        return hexString;
    }

    /**
     * 将16进制的2个字符串进行异或运算
     * http://blog.csdn.net/acrambler/article/details/45743157
     * @param strHex_X
     * @param strHex_Y
     * 注意：此方法是针对一个十六进制字符串一字节之间的异或运算，如对十五字节的十六进制字符串异或运算：1312f70f900168d900007df57b4884
    先进行拆分：13 12 f7 0f 90 01 68 d9 00 00 7d f5 7b 48 84
    13 xor 12-->1
    1 xor f7-->f6
    f6 xor 0f-->f9
    ....
    62 xor 84-->e6
    即，得到的一字节校验码为：e6
     * @return
     */
    public static String xor(String strHex_X,String strHex_Y){

        //将x、y转成二进制形式
        String anotherBinary=Integer.toBinaryString(Integer.valueOf(strHex_X,16));

        String thisBinary=Integer.toBinaryString(Integer.valueOf(strHex_Y,16));

        String result = "";

        //判断是否为8位二进制，否则左补零
        if(anotherBinary.length() != 8){
            for (int i = anotherBinary.length(); i <8; i++) {
                anotherBinary = "0"+anotherBinary;

            }
        }

        if(thisBinary.length() != 8){
            for (int i = thisBinary.length(); i <8; i++) {
                thisBinary = "0"+thisBinary;
            }
        }

        //异或运算
        for(int i=0;i<anotherBinary.length();i++){
            //如果相同位置数相同，则补0，否则补1
            if(thisBinary.charAt(i)==anotherBinary.charAt(i)){
                result+="0";
            }else{
                result+="1";
            }
        }
        return Integer.toHexString(Integer.parseInt(result, 2));
    }

    /**
     * byte转换成int型字符串
     * Convert byte[] to hex string
     * @param src byte[] data
     * @return hex string
     */
    public static String bytes2Str(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * @param msg
     * @return 接收字节数据并转为16进制字符串
     */
    public static String receiveHexToString(byte[] by) {
        try {
            String str = bytes2Str(by);
            str = str.toLowerCase();
            return str;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * "7dd",4,'0'==>"07dd"
     * @param input 需要补位的字符串
     * @param size 补位后的最终长度
     * @param symbol 按symol补充 如'0'
     * @return
     * N_TimeCheck中用到了
     */
    public static String fill(String input, int size, char symbol) {
        while (input.length() < size) {
            input = symbol + input;
        }
        return input;
    }

    /**
     * integer数据求和
     * @param integers
     * @return
     */
    public static int sum(Integer... integers){
        Integer sum = 0;
        for(int i=0;i<integers.length;i++){
            sum = Integer.sum(sum, integers[i]);
        }
        return sum;
    }
}
