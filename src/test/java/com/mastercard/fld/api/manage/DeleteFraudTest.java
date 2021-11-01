package com.mastercard.fld.api.manage;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.mastercard.fld.api.fld.ApiCallback;
import com.mastercard.fld.api.fld.ApiClient;
import com.mastercard.fld.api.fld.ApiException;
import com.mastercard.fld.api.fld.api.ConfirmedFraudManagementApi;
import com.mastercard.fld.api.fld.model.FraudDeleteAndConfirm;
import com.mastercard.fld.utility.RequestHelper;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@RunWith(MockitoJUnitRunner.class)
public class DeleteFraudTest {

	@InjectMocks
	@Spy
	DeleteFraud deleteFraudObj;

	@Mock
	RequestHelper helper;

	@Mock
	ConfirmedFraudManagementApi fraudApi;

	@Mock
	ApiClient apiclient;

	@Mock
	ApiCallback callback;

	@Mock
	Call call;

	FraudDeleteAndConfirm request;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		request = deleteFraudObj.createRequest();
	}
	
	@Test
	public void deleteFraud() throws ApiException, IOException {
		Response response = new Response.Builder().request(new Request.Builder().url("http://url.com").build())
				.protocol(Protocol.HTTP_2).code(200).message("")
				.body(ResponseBody.create(MediaType.parse("application/json"), "aaa")).build();

		when(helper.getCallback()).thenReturn(callback);
		when(helper.getClient()).thenReturn(apiclient);
		when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
		when(fraudApi.getApiClient()).thenReturn(apiclient);
		when(fraudApi.fraudStateCall(Mockito.any(), Mockito.any())).thenReturn(call);
		Mockito.doReturn(response).when(helper).apiCall(Mockito.any());
		deleteFraudObj.deleteFraud(request);
	}
	
	@Test
	public void deleteFraudFailuer() throws ApiException, IOException {
		Response response = new Response.Builder().request(new Request.Builder().url("http://url.com").build())
				.protocol(Protocol.HTTP_2).code(200).message("")
				.body(ResponseBody.create(MediaType.parse("application/json"), "aaa")).build();

		when(helper.getCallback()).thenReturn(callback);
		when(helper.getClient()).thenReturn(apiclient);
		when(apiclient.getBasePath()).thenReturn("https://sandbox.api.mastercard.com/fld/confirmed-frauds");
		when(fraudApi.getApiClient()).thenReturn(apiclient);
		when(fraudApi.fraudStateCall(Mockito.any(), Mockito.any())).thenReturn(call);
		Mockito.doThrow(new IOException()).when(helper).apiCall(Mockito.any());
		deleteFraudObj.deleteFraud(request);
	}

	@Test
	public void testCreateRequest() {
		assertNotNull(request);
	}
}
