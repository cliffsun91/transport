package com.cliffsun.transport;

import com.cliffsun.transport.tube.MediocreTubeStationRepository;
import com.cliffsun.transport.tube.TubeResource;
import com.cliffsun.transport.tube.TubeStationRepository;
import com.google.inject.AbstractModule;

public class ResourceModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(TransportResource.class);
		bind(TubeResource.class);
		bind(TubeStationRepository.class).to(MediocreTubeStationRepository.class).asEagerSingleton();
	}

}
