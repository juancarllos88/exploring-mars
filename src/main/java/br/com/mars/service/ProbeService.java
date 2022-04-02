package br.com.mars.service;

import java.util.UUID;

import br.com.mars.domain.Probe;
import br.com.mars.dto.MovementProbeDto;

public interface ProbeService extends BaseService<Probe> {

	Probe move(UUID probeId, MovementProbeDto movementProbeDto);

}
