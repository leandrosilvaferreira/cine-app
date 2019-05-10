package com.cine.api.endpoint;

import com.cine.api.interceptor.ExceptionHandler;
import com.cine.api.model.ErrorMessage;
import com.cine.api.model.GenreList;
import com.cine.api.model.Movie;
import com.cine.api.model.MovieSearchList;
import com.cine.api.service.MovieService;
import lombok.extern.java.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.opentracing.Traced;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/movie")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ExceptionHandler
@Traced
@Log
public class MovieEndpoint {

	@Inject
	MovieService service;

	@GET
	@Path("/latest")
	@Operation(
			summary = "Get latest movie",
			description = "Get the most newly created movie. This is a live response and will continuously change.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))),
			@APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "404", description = "No movies found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	public Response getMovieLatest() {

		return Response.ok(this.service.getMovieLatest()).build();
	}


	@GET
	@Path("/upcoming")
	@Operation(summary = "Get Upcoming",
	           description = "Get a list of upcoming movies in theatres. This is a release type query that looks for all movies that have a release type of 2 or 3 within the specified date range.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieSearchList.class))),
			@APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "404", description = "No movies found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	public Response getMovieUpcoming(@Parameter(name = "page", required = false, description = "Page number") @QueryParam("page") @DefaultValue("1") final Integer page) {

		return Response.ok(this.service.getMovieUpcoming(page)).build();

	}

	@GET
	@Path("/search")
	@Operation(
			summary = "Search Movies",
			description = "Search Movies by keyword")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieSearchList.class))),
			@APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "404", description = "No movies found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	public Response getSearchMovie(@Parameter(name = "query", required = true, description = "Keyword") @QueryParam("query") @NotNull final String query,
	                               @Parameter(name = "year", required = false, description = "Year") @QueryParam("year") final Integer year,
	                               @Parameter(name = "primary_release_year", required = false, description = "Primary Release Year") @QueryParam("primary_release_year") final Integer primaryReleaseYear,
	                               @Parameter(name = "page", required = false, description = "Page number") @QueryParam("page") @DefaultValue("1") final Integer page) {

		return Response.ok(this.service.getSearchMovie(query, year, primaryReleaseYear, page)).build();
	}


	@GET
	@Path("/{movie_id}")
	@Operation(
			summary = "Get Details",
			description = "Get the primary information about a movie.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))),
			@APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "404", description = "No movies found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	public Response getMovieById(@Parameter(name = "movie_id", required = true, description = "Movie Id") @PathParam("movie_id") @NotNull final Integer movieId,
	                             @Parameter(name = "append_video_to_response", required = false, description = "Append Video to Response") @QueryParam(value = "append_video_to_response") @DefaultValue("false") final boolean appendVideoToResponse) {

		return Response.ok(this.service.getMovieById(movieId, appendVideoToResponse)).build();
	}


	@GET
	@Path("/genre/movie/list")
	@Operation(
			summary = "Get the list of genres",
			description = "Get the list of official genres for movies.")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreList.class))),
			@APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "404", description = "No movies found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
			@APIResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	public Response getGenreList() {

		return Response.ok(this.service.getGenreList()).build();
	}

}