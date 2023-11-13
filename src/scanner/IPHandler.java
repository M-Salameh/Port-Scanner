package scanner;

import java.security.UnresolvedPermission;
import java.util.ArrayList;
import java.util.List;

public class IPHandler
{
    private String StartIP;
    private String EndIP;
    public IPHandler(String fip , String eip) throws IPExeption
    {
        this.StartIP = minIP(fip,eip);
        this.EndIP = maxIP(fip , eip);
    }
    private String maxIP(String fip , String eip)
    {
        int[] ip1,ip2 = new int[4];
        ip1 = convertIPtoIntegers(fip);
        ip2 = convertIPtoIntegers(eip);
        for (int i=0 ; i<4 ; i++)
        {
            if (ip1[i] > ip2[i]) return fip;
            if (ip1[i] < ip2[i]) return eip;
        }
        return fip;
    }
    private String minIP(String fip , String eip)
    {
        if (maxIP(fip,eip).equals(fip)) return eip;
        return fip;
    }
    private int[] convertIPtoIntegers(String ip)
    {
        int[] ip1 = new int[4];
        String[] parts = ip.split("\\.");
        for (int i = 0; i < 4; i++)
        {
            ip1[i] = Integer.parseInt(parts[i]);
        }
        return ip1;
    }

    public static class IPExeption extends Exception
    {
        String Error = "Invalid Range";
        public IPExeption ()
        {
            System.out.println(Error);
        }
    }
    public String getStartIP()
    {
        return StartIP;
    }
    public String getEndIP()
    {
        return EndIP;
    }
    public List<String> iterateIPRange()
    {
        List<String> ans = new ArrayList<>();
        int[] fip = new int[4];
        fip = convertIPtoIntegers(this.StartIP);
        int[] eip = new int[4];
        eip = convertIPtoIntegers(this.EndIP);
        return ans;
    }
}
