# DisReceiver

DisReceiver is a Java lanaguage example of receiving Distributed Interactive Simulation (DIS) Protocol Data Units (PDUs).

DIS sends binary format messages. The messages are in binary format, and this program reads broadcast binary messages and converts them into Java object format. 

You should run this in conjuction with DisSender, available at the git repository at https://github.com/mcgredonps/DisSender.git

Run both at the same time and see the messages exchanged between the DisSender and DisReceiver applications. You will probably have to change the TCP/IP addresses used to match whatever has been assigned in your network.
