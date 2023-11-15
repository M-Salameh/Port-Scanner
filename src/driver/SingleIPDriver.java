package driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import scanner.PortScanner;
import scanner.PortScannerResult;
import scanner.PortStatus;

public class SingleIPDriver
{
    private static final int SCAN_TIMEOUT = 200;
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    public static void start(Target t)
    {
        // run a scan on the target and get a result
        System.out.println("Scanning " + t.targetIP + ":" + t.portRange[0] + "-" + t.portRange[1]);

        // lists to populate
        List<Future<PortStatus>> futureList = new ArrayList<>();
        List<PortStatus> resultList = new ArrayList<>();

        // ExecutorService
        ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);

        // loop through provided port range
        for (int currPort = t.portRange[0]; currPort <= t.portRange[1]; currPort++) {
            futureList.add(PortScanner.isPortOpen(es, t.targetIP, currPort, SCAN_TIMEOUT));
        }

        es.shutdown();
        try {
            if (!es.awaitTermination(2, TimeUnit.MINUTES)) {
                // timeout elapsed before termination
                es.shutdownNow();
            }
        } catch (InterruptedException e) {
            es.shutdownNow();
        }

        // get results to resultList
        for (Future<PortStatus> eachFuture : futureList) {
            try {
                resultList.add(eachFuture.get(2, TimeUnit.MINUTES));
            } catch (Exception e) {
                System.err.println("Timeout reached");
                System.exit(1);
            }
        }

        // display results
        PortScannerResult r = new PortScannerResult(resultList);
        System.out.println(r);
    }
}
