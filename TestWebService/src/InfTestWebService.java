package tk.ziniulian.wstest;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface InfTestWebService {
    @WebMethod
    @WebResult(name="r")
    String setWs (@WebParam(name="url") String url, @WebParam(name="npc") String npc);

    @WebMethod
    @WebResult(name="r")
    String call (@WebParam(name="meth") String meth, @WebParam(name="pnam") String pnam, @WebParam(name="parm") String parm);
}
