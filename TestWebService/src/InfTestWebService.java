package tk.ziniulian.test.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface InfTestWebService {
    @WebMethod
    @WebResult(name="r")
    String hello (@WebParam(name="nam") String nam);
}
