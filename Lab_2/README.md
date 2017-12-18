What is a Slowloris DDoS attack?
-
A DDOS(Distributed Denial of Service) attack is one of the major problem, that organizations are dealing with today.

Such a kind of attack is very difficult to mitigate, especially for small organizations with small infrastructure. The main difficulty in dealing with DDOS attack is the fact that, traditional firewall filtering rules does not play well. The main reason behind this problem is that, most of the time the attacking machines(machine's that take part in a DDOS attack, and becomes part of a bot-net) are large in number and are from diverse geographical locations.



Which web-server's are affected by slowloris attack?
--
1.Apache (1.x & 2.x)

2.dhttpd

3.Goahead web server

Web server's that work on an event based architecture like nginx are not affected by a slowloris attack.

It seems that IIS is also is not affected by a slowloris attack(although not tested by us).

How to prevent/protect/mitigate a slowloris attack?
--
* Use Hardware Load Balencers that accepts only full http connections.

Using hardware load balencer's with an http profile configured will be the best method to stop such an attack.

Because the loadbalencer will inspect the packet's and will forward only those http request to the web server which are complete.

If you are using a F5 based BIG-IP Load Balencer i recommend reading the below link for mitigating slowloris attacks.

Mitigate Slowloris on BIG-IP F5 Load Balencer

Other Load balencer's like the below ones also can be configured with http profile to mitigate such an attack

Citrix NetScaler

Cisco CSS

* Protect your web server by using IPtables by limiting connections from a particular host

You can certainly limit the number of connections with the help of iptables to port 80. For example if suppose i want to block
```javascript
iptables -A INPUT -p tcp --syn --dport 80 -m connlimit --connlimit-above 30 -j DROP
```

* Configure the timeout directive in apache

Although this is not at all a good solution, you can still increase the rate with which your web server will reap inactive connections.

You can simply modify the timout directive in /etc/httpd/conf/httpd.conf file.

Reducing it to a lower value will atleast make the attack difficult(but still the attack can take down the server, by increasing the number of requests)

This is not at all a good solution.

* mod_antiloris apache module

Another good solution that i tested is an apache module called as mod_antiloris. This module can be installed using the below steps.

```javascript
[root@localhost ~]# wget http://sourceforge.net/projects/mod-antiloris/files/mod_antiloris-0.4.tar.bz2/download
[root@localhost ~]# tar -xvjf mod_antiloris-0.4.tar.bz2
mod_antiloris-0.4/
mod_antiloris-0.4/ChangeLog
mod_antiloris-0.4/mod_antiloris.c
 
[root@localhost ~]# cd mod_antiloris-0.4
[root@localhost mod_antiloris-0.4]# apxs -a -i -c mod_antiloris.c

````

Now simply restart apache to load the new module.


How to use Slowloris
--
Requirements:

  sudo apt-get update  

  sudo apt-get install perl

  sudo apt-get install libwww-mechanize-shell-perl

  sudo apt-get install perl-mechanize


1)Download slowloris.pl

2)Open Terminal

2)# cd /thePathToYourSlowloris.plFile

3)# ./slowloris.pl

4)# perl slowloris.pl -dns (Victim URL or IP) -options




How does slowloris http dos attack work?
--
An in depth understanding of http request and response is very much necessary to comprehend this attack tool.

Because it exploits a vulnerability in the web server(which was purposely made by the authors for different advantages like serving requests for a slow connection ) which wait for a complete header to be received.
 

Apache & some other web server's have a mechanism of timeout.  An Apache web server will wait for this specified timeout duration for the completion of a request( if the request was incomplete ).

This timeout value is by default 300 seconds, but is modifiable. This timeout value is very much useful if a website serve's large files for download through http(because it maintains an active http connection of a slow client without breaking the download).

Another important fact to note here is that the timeout counter is reset every time the client sends some more data( so the timeout count will start again from 1 ).

But imagine a situation if somebody purposely send partial http requests and reset the timeout counter of each request by sending some bogus data very frequently.

That's exactly what slowloris does. It sends partial http request with bogus header's. Once all connections are consumed by sending partial requests, it keeps on maintaining the connection's by sending request data and reseting the timout counter.

A complete GET request looks like something below.

```javascript
GET / HTTP/1.0[CRLF]
User-Agent: Wget/1.10.2 (Red Hat modified)[CRLF]
Accept: */*[CRLF]
Host: 192.168.0.103[CRLF]
Connection: Keep-Alive[CRLF][CRLF]GET / HTTP/1.0[CRLF]
                                  User-Agent: Wget/1.10.2 (Red Hat modified)[CRLF]
                                  Accept: */*[CRLF]
                                  Host: 192.168.0.103[CRLF]
                                  Connection: Keep-Alive[CRLF][CRLF]
```
What are those CRLF in that get request?
--
CRLF stands for CR (Carriage Return) and LF (Line Feed). This character is an entity which is non printable, used to denote end of the line.

Even when you are typing on a text editor the editor puts a CRLF at the end of a line when you want a new line after that.

And two CRLF characters together is used to denote a blank line.

In the above shown GET request there are two CRLF characters at the end of the "Connection" header(which means a blank line). In http protocol, a blank line after the header's is used to represent the completion of the header.
 

Slowloris tool takes advantage of this in implementing its attack. It does not send a finishing blank line, which indicates the end of the http header.

Some web server's give higher priority to those requests which are complete in its header's. This is the reason why IIS is not affected by a slowloris attack.

An incomplete request send by the slowloris script is shown below. This below snippet is taken from the slowloris script

```javascript
"GET /$rand HTTP/1.1\r\n"
          . "Host: $sendhost\r\n"
          . "User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.503l3; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; MSOffice 12)\r\n"
          . "Content-Length: 42\r\n";
```
 
In the above snippet shown \r\n is used to denote carriage return and newline in perl. Two consecutive "\r\n\r\n", should be there to denote a blank line, which is not there. So thats an incomplete header in HTTP.



Slowloris perl script http dos attack and its usage
--

You can find the slowloris script from ha.ckers.org

Copy the script and run it against any of your web server for testing. Most of the apache web server's are vulnerable against this kind of an attack.

The usage of the script is quite simple as shown below.

```javascript
	
[root@localhost ~]# ./slowloris.pl -dns www.example.com

```

You can also modify the timout interval, if known to you, used by the server with -timeout option

For a complete detailed help with slowloris tool, give the script as an argument to "perldoc" command.

```javascript
[root@localhost ~]# perldoc ./slowloris.pl
```

Slowloris is mostly not noticed by IDS(Intrusion Detection system's), because it does not send a malformed request, but a legitimate request to the web server. Hence it bypasses most of the IDS system's out there. Slowloris works by the principle of consuming all available http connections on the server. Hence it takes time if its a high traffic web site, and are already connected by a number of clients. Because in that case slowloris needs to wait, for http connections to become available(because other clients are connected to it and are being served)
An important funny thing with slowloris attack is that, as soon as the attacker stops running the script, the website will become back online. Because the connections will automatically be closed by the webserver after some time(after the timeout interval).


Implementation
--
I have chosen a blog developed by me represented bellow.
<div align="center">
<img src="https://github.com/cristeav49/SI/blob/master/Lab_2/images/3.png" width="500"/>
</div>

Run in Terminal slowloris.pl 
<div align="center">
<img src="https://github.com/cristeav49/SI/blob/master/Lab_2/images/2.png" width="500"/>
</div>

Webpage isn't available anymore
<div align="center">
<img src="https://github.com/cristeav49/SI/blob/master/Lab_2/images/1.png" width="500"/>
</div>