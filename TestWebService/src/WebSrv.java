package tk.ziniulian.util.dao;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * 访问WebService
 * Created by LZR on 2018/11/27.
 */

public class WebSrv {
	private String url;		// 服务地址
	private String npc;		// 命名空间

	public WebSrv (String u, String n) {
		this.url = u;
		this.npc = n;
	}

	public WebSrv setUrl(String url) {
		this.url = url;
		return this;
	}

	public WebSrv setNpc(String npc) {
		this.npc = npc;
		return this;
	}

	public String qry (String mnam, LinkedHashMap<String, String> p) {
		String r = null;

		SoapObject req = new SoapObject(npc, mnam);
		if (p != null) {
			Iterator<Entry<String, String>> iterator = p.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				req.addProperty(entry.getKey(), entry.getValue());
			}
		}

		// SoapSerializationEnvelope msg = new SoapSerializationEnvelope(SoapEnvelope.VER11);	// SOAP1.1 协议
		SoapSerializationEnvelope msg = new SoapSerializationEnvelope(SoapEnvelope.VER12);
		msg.bodyOut = req;
		// msg.dotNet = true;	// 调用 .net版服务器的 WebService
		HttpTransportSE ht = new HttpTransportSE(url);

		try {
			ht.call(null, msg);
			SoapPrimitive res = (SoapPrimitive) msg.getResponse();
			if (res != null) {
				r = res.toString();
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return r;
	}
}
