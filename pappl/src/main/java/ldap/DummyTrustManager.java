/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldap;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author kwyhr
 */
public class DummyTrustManager implements X509TrustManager {

    /**
     *
     * @param xcs
     * @param string
     * @throws CertificateException
     */
    @Override
    public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        // do nothing
    }

    /**
     *
     * @param xcs
     * @param string
     * @throws CertificateException
     */
    @Override
    public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        // do nothing
    }

    /**
     *
     * @return
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new java.security.cert.X509Certificate[0];
    }
}
