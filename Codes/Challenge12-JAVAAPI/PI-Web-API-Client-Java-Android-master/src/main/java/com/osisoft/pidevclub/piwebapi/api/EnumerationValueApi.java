/*
 *
 * Copyright 2018 OSIsoft, LLC
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   <http://www.apache.org/licenses/LICENSE-2.0>
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.osisoft.pidevclub.piwebapi.api;
import com.osisoft.pidevclub.piwebapi.ApiCallback;
import com.osisoft.pidevclub.piwebapi.ApiClient;
import com.osisoft.pidevclub.piwebapi.ApiException;
import com.osisoft.pidevclub.piwebapi.ApiResponse;
import com.osisoft.pidevclub.piwebapi.Configuration;
import com.osisoft.pidevclub.piwebapi.Pair;
import com.osisoft.pidevclub.piwebapi.ProgressRequestBody;
import com.osisoft.pidevclub.piwebapi.ProgressResponseBody;
import com.osisoft.pidevclub.piwebapi.models.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumerationValueApi {
	private ApiClient apiClient;
	public EnumerationValueApi(ApiClient apiClient){
		this.apiClient = apiClient;
	}
	public ApiClient getApiClient() {
		return apiClient;
	}
	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}
	/**
	 * Retrieve an enumeration value by path. 
	 *
	 */
	public PIEnumerationValue getByPath(String path, String selectedFields, String webIdType) throws ApiException {
		ApiResponse<PIEnumerationValue> resp = getByPathWithHttpInfo(path, selectedFields, webIdType);
		return resp.getData();
	}

	/**
	 * Retrieve an enumeration value by path. (with HTTP information)
	 *
	 */
	public ApiResponse<PIEnumerationValue> getByPathWithHttpInfo(String path, String selectedFields, String webIdType) throws ApiException {
		okhttp3.Call call = getByPathCall(path, selectedFields, webIdType,null,null);
		Type localVarReturnType = new TypeToken<PIEnumerationValue>(){}.getType();
		return apiClient.execute(call, localVarReturnType);
	}

	/**
	 * Retrieve an enumeration value by path. (asynchronously)
	 *
	 */
	public okhttp3.Call getByPathAsync(String path, String selectedFields, String webIdType, final ApiCallback<PIEnumerationValue> callback) throws ApiException {
		ProgressResponseBody.ProgressListener progressListener = null;
		ProgressRequestBody.ProgressRequestListener progressRequestListener = null;
		if (callback != null)
		{
			progressListener = new ProgressResponseBody.ProgressListener() {
				@Override
				public void update(long bytesRead, long contentLength, boolean done)
				{
					callback.onDownloadProgress(bytesRead, contentLength, done);
				}
			};
			progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
				@Override
				public void onRequestProgress(long bytesWritten, long contentLength, boolean done)
				{
					callback.onUploadProgress(bytesWritten, contentLength, done);
				}
			};
		}
		okhttp3.Call call = getByPathCall(path, selectedFields, webIdType, progressListener, progressRequestListener);
		apiClient.executeAsync(call, callback);
		return call;
	}

	private okhttp3.Call getByPathCall(String path, String selectedFields, String webIdType, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		Object localVarPostBody =  null;
		// verify the required parameter 'path' is set
		if (path == null)
			throw new ApiException("Missing required parameter 'path'");
		String localVarPath = "/enumerationvalues";
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();
		List<Pair> localVarQueryParams = new ArrayList<Pair>();

		final String[] localVarAccepts = {"application/json", "text/json", "text/html", "application/x-ms-application"};

		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

		final String[] localVarContentTypes = {"application/json", "text/json" };

		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
		localVarHeaderParams.put("Content-Type", localVarContentType);

		if (path != null)
			localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "path", path));
		if (selectedFields != null)
			localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "selectedFields", selectedFields));
		if (webIdType != null)
			localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "webIdType", webIdType));
		if (progressListener != null)
		{
			apiClient.getHttpClient().networkInterceptors().add(new okhttp3.Interceptor() {
			@Override
			public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
				okhttp3.Response originalResponse = chain.proceed(chain.request());
				return originalResponse.newBuilder()
				.body(new ProgressResponseBody(originalResponse.body(), progressListener))
				.build();
				}
			});
		}
		String[] localVarAuthNames = new String[] {"Basic" };
		return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
	}

	/**
	 * Retrieve an enumeration value mapping 
	 *
	 */
	public PIEnumerationValue get(String webId, String selectedFields, String webIdType) throws ApiException {
		ApiResponse<PIEnumerationValue> resp = getWithHttpInfo(webId, selectedFields, webIdType);
		return resp.getData();
	}

	/**
	 * Retrieve an enumeration value mapping (with HTTP information)
	 *
	 */
	public ApiResponse<PIEnumerationValue> getWithHttpInfo(String webId, String selectedFields, String webIdType) throws ApiException {
		okhttp3.Call call = getCall(webId, selectedFields, webIdType,null,null);
		Type localVarReturnType = new TypeToken<PIEnumerationValue>(){}.getType();
		return apiClient.execute(call, localVarReturnType);
	}

	/**
	 * Retrieve an enumeration value mapping (asynchronously)
	 *
	 */
	public okhttp3.Call getAsync(String webId, String selectedFields, String webIdType, final ApiCallback<PIEnumerationValue> callback) throws ApiException {
		ProgressResponseBody.ProgressListener progressListener = null;
		ProgressRequestBody.ProgressRequestListener progressRequestListener = null;
		if (callback != null)
		{
			progressListener = new ProgressResponseBody.ProgressListener() {
				@Override
				public void update(long bytesRead, long contentLength, boolean done)
				{
					callback.onDownloadProgress(bytesRead, contentLength, done);
				}
			};
			progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
				@Override
				public void onRequestProgress(long bytesWritten, long contentLength, boolean done)
				{
					callback.onUploadProgress(bytesWritten, contentLength, done);
				}
			};
		}
		okhttp3.Call call = getCall(webId, selectedFields, webIdType, progressListener, progressRequestListener);
		apiClient.executeAsync(call, callback);
		return call;
	}

	private okhttp3.Call getCall(String webId, String selectedFields, String webIdType, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		Object localVarPostBody =  null;
		// verify the required parameter 'webId' is set
		if (webId == null)
			throw new ApiException("Missing required parameter 'webId'");
		String localVarPath = "/enumerationvalues/{webId}";
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();
		List<Pair> localVarQueryParams = new ArrayList<Pair>();

		final String[] localVarAccepts = {"application/json", "text/json", "text/html", "application/x-ms-application"};

		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

		final String[] localVarContentTypes = {"application/json", "text/json" };

		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
		localVarHeaderParams.put("Content-Type", localVarContentType);

		localVarPath = localVarPath.replaceAll("\\{webId\\}", apiClient.escapeString(webId.toString()));
		if (selectedFields != null)
			localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "selectedFields", selectedFields));
		if (webIdType != null)
			localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "webIdType", webIdType));
		if (progressListener != null)
		{
			apiClient.getHttpClient().networkInterceptors().add(new okhttp3.Interceptor() {
			@Override
			public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
				okhttp3.Response originalResponse = chain.proceed(chain.request());
				return originalResponse.newBuilder()
				.body(new ProgressResponseBody(originalResponse.body(), progressListener))
				.build();
				}
			});
		}
		String[] localVarAuthNames = new String[] {"Basic" };
		return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
	}

	/**
	 * Update an enumeration value by replacing items in its definition. 
	 *
	 */
	public void updateEnumerationValue(String webId, PIEnumerationValue enumerationValue) throws ApiException {
		updateEnumerationValueWithHttpInfo(webId, enumerationValue);
	}

	/**
	 * Update an enumeration value by replacing items in its definition. (with HTTP information)
	 *
	 */
	public ApiResponse<Void> updateEnumerationValueWithHttpInfo(String webId, PIEnumerationValue enumerationValue) throws ApiException {
		okhttp3.Call call = updateEnumerationValueCall(webId, enumerationValue,null,null);
		return apiClient.execute(call);
	}

	/**
	 * Update an enumeration value by replacing items in its definition. (asynchronously)
	 *
	 */
	public okhttp3.Call updateEnumerationValueAsync(String webId, PIEnumerationValue enumerationValue, final ApiCallback<Void> callback) throws ApiException {
		ProgressResponseBody.ProgressListener progressListener = null;
		ProgressRequestBody.ProgressRequestListener progressRequestListener = null;
		if (callback != null)
		{
			progressListener = new ProgressResponseBody.ProgressListener() {
				@Override
				public void update(long bytesRead, long contentLength, boolean done)
				{
					callback.onDownloadProgress(bytesRead, contentLength, done);
				}
			};
			progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
				@Override
				public void onRequestProgress(long bytesWritten, long contentLength, boolean done)
				{
					callback.onUploadProgress(bytesWritten, contentLength, done);
				}
			};
		}
		okhttp3.Call call = updateEnumerationValueCall(webId, enumerationValue, progressListener, progressRequestListener);
		apiClient.executeAsync(call, callback);
		return call;
	}

	private okhttp3.Call updateEnumerationValueCall(String webId, PIEnumerationValue enumerationValue, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		Object localVarPostBody =  null;
		// verify the required parameter 'webId' is set
		if (webId == null)
			throw new ApiException("Missing required parameter 'webId'");
		// verify the required parameter 'enumerationValue' is set
		if (enumerationValue == null)
			throw new ApiException("Missing required parameter 'enumerationValue'");
		String localVarPath = "/enumerationvalues/{webId}";
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();
		List<Pair> localVarQueryParams = new ArrayList<Pair>();

		final String[] localVarAccepts = {"application/json", "text/json", "text/html", "application/x-ms-application"};

		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

		final String[] localVarContentTypes = {"application/json", "text/json" };

		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
		localVarHeaderParams.put("Content-Type", localVarContentType);

		localVarPath = localVarPath.replaceAll("\\{webId\\}", apiClient.escapeString(webId.toString()));
		localVarPostBody =  enumerationValue;
		if (progressListener != null)
		{
			apiClient.getHttpClient().networkInterceptors().add(new okhttp3.Interceptor() {
			@Override
			public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
				okhttp3.Response originalResponse = chain.proceed(chain.request());
				return originalResponse.newBuilder()
				.body(new ProgressResponseBody(originalResponse.body(), progressListener))
				.build();
				}
			});
		}
		String[] localVarAuthNames = new String[] {"Basic" };
		return apiClient.buildCall(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
	}

	/**
	 * Delete an enumeration value from an enumeration set. 
	 *
	 */
	public void deleteEnumerationValue(String webId) throws ApiException {
		deleteEnumerationValueWithHttpInfo(webId);
	}

	/**
	 * Delete an enumeration value from an enumeration set. (with HTTP information)
	 *
	 */
	public ApiResponse<Void> deleteEnumerationValueWithHttpInfo(String webId) throws ApiException {
		okhttp3.Call call = deleteEnumerationValueCall(webId,null,null);
		return apiClient.execute(call);
	}

	/**
	 * Delete an enumeration value from an enumeration set. (asynchronously)
	 *
	 */
	public okhttp3.Call deleteEnumerationValueAsync(String webId, final ApiCallback<Void> callback) throws ApiException {
		ProgressResponseBody.ProgressListener progressListener = null;
		ProgressRequestBody.ProgressRequestListener progressRequestListener = null;
		if (callback != null)
		{
			progressListener = new ProgressResponseBody.ProgressListener() {
				@Override
				public void update(long bytesRead, long contentLength, boolean done)
				{
					callback.onDownloadProgress(bytesRead, contentLength, done);
				}
			};
			progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
				@Override
				public void onRequestProgress(long bytesWritten, long contentLength, boolean done)
				{
					callback.onUploadProgress(bytesWritten, contentLength, done);
				}
			};
		}
		okhttp3.Call call = deleteEnumerationValueCall(webId, progressListener, progressRequestListener);
		apiClient.executeAsync(call, callback);
		return call;
	}

	private okhttp3.Call deleteEnumerationValueCall(String webId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
		Object localVarPostBody =  null;
		// verify the required parameter 'webId' is set
		if (webId == null)
			throw new ApiException("Missing required parameter 'webId'");
		String localVarPath = "/enumerationvalues/{webId}";
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();
		List<Pair> localVarQueryParams = new ArrayList<Pair>();

		final String[] localVarAccepts = {"application/json", "text/json", "text/html", "application/x-ms-application"};

		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

		final String[] localVarContentTypes = {"application/json", "text/json" };

		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
		localVarHeaderParams.put("Content-Type", localVarContentType);

		localVarPath = localVarPath.replaceAll("\\{webId\\}", apiClient.escapeString(webId.toString()));
		if (progressListener != null)
		{
			apiClient.getHttpClient().networkInterceptors().add(new okhttp3.Interceptor() {
			@Override
			public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
				okhttp3.Response originalResponse = chain.proceed(chain.request());
				return originalResponse.newBuilder()
				.body(new ProgressResponseBody(originalResponse.body(), progressListener))
				.build();
				}
			});
		}
		String[] localVarAuthNames = new String[] {"Basic" };
		return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
	}

}
