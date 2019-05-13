package az.pashabank.posemu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

class HostClient {
    
    private static HostClient client;
    
    private String hostIpAddress;
    private int hostPort;
    private int hostTimeout;
    
    private Socket sock;
    
    private HostClient () { }
    
    static HostClient getInstance () throws Exception {
        if (client == null) {
            client = new HostClient(); 
        }
        Database db = Database.getInstance();
        db.connect();
        client.hostIpAddress = db.getParameter(Constants.PARAM_ACQ_HOST_IP_ADDRESS);
        client.hostPort = db.getIntParameter(Constants.PARAM_ACQ_HOST_PORT);
        client.hostTimeout = db.getIntParameter(Constants.PARAM_ACQ_HOST_TIMEOUT);
        db.disconnect();
        return client;
    }
    
    void connect () throws IOException, UnknownHostException {
        this.sock = new Socket(this.hostIpAddress, this.hostPort);
        this.sock.setSoTimeout(this.hostTimeout);
        
    }
    
    void disconnect () throws IOException {
        if (this.sock != null) {
            this.sock.close();
        }
    }
    
    String send (String req) throws IOException {
        String res = null;
        try (DataOutputStream out = new DataOutputStream(this.sock.getOutputStream());
                DataInputStream in = new DataInputStream(this.sock.getInputStream())) {
            out.writeUTF(req);
            res = in.readUTF();
        } catch (IOException e) {
            throw new IOException(e);
        }
        return res;
    }

    @Override
    public String toString() {
        return "HostClient{" + "hostIpAddress=" + hostIpAddress + ", hostPort=" + hostPort + ", hostTimeout=" + hostTimeout + ", sock=" + sock + '}';
    }
    
    
    
    public static void main(String[] args) throws Exception {
        String msg = "1200701405d820c00200165315357289693858000000000000001111160928192518161251010151334420020005166160928000375315357289693858D16122011985704200000POS0020 1101054        1905F2A02084082023800950500000000009A031609289C01009F02060000000011119F03060000000000009F10120110A0800324380073C900000000000000FF9F1A0209819F2608B1C4853CA014D7BC9F2701809F3602000B9F370467AADBD1";
        HostClient c = HostClient.getInstance();
        System.out.println(c);
        c.connect();
        String response = c.send(msg);
        System.out.println("response="+response);
        c.disconnect();
    }
    
}
