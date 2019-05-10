package com.cine.api.cache;

import org.apache.commons.jcs.jcache.cdi.CacheResultInterceptor;

import javax.cache.annotation.CacheResult;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@JCache
@CacheResult
public class JCacheCacheResultInterceptor extends CacheResultInterceptor {

	private static final long serialVersionUID = 2674074386469303215L;

	@AroundInvoke
	public Object processRequest(final InvocationContext invocationContext) throws Throwable {

		return super.cache(invocationContext);
	}

}
