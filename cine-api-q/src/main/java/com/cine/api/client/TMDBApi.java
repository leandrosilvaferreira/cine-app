package com.cine.api.client;

import com.cine.api.filter.AuthenticationRequestFilter;
import com.cine.api.filter.LoggingFilter;
import com.cine.api.model.GenreList;
import com.cine.api.model.Movie;
import com.cine.api.model.MovieSearchList;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient
@RegisterProvider(AuthenticationRequestFilter.class)
@RegisterProvider(LoggingFilter.class)
public interface TMDBApi {

	String TMDB_API_KEY_NAME = "api_key";

	String TMDB_API_KEY_VALUE = "1f54bd990f1cdfb230adb312546d765d";

	@GET
	@Path("/movie/latest")
	Movie getMovieLatest();

	@GET
	@Path("/movie/upcoming")
	MovieSearchList getMovieUpcoming(@QueryParam("page") Integer page);


	@GET
	@Path("/movie/{movie_id}")
	Movie getMovieById(@PathParam("movie_id") Integer movieId, @QueryParam(value = "append_to_response") String appendToResponse);

	@GET
	@Path("/search/movie")
	MovieSearchList getSearchMovie(@QueryParam("query") String query, @QueryParam("year") Integer year, @QueryParam("primary_release_year") Integer primaryReleaseYear, @QueryParam("page") Integer page);

	@GET
	@Path("/genre/movie/list")
	GenreList getGenreList();

}

