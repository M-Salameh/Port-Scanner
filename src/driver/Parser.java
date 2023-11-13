package driver;

import scanner.IPHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public final class Parser {

    /**
     * Get target information from user inputs.
     *
     * @param args arguments from user
     */
    public static List<Target> run(String[] args) throws InvalidUserInputException
    {
        List<Target> ret = new ArrayList<>();
        List<String> IPs = new ArrayList<>();
        if (args.length < 2 || args.length >3)
        {
            throw new InvalidUserInputException("default");
        }

        // set ip
        if (isValidIPv4Address(args[0]) && isValidIPv4Address(args[1]))
        {
            try
            {
                IPHandler ipHandler = new IPHandler(args[0] , args[1]);
                String startIP = ipHandler.getStartIP();
                String endIP = ipHandler.getEndIP();
                IPs = IPv4Iterator.iterateIPv4Addresses(startIP , endIP);
                for (String ip : IPs)
                {
                    Target target = new Target();
                    target.targetIP = ip;
                    ret.add(target);
                }
            }
            catch (IPHandler.IPExeption e)
            {
                throw new RuntimeException(e);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }

        } else {
            // invalid ip
            throw new InvalidUserInputException("ip");
        }

        // set port range
        String targetPortStart;
        String targetPortEnd;

        if (args.length == 2) {
            // target all possible port if provided ip is valid
            targetPortStart = "1";
            targetPortEnd = "65535";
        } else if (args[2].contains("-")) {
            // is a range

            // first occurrence of dash
            int index = args[2].indexOf("-");
            targetPortStart = args[2].substring(0, index);
            targetPortEnd = args[2].substring(index + 1, args[2].length());
        } else {
            // a single port
            targetPortStart = args[2];
            targetPortEnd = args[2];
        }

        try {
            int p1 = Integer.parseInt(targetPortStart);
            int p2 = Integer.parseInt(targetPortEnd);
            for (Target target : ret)
            {
                target.portRange[0] = p1;
                target.portRange[1] = p2;
            }

        } catch (Exception e)
        {
            // could not convert port num to int
            throw new InvalidUserInputException("port");
        }

        // check if ports are in valid range
        for (int i=0 ; i<ret.size(); i++)
        {
            if (!isValidPortNumber(ret.get(i).portRange[0]) || !isValidPortNumber(ret.get(i).portRange[1]))
            {
                throw new InvalidUserInputException("port");
            }
        }

        return ret;
    }

    /**
     * Return true iff portNum is in valid range, 1 to 65535.
     *
     * @param portNum port number to check.
     * @return whether portNum is a valid port number.
     */
    private static boolean isValidPortNumber(int portNum) {
        return (1 <= portNum && portNum <= 65535);
    }

    /**
     * Validate provided IPv4 address.
     *
     * @param ip
     * @return whether ip is a valid IPv4 Address
     */
    private static boolean isValidIPv4Address(String ip) {
        try {
            return InetAddress.getByName(ip).getHostAddress().equals(ip);
        } catch (UnknownHostException e) {
            return false;
        }
    }
}
