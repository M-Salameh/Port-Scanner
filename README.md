# Java Port Scanner

Simple port scanner written in Java with parallelization support.

## Usage

`$ java -jar PortScanner.jar <IPv4> <IPv4> [PortStart-PortEnd]`

- `<IPv4>` is a required parameter that is a valid IPv4 address
- `[PortStart-PortEnd]` is an optional parameter to indicate certain range of ports to scan
  - If this is empty, scanner will try to scan all possible ports (can take a long time)
  - `PortStart-PortEnd` will scan from `PortStart` to `PortEnd`

## Examples

### Scanning all possible ports

```bash
$ java -jar PortScanner.jar 11.11.11.11 13.1.8.15
Scanning 11.11.11.11:1-65535
2/65535 were open
80:     open
443:    open

Scanning 11.11.11.12:1-65535
0/65535 were open
.
.
.
.
Scanning 13.1.8.15:1-65535
1/65535 were open
23:     open
```

### Scanning certain port(s) only

```bash
$ java -jar PortScanner.jar 174.135.200.47 200.0.3.1 80-90
Scanning 174.135.200.47:80-90
1/11 were open
80:     open
Scanning 174.135.200.48:80-90
2/11 were open
65:     open
113:    open
.
.
.
.
.
Scanning 200.0.3.1:80-90
1/11 were open
25:     open

$ java -jar PortScanner.jar 174.135.200.47 200.0.3.1 80
Scanning 174.135.200.47:80-80
1/1 were open
80:     open
.
.
.
.
.
Scanning 200.0.3.1:80-80
0/1 were open
```
