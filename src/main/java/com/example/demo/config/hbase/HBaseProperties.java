package com.example.demo.config.hbase;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties( prefix="hbase" , ignoreUnknownFields = true , ignoreInvalidFields = true )
public class HBaseProperties {

	private String zkQuorum;
	private String zkClientPort;
	private String hadoopHomeDir;

	public String getZkQuorum() {
		return zkQuorum;
	}

	public void setZkQuorum(String zkQuorum) {
		this.zkQuorum = zkQuorum;
	}

	public String getZkClientPort() {
		return zkClientPort;
	}

	public void setZkClientPort(String zkClientPort) {
		this.zkClientPort = zkClientPort;
	}

	public String getHadoopHomeDir() {
		return hadoopHomeDir;
	}

	public void setHadoopHomeDir(String hadoopHomeDir) {
		this.hadoopHomeDir = hadoopHomeDir;
	}
}
