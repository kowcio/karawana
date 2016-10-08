package allinone;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.sun.corba.se.spi.activation.LocatorPackage.ServerLocation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by Kowcio on 2016-10-08.
 */
public class TestInMainMethod {




    public static void main(String[] args) throws UnknownHostException {

        CrunchifyGetIPHostname();












}





    public static void CrunchifyGetIPHostname() throws UnknownHostException {

        InetAddress ip;
        String hostname;
        ip = InetAddress.getLocalHost();
        hostname = ip.getHostName();
        System.out.println("Your current IP address : " + ip);
        System.out.println("Your current Hostname : " + hostname);

    }



}
