package com.project.util;
/**
 * 支付参数
 * @author Administrator
 *
 */
public class PayParameter {
	
	/**************支付宝********************/

	public static final String ALI_APP_ID = "2019030763490213";
	public static final String ALI_APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCeQqAo6VvbSNhWZJUYyLSUYVMMDXmwaamz2ERjhHPXmMtAkcnbavXbc4W4hmfJhifI1zPGOlrXFTO71lEhqlDj1Fuo9t9YwJzi2NmiLTeZlDvAebg5UpCbL5y0qH3Bx9rsuyb2cQy4zWNve3TzKXAwnVB0EN3xY7MAVboUHAo8/CpsZQgoxhBL/g8wLiSyNMeYuZerBhq900Sjdg3DFHpMC96fdz2YCyWZT1V05SRRqqnXkvHbzDe9IDV4IazTy0fznt6Gz1bRPhOyNLvHp1d4mwPlVBO/SUOI/ALnltduz845NIJcW3M+9MoED/A4MH1DH5xOL55HIFIVmwO17+WvAgMBAAECggEAIsYA6vxbcpNO9aykFg4Dn5bC6KMNWfXdk+axICy6R2ULQ+TEtbGK0+1ONH1T0LPpife/KI8p5piCBYPRz9PccjL28OlNqfKohi+fokLNNLGdb21PTUkW/QI5LHjZKPsA34M/zGaKpRB8CMLXr5YrUINAakBKg/+kFS7A9Co1FbD6LQaHrmYNg1DPA4TIgGsj1X2urDmoCKQWkvQFYm0DtJdOLxKaTvPlXNkYtts7sbn5gtD9Ht3hrw3KzIgs7UYcElof2dGSBxIzadZ+ulvUjYVX4et2dKko/c/ppHaTo2DsJFP83YCDoQ6SCXfdR3JtoOFSRMKpsrQtAMXmSudkAQKBgQDJp3ZN+ZFy04Qbc0pjgGWTL2GbK8T/vRuiSiuiMZSJgQi0fJuckZgn0DZ0TOEteJiXzKa+ohUYrEbuZFmAWe3X8/62+fNowvHULCkAvMMMDPyXkTeotNvgHfL9Ug1SEqgR2eT/+2Wg5ZT+lU0A4tvyHmpdUlTYP0C2xCwJ/UaimQKBgQDI6VQwtcm8l8uL1rTkX4pLJ+T/KleAOWQFfqYlw83E9cSJmJqb4KZmKdXvqe+5LXMmlGg4EEZ4uGVVZoCcKnN1QKc5+BHDhR7Vx9xcTq+xFdM72Aikfw5s9xupoXreyHKNoUykxSGbI5M5XqgTwSPQNF8A3eZ2k0n+gFCy4WC/hwKBgFMqutMb683UyHZdzT9OVBYpA3Nw0NW8k6oSNv60vhMb4ZA2mCMxtPQunlbG4lPTmfN5VwfmZxb+7l4hivwxmrNnERU77xS02fHsqJiCQVs1+/My1CbDOK+tCmtJrO69fQ2ntReSAm8iYJnCz7he1+F6g9mrPAzXwYPfUl5oU/p5AoGAT3OeXKj3P4KT9xgQbyOtL+117MaU1dtcR6P/Fn4pWJOMhpF3yt77TvmKZf3BFGz51a7ozAr7o2/gevZPM/LBuAO6Xf1RIQ361LX12fcwGYq0/zx+pnasgMD0CpAWZBXG32WDRsHCeXSHGy/ore86N3MOguw94HR81Y1u3F6ugO8CgYEAmrm3G80Adxfte0CqXVERbzwLu09J6UE+phUnJS30CbFmyUO0D9YzNVu6QZPSpf/9Uq7qUcXleJ3BwT1DodS+3pJDgXhP9iFBNU/yMnTzmlyM6o1t6hQz80Io2tb756qnuY8rhI/i3nseX5RYRNXHqXXy3mu5RxuznEjgWd6iEFU=";
	//应用公钥
    public static String  Ali_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnkKgKOlb20jYVmSVGMi0lGFTDA15sGmps9hEY4Rz15jLQJHJ22r123OFuIZnyYYnyNczxjpa1xUzu9ZRIapQ49RbqPbfWMCc4tjZoi03mZQ7wHm4OVKQmy+ctKh9wcfa7Lsm9nEMuM1jb3t08ylwMJ1QdBDd8WOzAFW6FBwKPPwqbGUIKMYQS/4PMC4ksjTHmLmXqwYavdNEo3YNwxR6TAven3c9mAslmU9VdOUkUaqp15Lx28w3vSA1eCGs08tH857ehs9W0T4TsjS7x6dXeJsD5VQTv0lDiPwC55bXbs/OOTSCXFtzPvTKBA/wODB9Qx+cTi+eRyBSFZsDte/lrwIDAQAB";
    // 支付宝公钥 
    public static String ALI_ALIPAY_PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq8Z1GbmdYG7uvNGVipVrqNA87OP81+0WD88LpRg7hxWyu5xErXBmE6R180SaL/myso86KcLq9dfWd7jENw9IIVFmLw112A7UALJWhACVKGTVCsA18ZrTdaNlZDPK7YMcC1AKf8NXD5GxYChfQGQhUNgKqt1Nru+nUoiu0/Zq1VAd3kqdiaxp/gk15b3yfhJ1/z7pSjIFd0Hx/SHfKndpQlr8dqERi43c6ko1Z5h5QVUFEOtWNAZcCZiyVLV2rYj10jV5+mrCiG0TNJ1Snozm6r6Im9W3B2D1IGmcne8hdEJeqSAGqVEQnbkOwFSJi5XIXgUC5AeYh3lu4v2cyxKxQwIDAQAB";
    /**********************微信支付参数*********/
	public static final String WEI_APPID = "wx9d11ba5609fe991e";
	public static final String WEI_MCHID = "1528339211";
	public static final String WEI_KEY = "afbbd96e6cd2dceb5cdb7765e2416ecf";
}
