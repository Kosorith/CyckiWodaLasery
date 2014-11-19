package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Hint;

import java.util.List;

/**
 * Created by michal on 11/19/14.
 */
public interface ChallengeCreationService {

    Challenge getEditedChallenge();

    void setEditedChallenge(Challenge challenge);

    void initCreation();

    boolean saveChallenge();

    List<Hint> getHintList();

    void addHint(Hint hint);

    void removeHint(Hint hint);

    boolean preverifyChallenge();

}
