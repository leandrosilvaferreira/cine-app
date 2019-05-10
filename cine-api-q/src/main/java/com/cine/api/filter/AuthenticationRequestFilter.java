package com.cine.api.filter;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;

import static com.cine.api.client.TMDBApi.TMDB_API_KEY_NAME;
import static com.cine.api.client.TMDBApi.TMDB_API_KEY_VALUE;

public class AuthenticationRequestFilter implements ClientRequestFilter {

	@Override
	public void filter(final ClientRequestContext clientRequestContext) {

		clientRequestContext.setUri(UriBuilder.fromUri(clientRequestContext.getUri()).queryParam(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE).build());
	}

}
