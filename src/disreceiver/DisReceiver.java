
package disreceiver;

import java.net.*;
import java.io.*;

import edu.nps.moves.dis.*;
import edu.nps.moves.disutil.*;

/** 
 * A simple application to receive Distributed Interactive Simulation (DIS)
 * network messages.
 * 
 * @author mcgredo
 */
public class DisReceiver {

    public static void main(String[] args) 
    {
        try
        {
            // Create a socket that receives broadcast packets on port 3000. The 
            // setReuseAddress lets you work with another user of port 3000.
            DatagramSocket socket = new DatagramSocket(null);
            socket.setReuseAddress(true);
            socket.setBroadcast(true);
            InetSocketAddress addr = new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 3000);
            socket.bind(addr);
          
            // A PDU factory receives a collection of raw bytes, then reads enough
            // of the PDU header to determine what type of message it is. It then
            // interprets the message accordingly.
            PduFactory factory = new PduFactory();
            
            while(true)
            {
                // Message max size is 1500 bytes
                byte[] buffer = new byte[1500];
                
                // A datagram packet to receive the message in
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("Got packet");
               
                // Decode the message in the factory object
                Pdu aPdu = factory.createPdu(packet.getData());
                
                // We can tell via they PDU type field in every PDU what
                // type of PDU this is. Switch based on that.
                
                switch(aPdu.getPduType())
                {
                    case 1: 
                        System.out.println("Got entity state pdu");
                        EntityStatePdu espdu = (EntityStatePdu)aPdu;
                        break;
                        
                    case 2:
                        System.out.println("Got Fire PDU");
                        FirePdu firePdu = (FirePdu)aPdu;
                        break;
                        
                    // Dozens of other PDUs...
                        
                    default:
                        System.out.println("Unrecognized PDU");
                }
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
