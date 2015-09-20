package comq.service.estimate.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import comq.domain.Estimate;
import comq.domain.ProdList;
import comq.service.estimate.EstimateDao;

@Repository("estimateDaoImpl")
public class EstimateDaoImpl implements EstimateDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public EstimateDaoImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public int addEstimate(Estimate estimate) throws Exception {

		return sqlSession.insert("EstimateMapper.insertEstimate", estimate);
	}

	@Override
	public int addProdList(ProdList prodlist) throws Exception {

		return sqlSession.insert("EstimateMapper.insertProdList", prodlist);
	}

	@Override
	public List<Estimate> getEstimateList(Map<String, Integer> map) throws Exception {

		return sqlSession.selectList("EstimateMapper.getEstimateList", map);
	}

	@Override
	public List<ProdList> getProdList(int estId) throws Exception {

		return  sqlSession.selectList("EstimateMapper.getProdList", estId);
	}

	@Override
	public Estimate getLastEstimate() throws Exception {
		return  sqlSession.selectOne("EstimateMapper.getLastEstimate");
	}
	
	@Override
	public int updateEstimateName(Estimate estimate) throws Exception {

		return sqlSession.update("EstimateMapper.updateEstimate", estimate);
	}

	@Override
	public int updateProd(ProdList prodlist) throws Exception {

		return sqlSession.update("EstimateMapper.updateProdList", prodlist);
	}

	@Override
	public int deleteEstimate(int estId) throws Exception {

		return sqlSession.update("EstimateMapper.deleteEstimate", estId);
	}

	@Override
	public int deleteProd(int prolId) throws Exception {

		return sqlSession.update("EstimateMapper.deleteProd", prolId);
	}

}
