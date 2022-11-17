/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldap;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 *
 * @author kwyhr
 */
public class MySSLSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory socketFactory;

    /**
     *
     */
    public MySSLSocketFactory() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { new DummyTrustManager() }, new SecureRandom());
            socketFactory = ctx.getSocketFactory();
        } catch (Exception ex) {
          //  ex.printStackTrace(System.err);
            /* handle exception */
        }
    }

    /**
     *
     * @return
     */
    public static SocketFactory getDefault() {
        return new MySSLSocketFactory();
    }

    /**
     *
     * @return
     */
    @Override
    public String[] getDefaultCipherSuites() {
        return socketFactory.getDefaultCipherSuites();
    }

    /**
     *
     * @return
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return socketFactory.getSupportedCipherSuites();
    }

    /**
     *
     * @param socket
     * @param string
     * @param i
     * @param bln
     * @return
     * @throws IOException
     */
    @Override
    public Socket createSocket(Socket socket, String string, int i, boolean bln) throws IOException {
        return socketFactory.createSocket(socket, string, i, bln);
    }

    /**
     *
     * @param string
     * @param i
     * @return
     * @throws IOException
     * @throws UnknownHostException
     */
    @Override
    public Socket createSocket(String string, int i) throws IOException, UnknownHostException {
        return socketFactory.createSocket(string, i);
    }

    /**
     *
     * @param string
     * @param i
     * @param ia
     * @param i1
     * @return
     * @throws IOException
     * @throws UnknownHostException
     */
    @Override
    public Socket createSocket(String string, int i, InetAddress ia, int i1) throws IOException, UnknownHostException {
        return socketFactory.createSocket(string, i, ia, i1);
    }

    /**
     *
     * @param ia
     * @param i
     * @return
     * @throws IOException
     */
    @Override
    public Socket createSocket(InetAddress ia, int i) throws IOException {
        return socketFactory.createSocket(ia, i);
    }

    /**
     *
     * @param ia
     * @param i
     * @param ia1
     * @param i1
     * @return
     * @throws IOException
     */
    @Override
    public Socket createSocket(InetAddress ia, int i, InetAddress ia1, int i1) throws IOException {
        return socketFactory.createSocket(ia, i, ia1, i1);
    }
}