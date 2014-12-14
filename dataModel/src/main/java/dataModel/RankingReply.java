package dataModel;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RankingReply")
@XmlAccessorType(XmlAccessType.FIELD)
public class RankingReply {
	
    @XmlElementWrapper(name="rankingList")
    @XmlElement(name="ranking")
	List<Ranking> ranking;

	public RankingReply(List<Ranking> ranking) {
		this.ranking = ranking;
	}

	public RankingReply() {
		super();
	}

	public List<Ranking> getRanking() {
		return ranking;
	}

	public void setRanking(List<Ranking> ranking) {
		this.ranking = ranking;
	}
}
