package test;
import driver.IPv4Iterator;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.List;

public class IPv4IteratorTest
{
    @Test
    public void MAIN() throws UnknownHostException {
        String ip = "192.168.98.55";
        String ip2 = "192.168.99.1";
        List<String> list = IPv4Iterator.iterateIPv4Addresses(ip , ip2);
        for (String s : list) System.out.println(s);
    }
}
