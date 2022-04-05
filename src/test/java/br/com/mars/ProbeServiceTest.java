package br.com.mars;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import br.com.mars.domain.dto.MovementProbeDto;
import br.com.mars.domain.model.Field;
import br.com.mars.domain.model.Probe;
import br.com.mars.domain.services.FieldService;
import br.com.mars.domain.services.ProbeService;
import br.com.mars.domain.shared.Direction;
import br.com.mars.domain.shared.Position;
import br.com.mars.infrastructure.exceptions.EntityModelNotFoundException;
import br.com.mars.infrastructure.exceptions.InstructionInvalidException;
import br.com.mars.infrastructure.exceptions.SpaceAlreadyOccupiedException;
import br.com.mars.infrastructure.repositories.FieldRepository;
import br.com.mars.infrastructure.repositories.ProbeRepository;

@ActiveProfiles("test")
@SpringBootTest
public class ProbeServiceTest {

	@Autowired
	private FieldService fieldService;

	@Autowired
	private ProbeService probeService;

	@Autowired
	private FieldRepository fieldRepository;

	@Autowired
	private ProbeRepository probeRepository;

	@BeforeEach
	public void tearDown() {
		probeRepository.deleteAll();
		fieldRepository.deleteAll();
	}

	@Test
	public void findOneProbe() throws Exception {
		Field field = fieldService.save(generateField());
		Probe probeSave = probeService.save(generateProbe(field, 1, 2, Direction.NORTH));
		Probe probeFind = probeService.findOne(probeSave.getId());
		Assertions.assertEquals(probeSave.getId(), probeFind.getId());
	}

	@Test
	public void probeNotFound() throws Exception {
		try {
			probeService.findOne(UUID.fromString("6309f0d3-5357-4201-b252-77889110fe50"));
		} catch (RuntimeException e) {
			Assertions.assertTrue(e.getClass().equals(EntityModelNotFoundException.class));
		}
	}

	@Test
	public void createProbe() throws Exception {
		Field field = fieldService.save(generateField());
		Probe probe = probeService.save(generateProbe(field, 1, 2, Direction.NORTH));
		Assertions.assertNotNull(probe.getId());
	}

	@Test
	public void dontCreateProbeBecauseThereIsOtherProbeInSpace() throws Exception {
		try {
			Field field = fieldService.save(generateField());
			probeService.save(generateProbe(field, 1, 2, Direction.NORTH));
			probeService.save(generateProbe(field, 1, 2, Direction.NORTH));
		} catch (RuntimeException e) {
			Assertions.assertTrue(e.getClass().equals(SpaceAlreadyOccupiedException.class));
		}
	}

	@Test
	public void dontMoveProbeBecauseThereIsWrongInstruction() throws Exception {
		try {
			Field field = fieldService.save(generateField());
			Probe probe = probeService.save(generateProbe(field, 1, 2, Direction.NORTH));
			MovementProbeDto movement = new MovementProbeDto();
			movement.setMovement("LMALMLMLMM");
			probeService.move(probe.getId(), movement);
		} catch (RuntimeException e) {
			Assertions.assertTrue(e.getClass().equals(InstructionInvalidException.class));
		}
	}

	@Test
	public void moveProbe() throws Exception {
		Field field = fieldService.save(generateField());
		Probe probe = probeService.save(generateProbe(field, 1, 2, Direction.NORTH));
		MovementProbeDto movement = new MovementProbeDto();
		movement.setMovement("LMLMLMLMM");
		probe = probeService.move(probe.getId(), movement);
		Assertions.assertEquals(probe.getDirection(), Direction.NORTH);
		Assertions.assertEquals(probe.getPosition().getX(), 1);
		Assertions.assertEquals(probe.getPosition().getY(), 3);
	}

	@Test
	public void moveProbeTurnLeftDirectionBecauseThereIsOtherProbeInSpace() throws Exception {
		Field field = fieldService.save(generateField());
		Probe firstProbe = probeService.save(generateProbe(field, 1, 2, Direction.NORTH));
		probeService.save(generateProbe(field, 1, 3, Direction.NORTH));
		MovementProbeDto movement = new MovementProbeDto();
		movement.setMovement("M");
		Probe probe = probeService.move(firstProbe.getId(), movement);
		Assertions.assertEquals(probe.getDirection(), Direction.WEST);
		Assertions.assertEquals(probe.getPosition().getX(), 0);
		Assertions.assertEquals(probe.getPosition().getY(), 2);
	}

	@Test
	public void findAllProbes() throws Exception {
		Field field = fieldService.save(generateField());
		probeService.save(generateProbe(field, 1, 2, Direction.NORTH));
		Page<Probe> probes = probeService.findAll(PageRequest.of(0, 20));
		Assertions.assertEquals(probes.getContent().size(), 1);
		Assertions.assertEquals(probes.getNumberOfElements(), 1);
		Assertions.assertEquals(probes.getTotalElements(), 1);
		Assertions.assertEquals(probes.getTotalPages(), 1);
	}

	private Field generateField() {
		Field field = new Field();
		field.setAxisX(5);
		field.setAxisY(5);
		return field;
	}

	private Probe generateProbe(Field field, Integer positionX, Integer positionY, Direction direction) {
		Probe probe = new Probe();
		probe.setField(field);
		probe.setPosition(new Position(positionX, positionY));
		probe.setDirection(direction);
		return probe;
	}

}
