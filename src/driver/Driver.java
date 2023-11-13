package driver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import scanner.PortScanner;
import scanner.PortScannerResult;
import scanner.PortStatus;

/**
 * USAGE: JavaPortScanner [target IP] [IP Last Target] [port range]
 * <ul>
 * <li>[target IP]: IP address start to scan</li>
 * <li>[port range]: range of ports to scan</li>
 * <ul>
 * <li>e.g. 80-100 scans from 80 to 100</li>
 * <li>e.g. 8080 scans only port 8080</li>
 * </ul>
 * </ul>
 */
public class Driver {

    private static final int SCAN_TIMEOUT = 200;
    private static final int THREAD_COUNT = 128;

    public static void main(String[] args) {
        // get a Target info from Parser
        List<Target> targets = new ArrayList<>();
        String[] Args = new String[3];
        Args[0] = "8.8.8.4";
        Args[1] = "8.8.8.9";
        Args[2] = "19-80";
        try {
            targets = Parser.run(Args);
        } catch (InvalidUserInputException e) {
            // exit with the message
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // run a scan on the target and get a result
        for (Target t : targets)
        {
            SingleIPDriver.start(t);
        }
    }
}
