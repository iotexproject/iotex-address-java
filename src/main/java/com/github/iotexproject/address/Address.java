package com.github.iotexproject.address;

/**
 * Address.
 *
 * @author Yang XuePing
 */
public class Address {
    public static final String AddressPrefix = "io";
    public static String to0x(String address) {
        if (!address.startsWith("io")) {
            throw new RuntimeException("Error address format");
        }
        byte[] dec = Bech32.decode(address).data;
        return "0x" + Numeric.toHexString(Bech32.convertBits(dec, 0, dec.length, 5, 8, false));
    }

    public static String toIo(String address) {
        if (!address.startsWith("0x")) {
            throw new RuntimeException("Error address format");
        }
        byte[] values = Numeric.hexStringToByteArray(address.substring(2));
        byte[] grouped = Bech32.convertBits(values, 0, values.length, 8, 5, true);
        return Bech32.encode(AddressPrefix, grouped);
    }
}
