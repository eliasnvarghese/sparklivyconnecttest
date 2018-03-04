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
package delsh.livy;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Convert json string to Java object and vise versa.
 */
public class JsonConverter {

	/**
	 * Convert json string to a Java object.
     * @param Java class
     * @param json string
     * @return Java object
     * @throws IOException
     */
	public static <T> T toObject(Class<T> cls, String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return (T) mapper.readValue(json, cls);
	}

	/**
	 * Convert Java object to json string.
	 * @param obj
	 * @return String
	 * @throws JsonProcessingException
	 */
	public static String toJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		return json;
	}
}

