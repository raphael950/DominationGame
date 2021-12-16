package net.hydramc.domination.step;

import net.hydramc.domination.Game;

import java.util.ArrayList;
import java.util.List;

public class StepManager {

    private final Game game;
    private final List<Step> gameSteps;
    private int currentStep;

    public StepManager(Game game) {
        this.game = game;
        this.gameSteps = new ArrayList<Step>();
        this.currentStep = -1;
    }

    public boolean registerStep(int index, Step step) {
        if (step == null || this.gameSteps.contains(step))
            return false;
        this.gameSteps.add(index, step);
        return true;
    }

    public boolean registerStepBefore(String targetedStepName, Step step) {
        final int targetedIndex = indexOfFromName(targetedStepName);

        if (targetedIndex < 0)
            return false;
        return registerStep(targetedIndex, step);
    }

    public boolean registerStepAfter(String targetedStepName, Step step) {
        final int targetedIndex = indexOfFromName(targetedStepName);

        if (targetedIndex < 0)
            return false;
        return registerStep(targetedIndex + 1, step);
    }

    public boolean registerStep(Step step) {
        if (step == null || this.gameSteps.contains(step))
            return false;
        return this.gameSteps.add(step);
    }

    public boolean unRegisterStep(Step step) {
        return step != null && this.gameSteps.remove(step);
    }

    public boolean unRegisterStepFromName(String stepName) {
        return stepName != null && this.gameSteps.remove(stepName);
    }

    public int indexOf(Step step) {
        if (step == null)
            return -1;
        return this.gameSteps.indexOf(step);
    }

    public int indexOfFromName(String stepName) {
        if (stepName == null)
            return -1;
        return this.gameSteps.indexOf(stepName);
    }

    public Step getStepFromIndex(int index) {
        if (index < 0 || index >= this.gameSteps.size())
            return null;
        return this.gameSteps.get(index);
    }

    public Step getStepFromName(String stepName) {
        if (stepName == null)
            return null;
        for (Step step : this.gameSteps) {
            if (step.equals(stepName))
                return step;
        }
        return null;
    }

    public Step getCurrentStep() {
        return getStepFromIndex(this.currentStep);
    }

    public Step getBeforeStep() {
        return getStepFromIndex(this.currentStep - 1);
    }

    public Step getNextStep() {
        return getStepFromIndex(this.currentStep + 1);
    }

    private void callStep(Step before, Step current, Step next) {
        if (before != null)
            before.remove();
        if (current != null)
            current.complete();
        if (next != null)
            next.start();
    }

    public Step nextStep() {
        final Step nextStep;

        if (this.currentStep >= this.gameSteps.size() || this.game.getGameStats().ordinal() < 3)
            return null;
        nextStep = getBeforeStep();
        callStep(getBeforeStep(), getCurrentStep(), nextStep);
        this.currentStep++;
        return nextStep;
    }

    public Step backStep() {
        final Step lastStep;

        if (this.currentStep < 0 || this.game.getGameStats().ordinal() < 3)
            return null;
        lastStep = getBeforeStep();
        if (lastStep != null && !lastStep.canStepBack())
            return getCurrentStep();
        callStep(getNextStep(), getCurrentStep(), lastStep);
        this.currentStep--;
        return lastStep;
    }

}
