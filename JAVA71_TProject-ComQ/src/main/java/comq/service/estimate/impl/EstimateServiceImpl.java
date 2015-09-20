package comq.service.estimate.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import comq.domain.Estimate;
import comq.domain.ProdList;
import comq.service.estimate.EstimateDao;
import comq.service.estimate.EstimateService;

@Service("estimateServiceImpl")
public class EstimateServiceImpl implements EstimateService {

	@Autowired
	@Qualifier("estimateDaoImpl")
	private EstimateDao estimateDao;
	public void setEsimateDao(EstimateDao estimateDao) {
		this.estimateDao = estimateDao;
	}
	
	public EstimateServiceImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public int addEstimate(Estimate estimate) throws Exception {
		
		return estimateDao.addEstimate(estimate);
	}

	@Override
	public int addProdList(ProdList prodlist) throws Exception {

		return estimateDao.addProdList(prodlist);
	}

	@Override
	public List<Estimate> getEstimateList(int userId, int startNo, int endNo) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", userId);
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		return estimateDao.getEstimateList(map);
	}

	@Override
	public List<ProdList> getProdList(int estId) throws Exception {

		return estimateDao.getProdList(estId);
	}

	@Override
	public Estimate getLastEstimate() throws Exception {
		return estimateDao.getLastEstimate();
	}
	
	@Override
	public int updateEstimateName(Estimate estimate) throws Exception {

		return estimateDao.updateEstimateName(estimate);
	}

	@Override
	public int updateProd(ProdList prodlist) throws Exception {

		return estimateDao.updateProd(prodlist);
	}

	@Override
	public int deleteEstimate(int estId) throws Exception {

		return estimateDao.deleteEstimate(estId);
	}

	@Override
	public int deleteProd(int prolId) throws Exception {

		return estimateDao.deleteProd(prolId);
	}

}
