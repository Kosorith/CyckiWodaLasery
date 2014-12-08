package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Hint;

import java.util.List;

/**
 * Created by michal on 12/8/14.
 */
public class ChallengeCreationServiceImpl implements ChallengeCreationService {
    @Override
    public Challenge getEditedChallenge() {
        return null;
    }

    @Override
    public void setEditedChallenge(Challenge challenge) {

    }

    @Override
    public void initCreation() {

    }

    @Override
    public boolean saveChallenge() {
        return false;
    }

    @Override
    public List<Hint> getHintList() {
        return null;
    }

    @Override
    public void addHint(Hint hint) {

    }

    @Override
    public void removeHint(Hint hint) {

    }

    @Override
    public boolean preverifyChallenge() {
        return false;
    }
}
