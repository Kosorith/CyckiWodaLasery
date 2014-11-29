package pl.p.lodz.ftims.server.controllerDataModel;

import java.util.List;

import pl.p.lodz.ftims.server.entities.Ranking;

public class RankingReply {

	private List<Ranking> ranking;

	public List<Ranking> getRanking() {
		return ranking;
	}

	public void setRanking(List<Ranking> ranking) {
		this.ranking = ranking;
	}
	
}
