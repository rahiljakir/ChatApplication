import java.net.*;
import java.util.*;

class sending extends Thread {

    InetAddress i;
    DatagramSocket ds;
    String name;

    public sending(InetAddress i, DatagramSocket ds, String name) {
        this.name = name;
        this.i = i;
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            while (true) {
                byte[] buff = (name + ": " + sc.nextLine()).trim().getBytes();
                DatagramPacket dp = new DatagramPacket(buff, buff.length, i, 2000);
                ds.send(dp);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class message {

    public static void main(String[] args) {

        try {
            DatagramSocket ds = new DatagramSocket(2000);
            sending s = new sending(InetAddress.getByName(args[0]), ds, args[1]);
            s.start();
            while (true) {
                byte[] buff = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buff, 1024);
                ds.receive(dp);
                System.out.println(new String(dp.getData()).trim());
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}