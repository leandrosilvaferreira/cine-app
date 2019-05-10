package com.cine.api.interceptor;

import com.cine.api.model.ErrorGenericList;
import com.cine.api.model.ErrorMessage;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.Serializable;
import java.util.stream.Collectors;

@Interceptor
@ExceptionHandler
public class ExceptionHandlerInterceptor implements Serializable {

	private static final long serialVersionUID = 926805010049284260L;

	@AroundInvoke
	public Object processRequest(final InvocationContext invocationContext) {

		try {
			return invocationContext.proceed();

		}
		catch (final WebApplicationException e) {

			final int status = e.getResponse().getStatus();
			final String errorJson = e.getResponse().readEntity(String.class);

			final Jsonb jsonb = JsonbBuilder.create();

			ErrorMessage errorMessage = jsonb.fromJson(errorJson, ErrorMessage.class);
			if(errorMessage.isValid()) {
				return Response
						.status(status)
						.entity(errorMessage)
						.build();
			}

			errorMessage = ErrorMessage
					.builder()
					.statusCode(status)
					.statusMessage(e.getMessage())
					.success(false)
					.build();

			final ErrorGenericList errorGenericList = jsonb.fromJson(errorJson, ErrorGenericList.class);
			if(errorGenericList.isValid()) {
				errorMessage = ErrorMessage
						.builder()
						.statusCode(status)
						.statusMessage(errorGenericList.getErrors().stream().collect(Collectors.joining(". ")))
						.success(false)
						.build();
			}

			return Response
					.status(status)
					.entity(errorMessage)
					.build();
		}
		catch (final Exception e) {

			e.printStackTrace();

			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity(ErrorMessage
							.builder()
							.statusCode(Status.INTERNAL_SERVER_ERROR.getStatusCode())
							.statusMessage(e.getMessage())
							.success(false)
							.build())
					.build();
		}
	}

}
