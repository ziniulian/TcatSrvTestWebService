package com.invengo.lzr.ws2ws.imp;

import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.jws.WebService;
import com.invengo.lzr.ws2ws.InfWs;
import tk.ziniulian.util.dao.WebSrv;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

@WebService(endpointInterface = "com.invengo.lzr.ws2ws.InfWs")
// @BindingType(value=javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)    // 使用 SOAP1.2 协议
public class Ws implements InfWs {
	private Gson gson = new Gson();
	private Type tm = new TypeToken<LinkedHashMap<String, String>>(){}.getType();
    private WebSrv ws = new WebSrv("", "");
	private Properties properties = new Properties();

	public Ws () {
		String u = "http://127.0.0.1/";
		try {
			// 读取配置文件
			String fp = System.getProperty("user.dir").replace("\\", "/") +"/../webapps/Ws2Ws/config.properties";
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fp));
			properties.load(bufferedReader);
			String ip = properties.getProperty("ip");
			String port = properties.getProperty("port");
			u = "http://" + ip + ":" + port + "/room/DataWebServicePort";
		} catch (Exception e) {
			e.printStackTrace();
		}
		ws = new WebSrv(u, "http://sys.action.web.cw.com/");
	}

    public String setWs (String url, String npc) {
        ws.setUrl(url).setNpc(npc);
        return (
            "{\"ok\":true, \"dat\":{\"url\":\""
            + url + "\",\"npc\":\""
            + npc + "\"}}"
        );
    }

    public String call (String meth, String parm) {
        String r = null;
		LinkedHashMap<String, String> m = null;
        if (parm != null) {
			try {
				m = gson.fromJson(parm, tm);
			} catch (Exception e) {
				// e.printStackTrace();
			}
        }
		r = ws.qry(meth, m);
        if (r == null) {
            r = "{\"ok\":false, \"error\":\"数据接口 : 远程服务连接失败！\"}";
        }
        return r;
    }
}
