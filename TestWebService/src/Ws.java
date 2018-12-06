package com.invengo.lzr.ws2ws.imp;

import java.util.List;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import com.google.gson.Gson;
import com.invengo.lzr.ws2ws.InfWs;
import tk.ziniulian.util.dao.WebSrv;

@WebService(endpointInterface = "com.invengo.lzr.ws2ws.InfWs")
// @BindingType(value=javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)    // 使用 SOAP1.2 协议
public class Ws implements InfWs {
	private Gson gson = new Gson();
    private List<String> tls = new ArrayList<String>();
    private WebSrv ws = new WebSrv("", "");

    public String setWs (String url, String npc) {
        ws.setUrl(url).setNpc(npc);
        return (
            "{\"ok\":true, \"dat\":{\"url\":\""
            + url + "\",\"npc\":\""
            + npc + "\"}"
        );
    }

    public String call (String meth, String pnam, String parm) {
        String r = null;
        if (pnam == null) {
            r = ws.qry(meth);
        } else {
            r = ws.qry(meth,
                gson.fromJson(pnam, tls.getClass()),
                gson.fromJson(parm, tls.getClass())
            );
        }
        if (r == null) {
            r = "{\"ok\":false}";
        } else if (r.startsWith("[") || r.startsWith("{")) {
            r = "{\"ok\":true, \"dat\":" + r + "}";
        } else {
            r = "{\"ok\":false, \"msg\":\"" + r + "\"}";
        }
        return r;
    }
}
