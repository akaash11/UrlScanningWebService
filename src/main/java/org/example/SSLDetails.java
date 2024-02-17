package org.example;

import com.microsoft.playwright.Response;

public class SSLDetails {
    String issuerName = "";
    String protocol = "";
    String subjectName = "";
    double validFrom = 0.0;
    double validTo = 0.0;
    SSLDetails(String issuerName, String protocol, String subjectName, double validFrom,double validTo){
        this.issuerName = issuerName;
        this.protocol = protocol;
        this.subjectName = subjectName;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }
    public static SSLDetails getSSLDetails(Response response){
        return new SSLDetails(
                response.securityDetails().issuer,
                response.securityDetails().protocol,
                response.securityDetails().subjectName,
                response.securityDetails().validFrom,
                response.securityDetails().validTo);
    }

    @Override
    public String toString() {
        return "SSLDetails{" +
                "issuerName='" + issuerName + '\'' +
                ", protocol='" + protocol + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                '}';
    }
}
