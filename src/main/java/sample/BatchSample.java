/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample;

import java.io.IOException;
import java.net.MalformedURLException;
import delsh.livy.*;

public class BatchSample {

	private LivyBatchClient client = null;
	private String username = null;
	private String password = null;
	private String endpoint = null;
	private String jarname = null;
	private String storageaccount = null;
	private String containernmae = null;
	private String inputfile = null;
	private final String AZUREHDINSIGHT_LIVY_URI = ".azurehdinsight.net/livy";
	private String environment = "bizqa";

	public void run() {

		BatchJobParameters param = new BatchJobParameters("wasbs://"+containernmae+"@"+storageaccount+"/"+jarname,"org.apache.spark.examples.JavaWordCount");
		// String [] inputFile  = {"wasbs://test@edpazuredev1bloblstorage.blob.core.windows.net/README.md"};
		String [] inputFile = {"wasbs://"+containernmae+"@"+storageaccount+"/"+inputfile};
		param.args = inputFile;
		try {
			//System.out.println(client.getActiveSessions());
			client.createJob(param);
			int status = Session.NOT_STARTED;
			while(true) {
				//System.out.println("Full  Log: " + client.getFullLog());
				status = client.getSession().getState();
		    	if(status == Session.RUNNING) {
		    		System.out.println("Status: RUNNING");
					System.out.println(" AppId: " + client.getSession().getAppId());
					System.out.println(" AppInfo: " + client.getSession().getAppInfo());
					System.out.println(" Id: " + client.getSession().getId());
					System.out.println(" Log: " + client.getSession().getLog());
					client.getFullLog();
		    	}
		    	else if(status == Session.SUCCESS) {
		    		System.out.println("Status: SUCCESS");
		    		client.getFullLog();
		    		break;
		    	}
		    	else if(status == Session.ERROR) {
		    		System.out.println("Status: ERROR");
		    		client.getFullLog();
		    		break;
		    	}
				else if(status == Session.STARTING) {
					System.out.println("Status: STARTING");
				}
		    	else {
		    		System.out.println("Status: None");
		    		break;
		    	}
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LivyException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

public BatchSample() {

	if(environment == "dev") {
		endpoint = "";
		username = "";
		password = "";
		storageaccount = "";
		containernmae = "test";
		jarname = "spark-examples_2.11-2.1.1.2.6.2.3-1.jar";
		inputfile = "sample.txt";
	}  else if (environment == "intqa") {
		endpoint = "";
		username = "";
		password = "";
		storageaccount = "";
		containernmae = "test";
		jarname = "spark-examples_2.11-2.1.1.2.6.2.3-1.jar";
		inputfile = "sample.txt";
	} else if (environment == "busqa") {
		endpoint = "";
		username = "";
		password = "";
		storageaccount = "";
		containernmae = "test";
		jarname = "spark-examples_2.11-2.1.1.2.6.2.3-1.jar";
		inputfile = "sample.txt";
	} else if (environment == "bizqa") {
		endpoint = "";
		username = "";
		password = "";
		storageaccount = "";
		containernmae = "test";
		jarname = "spark-examples_2.11-2.1.1.2.6.2.3-1.jar";
		inputfile = "sample.txt";
	}

		System.out.println("======== Environment ====== "+environment);

		String baseUri = "https://" + endpoint + AZUREHDINSIGHT_LIVY_URI;
		try {
			client = new LivyBatchClient(baseUri, username, password);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
