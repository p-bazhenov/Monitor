package monitor.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import monitor.domain.Statistic;

public interface StatsRepo extends JpaRepository<Statistic, Long>{

	public List<Statistic> findByProjectNameAndGameId(String projectName, Long gameId);
	
	public Statistic findFirstByProjectNameAndGameIdOrderByIdDesc(String projectName, Long gameId);

	public List<Statistic> findTop3ByProjectNameAndGameIdOrderByIdDesc(String projectName, Long userid);

	public List<Statistic> findByGameIdAndDatestamp(Long gameId, Date date);

}
