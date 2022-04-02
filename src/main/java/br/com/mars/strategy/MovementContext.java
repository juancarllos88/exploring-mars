package br.com.mars.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import br.com.mars.domain.Probe;
import br.com.mars.enums.Movement;

@RequestScope
@Component
public class MovementContext {

	private MovementStrategy strategy;

	@Autowired
	private List<MovementStrategy> strategies;

	public void defineContext(Movement movement) {
		this.strategy = this.strategies.stream().filter(s -> movement.equals(s.getType())).findFirst().get();
	}
	
	public void doMovement(Probe probe) {
		strategy.doMovement(probe);
	}

}
