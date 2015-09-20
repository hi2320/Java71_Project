package comq.service.estimate;

import java.util.List;

import comq.domain.Estimate;
import comq.domain.ProdList;

public interface EstimateService {
	
	//INSERT
	public int addEstimate(Estimate estimate) throws Exception;
	public int addProdList(ProdList prodlist) throws Exception;
	
	//SELECT
	public List<Estimate> getEstimateList(int userId, int startNo, int endNo) throws Exception;
	public List<ProdList> getProdList(int estId) throws Exception;
	public Estimate getLastEstimate() throws Exception;
	
	//UPDATE
	public int updateEstimateName(Estimate estimate) throws Exception;
	public int updateProd(ProdList prodlist) throws Exception;
	
	//DELETE
	public int deleteEstimate(int estId) throws Exception;
	public int deleteProd(int prolId) throws Exception;
}
