package com.cine.api.filter;

import lombok.extern.java.Log;
import org.glassfish.jersey.message.MessageUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Based on org.glassfish.jersey.filter.LoggingFilter
 * <p>
 * Credits:
 *
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 * @author Martin Matula
 */
@Log
public class LoggingFilter implements ContainerRequestFilter, ClientRequestFilter, ContainerResponseFilter,
                                      ClientResponseFilter, WriterInterceptor {

	private static final String NOTIFICATION_PREFIX = "* ";

	private static final String REQUEST_PREFIX = "> ";

	private static final String RESPONSE_PREFIX = "< ";

	private static final String ENTITY_LOGGER_PROPERTY = LoggingFilter.class.getName() + ".entityLogger";

	private static final String LOGGING_ID_PROPERTY = LoggingFilter.class.getName() + ".id";

	private static final Comparator<Map.Entry<String, List<String>>> COMPARATOR = (o1, o2) -> o1.getKey().compareToIgnoreCase(o2.getKey());

	private static final int DEFAULT_MAX_ENTITY_SIZE = 8 * 1024;

	private final AtomicLong _id = new AtomicLong(0);

	private final int maxEntitySize;

	public LoggingFilter() {

		this.maxEntitySize = LoggingFilter.DEFAULT_MAX_ENTITY_SIZE;
	}


	private void log(final StringBuilder b) {

		LoggingFilter.log.info(b.toString());
	}

	private StringBuilder prefixId(final StringBuilder b, final long id) {

		b.append(id).append(" ");
		return b;
	}

	private void printRequestLine(final StringBuilder b, final String note, final long id, final String method, final URI uri) {

		this.prefixId(b, id).append(LoggingFilter.NOTIFICATION_PREFIX)
				.append(note)
				.append(" on thread ").append(Thread.currentThread().getName())
				.append("\n");
		this.prefixId(b, id).append(LoggingFilter.REQUEST_PREFIX).append(method).append(" ")
				.append(uri.toASCIIString()).append("\n");
	}

	private void printResponseLine(final StringBuilder b, final String note, final long id, final int status) {

		this.prefixId(b, id).append(LoggingFilter.NOTIFICATION_PREFIX)
				.append(note)
				.append(" on thread ").append(Thread.currentThread().getName()).append("\n");
		this.prefixId(b, id).append(LoggingFilter.RESPONSE_PREFIX)
				.append(status)
				.append("\n");
	}

	private void printPrefixedHeaders(final StringBuilder b,
	                                  final long id,
	                                  final String prefix,
	                                  final MultivaluedMap<String, String> headers) {

		for (final Map.Entry<String, List<String>> headerEntry : this.getSortedHeaders(headers.entrySet())) {
			final List<?> val = headerEntry.getValue();
			final String header = headerEntry.getKey();

			if(val.size() == 1) {
				this.prefixId(b, id).append(prefix).append(header).append(": ").append(val.get(0)).append("\n");
			}
			else {
				final StringBuilder sb = new StringBuilder();
				boolean add = false;
				for (final Object s : val) {
					if(add) {
						sb.append(',');
					}
					add = true;
					sb.append(s);
				}
				this.prefixId(b, id).append(prefix).append(header).append(": ").append(sb.toString()).append("\n");
			}
		}
	}

	private Set<Map.Entry<String, List<String>>> getSortedHeaders(final Set<Map.Entry<String, List<String>>> headers) {

		final TreeSet<Map.Entry<String, List<String>>> sortedHeaders = new TreeSet<>(LoggingFilter.COMPARATOR);
		sortedHeaders.addAll(headers);
		return sortedHeaders;
	}

	private InputStream logInboundEntity(final StringBuilder b, InputStream stream, final Charset charset) throws IOException {

		if(!stream.markSupported()) {
			stream = new BufferedInputStream(stream);
		}
		stream.mark(this.maxEntitySize + 1);
		final byte[] entity = new byte[this.maxEntitySize + 1];
		final int entitySize = stream.read(entity);
		b.append(new String(entity, 0, Math.min(entitySize, this.maxEntitySize), charset));
		if(entitySize > this.maxEntitySize) {
			b.append("...more...");
		}
		b.append('\n');
		stream.reset();
		return stream;
	}

	@Override
	public void filter(final ClientRequestContext context) throws IOException {

		final long id = this._id.incrementAndGet();
		context.setProperty(LoggingFilter.LOGGING_ID_PROPERTY, id);

		final StringBuilder b = new StringBuilder();

		this.printRequestLine(b, "Sending client request", id, context.getMethod(), context.getUri());
		this.printPrefixedHeaders(b, id, LoggingFilter.REQUEST_PREFIX, context.getStringHeaders());

		if(context.hasEntity()) {
			final OutputStream stream = new LoggingFilter.LoggingStream(b, context.getEntityStream());
			context.setEntityStream(stream);
			context.setProperty(LoggingFilter.ENTITY_LOGGER_PROPERTY, stream);
			// not calling log(b) here - it will be called by the interceptor
		}
		else {
			this.log(b);
		}
	}

	@Override
	public void filter(final ClientRequestContext requestContext, final ClientResponseContext responseContext)
	throws IOException {

		final Object requestId = requestContext.getProperty(LoggingFilter.LOGGING_ID_PROPERTY);
		final long id = requestId != null ? (Long) requestId : this._id.incrementAndGet();

		final StringBuilder b = new StringBuilder();

		this.printResponseLine(b, "Client response received", id, responseContext.getStatus());
		this.printPrefixedHeaders(b, id, LoggingFilter.RESPONSE_PREFIX, responseContext.getHeaders());

		if(responseContext.hasEntity()) {
			responseContext.setEntityStream(this.logInboundEntity(b, responseContext.getEntityStream(),
					MessageUtils.getCharset(responseContext.getMediaType())));
		}

		this.log(b);
	}

	@Override
	public void filter(final ContainerRequestContext context) throws IOException {

		final long id = this._id.incrementAndGet();
		context.setProperty(LoggingFilter.LOGGING_ID_PROPERTY, id);

		final StringBuilder b = new StringBuilder();

		this.printRequestLine(b, "Server has received a request", id, context.getMethod(), context.getUriInfo().getRequestUri());
		this.printPrefixedHeaders(b, id, LoggingFilter.REQUEST_PREFIX, context.getHeaders());

		if(context.hasEntity()) {
			context.setEntityStream(
					this.logInboundEntity(b, context.getEntityStream(), MessageUtils.getCharset(context.getMediaType())));
		}

		this.log(b);
	}

	@Override
	public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext)
	throws IOException {

		final Object requestId = requestContext.getProperty(LoggingFilter.LOGGING_ID_PROPERTY);
		final long id = requestId != null ? (Long) requestId : this._id.incrementAndGet();

		final StringBuilder b = new StringBuilder();

		this.printResponseLine(b, "Server responded with a response", id, responseContext.getStatus());
		this.printPrefixedHeaders(b, id, LoggingFilter.RESPONSE_PREFIX, responseContext.getStringHeaders());

		if(responseContext.hasEntity()) {
			final OutputStream stream = new LoggingFilter.LoggingStream(b, responseContext.getEntityStream());
			responseContext.setEntityStream(stream);
			requestContext.setProperty(LoggingFilter.ENTITY_LOGGER_PROPERTY, stream);
			// not calling log(b) here - it will be called by the interceptor
		}
		else {
			this.log(b);
		}
	}

	@Override
	public void aroundWriteTo(final WriterInterceptorContext writerInterceptorContext)
	throws IOException, WebApplicationException {

		final LoggingFilter.LoggingStream stream = (LoggingFilter.LoggingStream) writerInterceptorContext.getProperty(LoggingFilter.ENTITY_LOGGER_PROPERTY);
		writerInterceptorContext.proceed();
		if(stream != null) {
			this.log(stream.getStringBuilder(MessageUtils.getCharset(writerInterceptorContext.getMediaType())));
		}
	}

	private class LoggingStream extends FilterOutputStream {

		private final StringBuilder b;

		private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

		LoggingStream(final StringBuilder b, final OutputStream inner) {

			super(inner);

			this.b = b;
		}

		StringBuilder getStringBuilder(final Charset charset) {
			// write entity to the builder
			final byte[] entity = this.baos.toByteArray();

			this.b.append(new String(entity, 0, Math.min(entity.length, LoggingFilter.this.maxEntitySize), charset));
			if(entity.length > LoggingFilter.this.maxEntitySize) {
				this.b.append("...more...");
			}
			this.b.append('\n');

			return this.b;
		}

		@Override
		public void write(final int i) throws IOException {

			if(this.baos.size() <= LoggingFilter.this.maxEntitySize) {
				this.baos.write(i);
			}
			this.out.write(i);
		}

	}

}
