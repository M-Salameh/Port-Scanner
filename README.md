# Java Port Scanner

Simple port scanner written in Java with parallelization support.

## Usage

`$ java -jar JavaPortScanner.jar <IPv4> <IPv4> [PortStart-PortEnd]`
- It does not work like this so far it needs to be working using jar
- For now it takes input from console and runs
- `<IPv4>` is a required parameter that is a valid IPv4 address
- `[PortStart-PortEnd]` is an optional parameter to indicate certain range of ports to scan
  - If this is empty, scanner will try to scan all possible ports (can take a long time)
  - `PortStart-PortEnd` will scan from `PortStart` to `PortEnd`

## Examples

### Scanning all possible ports in The IP range of user input

intput:
two IPv4
<!-- 
```bash
$ java -jar JavaPortScanner.jar 127.0.0.1
Scanning 127.0.0.1:1-65535
2/65535 were open
80:     open
443:    open

``` -->

### Scanning certain port(s) only
Input :
Two IPv4
Start Port
End Port
(IPs can be equal indicating only one IP we want same as for Ports)
<!-- 
```bash
$ java -jar JavaPortScanner.jar 127.0.0.1 80
Scanning 127.0.0.1:80
1/1 were open
80:     open

$ java -jar JavaPortScanner.jar 127.0.0.1 80-81
Scanning 127.0.0.1:80-81
1/2 were open
80:     open

``` -->
