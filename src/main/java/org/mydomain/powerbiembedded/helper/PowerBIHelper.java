package org.mydomain.powerbiembedded.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.joda.time.DateTime;
import org.mydomain.powerbiembedded.model.ReportList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is a ample of Power BI Embedded with reference below article
 * https://blogs.msdn.microsoft.com/tsmatsuz/2016/07/20/power-bi-embedded-rest/
 * 
 */
public class PowerBIHelper {
	static final Logger LOG = LoggerFactory.getLogger(PowerBIHelper.class);
	private static final String MAC_ALOG = "HmacSHA256";
	private static final String TEXT_ENCODING = "UTF-8";

	static String rfc4648Base64Encode(String arg) throws UnsupportedEncodingException {
		Encoder encoder = Base64.getMimeEncoder(0, new byte[0]);
		String res = encoder.encodeToString(arg.getBytes(TEXT_ENCODING));
		res = res.replace("/", "_");
		res = res.replace("+", "-");
		res = res.replace("=", "");
		return res;
	}

	static String rfc4648Base64Encode(byte[] arg) throws UnsupportedEncodingException {
		Encoder encoder = Base64.getMimeEncoder(0, new byte[0]);
		String res = encoder.encodeToString(arg);
		res = res.replace("/", "_");
		res = res.replace("+", "-");
		res = res.replace("=", "");
		return res;
	}

	public static String createAppToken(String workspaceid, String reportId, String workspaceCollectionName,
			String accessToken) {
		String apptoken = "";
		try {
			String token1 = "{\"typ\":\"JWT\",\"alg\":\"HS256\"}";
			String token2 = "{" + //
					"\"wid\":\"" + workspaceid + "\"," + // workspaceid
					"\"rid\":\"" + reportId + "\"," + // reportid
					"\"wcn\":\"" + workspaceCollectionName + "\"," + // workspace_collection_name
					"\"iss\":\"PowerBISDK\"," + //
					"\"ver\":\"0.2.0\"," + //
					"\"aud\":\"https://analysis.windows.net/powerbi/api\"," + //
					"\"nbf\":\"" + (new Date().getTime() / 1000) + "\","//
					+ "\"exp\":\"" + (new DateTime().plusHours(1).toDate().getTime() / 1000) + "\"}";//
			String inputval = rfc4648Base64Encode(token1) + "." + rfc4648Base64Encode(token2);
			SecretKeySpec sk = new SecretKeySpec(accessToken.getBytes(TEXT_ENCODING), MAC_ALOG);
			Mac mac = Mac.getInstance(MAC_ALOG);
			mac.init(sk);
			byte[] mac_bytes = mac.doFinal(inputval.getBytes(TEXT_ENCODING));

			apptoken = inputval + "." + rfc4648Base64Encode(mac_bytes);
			LOG.trace("PowerBIHelper creates AppToken = " + apptoken);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("PowerBI Helper class something wrong", e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("PowerBI Helper class something wrong", e);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new RuntimeException("PowerBI Helper class something wrong", e);
		}
		return apptoken;
	}

	public static ReportList getReports(String workspaceid, String workspaceCollectionName, String accessToken) {
		ReportList list = null;
		try {
			String url = "https://api.powerbi.com/v1.0/collections/" + workspaceCollectionName + "/workspaces/"
					+ workspaceid + "/reports";
			Client client = ClientBuilder.newClient();
			MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
			String authorizationHeaderValue = "AppKey " + accessToken;
			headers.putSingle("Authorization", authorizationHeaderValue);
			LOG.trace("Authorization header = " + authorizationHeaderValue);
			String src = client.target(url).request().headers(headers).get(String.class);
			list = new ObjectMapper().readValue(src, ReportList.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("PowerBI Helper class something wrong", e);
		}
		return list;
	}
}
