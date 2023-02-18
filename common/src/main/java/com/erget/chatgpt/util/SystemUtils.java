package com.erget.chatgpt.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@Slf4j
public class SystemUtils {

    public static boolean systemIP(String systemIP) {
        if (StringUtils.isBlank(systemIP)) {
            log.info("未指定定时任务运行服务器IP地址");
            return false;
        }
        systemIP = StringUtils.trim(systemIP);
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        if (systemIP.equals(StringUtils.trim(ip.getHostAddress()))) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("检查服务端运行IP异常", e);
        }
        return false;
    }

    /**
     * 获取本机公网ip
     * @return
     */
    public static String getRealIP() {

        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP

        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();

            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1 && ip instanceof Inet4Address) {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()&& ip.getHostAddress().indexOf(":") == -1 && ip instanceof Inet4Address) {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            log.error("检查服务端运行IP异常", e);
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }

    }

}
