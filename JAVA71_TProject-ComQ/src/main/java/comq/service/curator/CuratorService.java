package comq.service.curator;

import comq.domain.Curator;

public interface CuratorService {
	
	// SELECT
	public Curator getCurator(int cur_id) throws Exception;
	
	// UPDATE
	public void updateCurator(Curator curator) throws Exception;
}
