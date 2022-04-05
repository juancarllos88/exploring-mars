package br.com.mars.domain.services;

import java.util.UUID;

import br.com.mars.domain.dto.MovementProbeDto;
import br.com.mars.domain.model.Probe;

public interface ProbeService extends BaseService<Probe> {

	Probe move(UUID probeId, MovementProbeDto movementProbeDto);

}
