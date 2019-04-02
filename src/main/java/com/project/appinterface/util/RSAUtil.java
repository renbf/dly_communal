package com.project.appinterface.util;

import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.util.RSAUtil.RSAUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {
    static String publicKey;
    static String privateKey;
    /**
     * showdoc
     * @catalog 测试文档/用户相关
     * @title 用户登录
     * @description 用户登录的接口
     * @method get
     * @url https://www.showdoc.cc/home/user/login
     * @return {"error_code":0,"data":{"uid":"1","username":"12154545","name":"吴系挂","groupid":2,"reg_time":"1436864169","last_login_time":"0"}}
     * @return_param groupid int 用户组id
     * @return_param name string 用户昵称
     * @remark 这里是备注信息
     * @number 99
     */
    @RequestMapping(value="/queryHomePage",method= RequestMethod.GET)
    @ApiOperation(value=" 查询首页",httpMethod="GET",response= Result.class)
    public @ResponseBody
    DataResult queryHomePage(HttpServletRequest request, @ApiParam(name="privateKey",value="私钥",required=true)@RequestParam(value="privateKey",required=false) String privateKey,
                             @ApiParam(name="json",value="数据",required=true)@RequestParam(value="json",required=false) byte[] encodedData) throws Exception {
        DataResult result =new DataResult();
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
                privateKey);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
        return result;
    }
    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getPublicKey",method=RequestMethod.GET)
    @ApiOperation(value=" 生成公钥",httpMethod="GET",response=Result.class)
    public @ResponseBody DataResult getPublicKey(HttpServletRequest request) throws Exception {
        DataResult result=new DataResult();
        Map<String, Object> keyMap = RSAUtils.genKeyPair();
        publicKey = RSAUtils.getPublicKey(keyMap);
        privateKey = RSAUtils.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
        Map<String,String> map=new HashMap<String,String>();
        map.put("publicKey",publicKey);
        map.put("privateKey",privateKey);
        result.setResult(map);
        return result;
    }
}
