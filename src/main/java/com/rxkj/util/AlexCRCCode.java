package com.rxkj.util;

public class AlexCRCCode {

    /**
     * 计算CRC16校验码
     * @param bytes
     * 字节数组
     * @return {@link String} 校验码
     * @since 1.0
     */
    public static String getCRC(byte[] bytes) {
        // CRC寄存器全为1
        int CRC = 0x0000ffff;
        // 多项式校验值
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        // 结果转换为16进制
        String result = Integer.toHexString(CRC).toUpperCase();
        if (result.length() != 4) {
            StringBuffer sb = new StringBuffer("0000");
            result = sb.replace(4 - result.length(), 4, result).toString();
        }
        //高位在前地位在后
        //return result.substring(2, 4) + " " + result.substring(0, 2);
        // 交换高低位，低位在前高位在后
        return result.substring(2, 4) + " " + result.substring(0, 2);
    }

    //crc调用出口
    public static String getCRC(String data) {
        data = data.replace(" ", "");
        int len = data.length();
        if (!(len % 2 == 0)) {
            return "0000";
        }
        int num = len / 2;
        byte[] para = new byte[num];
        for (int i = 0; i < num; i++) {
            int value = Integer.valueOf(data.substring(i * 2, 2 * (i + 1)), 16);
            para[i] = (byte) value;
        }
        return getCRC(para);
    }

    public static void main(String[] args) {
        String crcon = getCRC("030600150001");
        String crcoff = getCRC("030600150000");
        //String crdi1 = getCRC("630200100002");
        //String crcdo1 = getCRC("630100140002");
        System.out.println("开: " + "030600150001"+crcon.replaceAll(" ",""));
        System.out.println("关: " + "030600150000"+crcoff.replaceAll(" ",""));
        //System.out.println("crdi1: " + "630200100002"+crdi1.replaceAll(" ",""));
        //System.out.println("crcdo1: " + "630100140002"+crcdo1.replaceAll(" ",""));
    }

}

