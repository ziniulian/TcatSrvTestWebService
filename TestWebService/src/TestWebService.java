package tk.ziniulian.test.webservice.imp;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import tk.ziniulian.test.webservice.InfTestWebService;

@WebService(endpointInterface = "tk.ziniulian.test.webservice.InfTestWebService")
// @BindingType(value=javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)    // 使用 SOAP1.2 协议
public class TestWebService implements InfTestWebService {
    public String hello (String nam) {
        return "Hello " + nam;
    }
}
