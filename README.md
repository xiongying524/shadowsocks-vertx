shadowsocks-vertx
================

[![License](http://img.shields.io/:license-apache-blue.svg?style=flat-square)](http://www.apache.org/licenses/LICENSE-2.0.html)

#Intro

shadowsocks-vertx is a socks5 proxy which can help you get through firewalls. It is a java port of shadowsocks. This project is based on vert.x.

Java 8 is needed.  

Gradle is needed.  

It is **unstable**! If you encounter any problems, please open an issue.

About shadowsocks, please refer to https://shadowsocks.org/

About vert.x, please refer to http://vertx.io/

#Features

Not support ipv6. Not support udp.

Supported encrypt method:

    aes-256-cfb, chacha20, rc4-md5


Support JSON config file. Please refer to https://github.com/shadowsocks/shadowsocks/wiki/Configuration-via-Config-File.
Note:

    1. Not support: local_address, client will bind 0.0.0.0 not 127.0.0.1
    2. Not support: fast_open. Java doesn't have such feature.
    3. Not support: workers. Vertx will set work thread number automatically.
    4. Additional: server_mode, set the running mode, true for the server, false for the client.
    5. Additional: iv_len, set the iv length.

You could refer to demo config etc/demo.json.  

It is recommended to use TCP-BBR !

How to run:
===========

### (1) generate fat jar
```
$ gradle clean build fatJar
```


Then you will get **shadowsocks-fat-ver.jar** in build/libs.

### (2) run both server and client
```
$ java -jar shadowsocks-fat-ver.jar configFile
```

### (3) web browser

Chrome + SwitchyOmega.
