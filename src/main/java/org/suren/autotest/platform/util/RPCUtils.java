/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.suren.autotest.platform.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * @author suren
 * @date 2017年3月18日 下午6:24:27
 */
public class RPCUtils
{
    // Substitute your Dev Key Here
    public static final String DEV_KEY =  "ff698332e128d54e631dd42dae8e9bf70fd6df22b6ab3c057aed052bc1a5e616";
    // Substitute your Server URL Here
    public static final String SERVER_URL = "http://surenpi.com:8088/testlink/api/xmlrpc.php";    
    
    /**
     * report test execution to TestLink API
     * 
     * @param int tcid
     * @param int tpid
     * @param String status
     */
    public static void testLinkReport(int tcid, int tpid, String status)
    {
        try 
        {
            XmlRpcClient rpcClient;
            XmlRpcClientConfigImpl config;
            
            config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(SERVER_URL));
            rpcClient = new XmlRpcClient();
            rpcClient.setConfig(config);        
            
            ArrayList<Object> params = new ArrayList<Object>();
            Hashtable<String, Object> executionData = new Hashtable<String, Object>();                
            executionData.put("devKey", DEV_KEY);
            executionData.put("tcid", tcid);
            executionData.put("tpid", tpid);
            executionData.put("status", status);
            params.add(executionData);
            
            Object[] result = (Object[]) rpcClient.execute("tl.reportTCResult", params);
 
            // Typically you'd want to validate the result here and probably do something more useful with it
            System.out.println("Result was:\n");                
            for (int i=0; i< result.length; i++)
            {
                Map item = (Map)result[i];
                System.out.println("Keys: " + item.keySet().toString() + " values: " + item.values().toString());
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (XmlRpcException e)
        {
            e.printStackTrace();
        }
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RPCUtils.testLinkReport(123, 123, "p");
	}

}
