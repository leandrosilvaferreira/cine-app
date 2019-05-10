package com.cine.api.cache;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.eviction.LFUEvictionPolicy;
import lombok.extern.java.Log;
import org.apache.commons.jcs.jcache.cdi.CDIJCacheHelper;
import org.apache.commons.jcs.jcache.cdi.MakeJCacheCDIInterceptorFriendly;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import static com.hazelcast.config.MaxSizeConfig.MaxSizePolicy.PER_NODE;

@ApplicationScoped
@Log
public class InMemoryCacheFactory {

	HazelcastInstance instance;

	@PostConstruct
	public void afterCreate() {

		final Config config = new Config();

		final LFUEvictionPolicy mapEvictionPolicy = new LFUEvictionPolicy();

		config.getMapConfig("default")
				.setTimeToLiveSeconds(5)
				.setMaxIdleSeconds(10)
				.setMapEvictionPolicy(mapEvictionPolicy)
				.getMaxSizeConfig().setMaxSizePolicy(PER_NODE)
				.setSize(10000);

		this.instance = Hazelcast.newHazelcastInstance(config);
	}

	@PreDestroy
	public void beforeDestroy() {

		if(this.instance != null) {
			this.instance.shutdown();
		}
	}

	@Produces
	@ApplicationScoped
	public HazelcastInstance getInstance() {

		return this.instance;
	}

	@Produces
	public MakeJCacheCDIInterceptorFriendly getMakeJCacheCDIInterceptorFriendly() {

		return new MakeJCacheCDIInterceptorFriendly();
	}

	@Produces
	public CDIJCacheHelper getCDIJCacheHelper() {

		return new CDIJCacheHelper();
	}


}
