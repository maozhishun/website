package com.gz.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class HostData {
	// 主机名称
	public String hostName;
	// 主机ip
	public String ip;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public HostData() {
		try {
			this.hostName = java.net.InetAddress.getLocalHost().getHostName();
			// this.ip = java.net.Inet4Address.getLocalHost().getHostAddress();
			this.ip = getLocalIP();
		} catch (UnknownHostException e) {
			setHostName("unknown-host");
		}
	}

	/**
	 * 获得主机IP
	 * 
	 * @return String
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本机ip地址，并自动区分Windows还是linux操作系统
	 * 
	 * @return String
	 */
	public static String getLocalIP() {
		String sIP = "";
		InetAddress ip = null;
		try {
			// 如果是Windows操作系统
			if (isWindowsOS()) {
				ip = InetAddress.getLocalHost();
			}
			// 如果是Linux操作系统
			else {
				boolean bFindIP = false;
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
						.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					if (bFindIP) {
						break;
					}
					NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
					// ----------特定情况，可以考虑用ni.getName判断
					// 遍历所有ip
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						ip = (InetAddress) ips.nextElement();
						if ((ip.getHostAddress().endsWith(".0")) || (ip.getHostAddress().endsWith(".1"))) {
							continue;
						}
						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
								&& ip.getHostAddress().indexOf(":") == -1) {
							bFindIP = true;
							break;
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}

}
