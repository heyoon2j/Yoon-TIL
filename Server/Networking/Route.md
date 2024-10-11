# Route
```sh
$ ip route { add | del | change | append | replace } ROUTE
# ROUTE := NODE_SPEC [ INFO_SPEC ]
# 
"""
# NODE_SPEC := [ TYPE ] PREFIX [ tos TOS ] [ table TABLE_ID ] [
               proto RTPROTO ] [ scope SCOPE ] [ metric METRIC ] [ ttl-
               propagate { enabled | disabled } ]

# INFO_SPEC := { NH | nhid ID } OPTIONS FLAGS [ nexthop NH ] ...

# NH := [ encap ENCAP ] [ via [ FAMILY ] ADDRESS ] [ dev STRING ] [ weight NUMBER ] NHFLAGS
"""

```

```sh
# [Destination] via [Gateway] dev [Network_interface] proto [kernel] scope link src 172.16.30.36 table 10001 
# : Destination 인 경우, Gateway로 트래픽을 dev(Device) Network_interface를 통해 보낸다.
# default : 0.0.0.0


# Static route for metadata service
169.254.169.254 via 0.0.0.0 dev eth0


deault via 172.16.30.1 dev eth1 table 10001
172.16.30.0/255.255.255.192 dev eth1 proto kernel scope link src 172.16.30.36 table 10001
default via 172.16.30.1 dev eth1 metric 10001



Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         172.16.30.1     0.0.0.0         UG    0      0        0 eth0
0.0.0.0         172.16.30.1     0.0.0.0         UG    10001  0        0 eth1
169.254.169.254 0.0.0.0         255.255.255.255 UH    0      0        0 eth0
172.16.30.0     0.0.0.0         255.255.255.192 U     0      0        0 eth0
172.16.30.0     0.0.0.0         255.255.255.192 U     0      0        0 eth1

```
* Destination : 도착지
* Gateway : 게이트웨이. 도착지에 가기 위해 거쳐가야 하는 경유지
* Genmask : Netmask
* Flags : Flag
*  