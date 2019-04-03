package com.project.appinterface.util.wxpayutil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;
import com.project.common.config.Global;
import com.project.util.PayParameter;

public class MyConfig implements WXPayConfig{

    private byte[] certData;

    public MyConfig() throws Exception {
        String certPath = Global.getWeixinCertPath();
//        String certPath = "C:/Users/admin/Desktop/gift/weixin/weixinXCX/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);

        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return PayParameter.WEI_APPID;
    }

    public String getMchID() {
        return PayParameter.WEI_MCHID;
    }

    public String getKey() {
        return PayParameter.WEI_KEY;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public int getTimeOutMs() {
        return 0;
    }
}