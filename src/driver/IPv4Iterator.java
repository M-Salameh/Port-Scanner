package driver;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class IPv4Iterator
{
    public static List<String> iterateIPv4Addresses(String startIP, String endIP) throws UnknownHostException
    {
        InetAddress start = InetAddress.getByName(startIP);
        InetAddress end = InetAddress.getByName(endIP);
        byte[] startAddress = start.getAddress();
        byte[] endAddress = end.getAddress();

        long startValue = bytesToLong(startAddress);
        long endValue = bytesToLong(endAddress);

        List<String> ipAddresses = new ArrayList<>();
        for (long i = startValue; i <= endValue; i++) {
            InetAddress address;
            try {
                address = InetAddress.getByAddress(longToBytes(i));
                ipAddresses.add(address.getHostAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ipAddresses;
    }

    public static long bytesToLong(byte[] bytes) {
        long result = 0;
        for (byte b : bytes) {
            result = result << 8 | (b & 0xFF);
        }
        return result;
    }

    public static byte[] longToBytes(long value) {
        byte[] bytes = new byte[4];
        for (int i = 3; i >= 0; i--) {
            bytes[i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return bytes;
    }
}