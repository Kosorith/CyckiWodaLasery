package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Hint;

import java.util.Collections;
import java.util.List;

/**
 * Created by michal on 12/8/14.
 */
public class ChallengeCreationServiceImpl implements ChallengeCreationService {

    private Challenge editedChallenge;

    @Override
    public Challenge getEditedChallenge() {
        return editedChallenge;
    }

    @Override
    public void setEditedChallenge(Challenge challenge) {
        editedChallenge = challenge;
    }

    @Override
    public void initCreation() {
        editedChallenge = new Challenge();
    }

    @Override
    public boolean saveChallenge() {
        if (!preverifyChallenge()) {
            return false;
        }

        // TODO: SEND

        return false;
    }

    @Override
    public List<Hint> getHintList() {
        return Collections.unmodifiableList(editedChallenge.getHints());
    }

    @Override
    public void addHint(Hint hint) {
        editedChallenge.getHints().add(hint);
    }

    @Override
    public void removeHint(Hint hint) {
        editedChallenge.getHints().remove(hint);
    }

    @Override
    public boolean preverifyChallenge() {
        return editedChallenge != null
            && notEmpty(editedChallenge.getStub().getName())
            && !editedChallenge.getHints().isEmpty();
    }

    private boolean notEmpty(String string) {
        return string != null && !string.isEmpty();
    }
}
