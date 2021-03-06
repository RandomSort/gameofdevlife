package net.praqma.lifeofdev.git

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.Work
import net.praqma.lifeofdev.game.GitGame
import net.praqma.lifeofdev.git.mergeconflict.costStrategy.ConstantMergeConflictCostStrategy
import net.praqma.lifeofdev.git.mergeconflict.costStrategy.InventoryMergeConflictCostStrategy
import net.praqma.lifeofdev.git.mergeconflict.costStrategy.LocalOriginDiffMergeConflictCostStrategy
import net.praqma.lifeofdev.git.mergeconflict.costStrategy.MergeConflictCostStrategy
import net.praqma.lifeofdev.git.mergeconflict.probabilitystrategy.LocalOriginDiffMergeConflictProbabilityStrategy
import net.praqma.lifeofdev.git.mergeconflict.probabilitystrategy.MergeConflictProbabilityStrategy
import net.praqma.lifeofdev.git.mergeconflict.probabilitystrategy.InventoryMergeConflictProbabilityStrategy
import net.praqma.lifeofdev.git.mergeconflict.probabilitystrategy.PercentageMergeConflictProbabilityStrategy
import net.praqma.lifeofdev.git.pushstrategy.OnlyFFPushStrategy
import net.praqma.lifeofdev.git.pushstrategy.PushStrategy

class Repository {
    ArrayList<Branch> branches = new ArrayList<Branch>()

    // dictionary of commits, order-less-"LinkedHashMap" for 1-to-1 get/put
    HashMap<String, Commit> commits = new HashMap<String, Commit>()

    Treeish HEAD

    PushStrategy ps

    MergeConflictProbabilityStrategy mcps

    MergeConflictCostStrategy mccs

    int ORIGINvalue = 0

    public void makeCommit(Work w, Developer author, String message) {
        addCommit(newRepoCommit(w, author, message))
    }

    /**
     * Creates a new commit for the repository - using the current HEAD as parent
     * @param w
     * @param author
     * @param message
     * @return
     */
    public Commit newRepoCommit(Work w, Developer author, String message) {
        return new Commit([HEAD], [w], author, message)
    }

    /**
     * Adds a commit and updates HEAD reference
     * @param c
     */
    public void addCommit(Commit c){
        // add commit to repository
        commits.put(c.sha, c)

        // update HEAD
        if(HEAD.getClass() == Branch){ // is the HEAD currently pointing to a branch?
            // set the branch-pointer to the new commit
            HEAD.commit = c
        } else {
            // assume it's pointing to a commit, update it to point to the new commit
            HEAD = c
        }
    }

    public Repository() {
        HEAD = new Branch(name: "master", commit: null)

        // PushStrategy ps = new AcceptAllPushStrategy(this)
        this.ps = new OnlyFFPushStrategy(this)

        this.mcps = new PercentageMergeConflictProbabilityStrategy(this, 0) // we never have a merge conflict
        // MergeConflictProbabilityStrategy mcps = new PercentageMergeConflictProbabilityStrategy(this, 80) // 80% chance
        // MergeConflictProbabilityStrategy mcps = new InventoryMergeConflictProbabilityStrategy(this, 15) // 15% per InventoryValue
        // 2% per InventoryValue and difference in remote and local idea of remote (with 5 developers, it becomes somewhat normalized to 0-100)
        // MergeConflictProbabilityStrategy mcps = new LocalOriginDiffMergeConflictProbabilityStrategy(this, 2)

        this.mccs = new ConstantMergeConflictCostStrategy(0) // merging is free (only trivial conflicts)
        // MergeConflictCostStrategy mccs = new ConstantMergeConflictCostStrategy(1) // merging costs 1 (only easy conflicts, but a conflict takes time)
        // MergeConflictCostStrategy mccs = new InventoryMergeConflictCostStrategy(this, 0.2) // merging costs 0.2 per InventoryValue, rounded to nearest integer
        // MergeConflictCostStrategy mccs = new LocalOriginDiffMergeConflictCostStrategy(this, 0.05) // merging costs 0.05 per difference in InventoryValue locally + the Value only on the remote

    }


    /**
     * What's the explicit value of the repsitory
     * @return
     */
    public int getValue() {

        int value = 0
        ArrayList<String> workSeen = new ArrayList<>()

        for (Commit c in commits.values()){

            if(!workSeen.contains(c.sha)){
                workSeen.add(c.sha)
                c.changes.each { Work w ->
                    value += w.value
                }
            }

        }

        return value
    }

    /**
     * Push all commits in local repository to ORIGIN
     * // FIXME this does not at all consider branches
     * @return
     */
    public boolean push(){
        if(GitGame.gameState == null) return false // fake singleton-like behaviour of pluggable gamestate instance

        return ps.push()

    }

    /**
     * What's the explicit value of THIS repository not delivered to "THE REMOTE"
     *  SLIGHTLY optimized since the quadratic overhead of recalculation
     *  if the number of commits haven't changed is completely unnecessary
     * @param gameState needed to infer "this games ORIGIN repository"
     * @return
     */
    private int commitsAtLastInventoryCalculation = 0
    private int inventoryValueAtLastCalculation = 0
    public int getInventoryValue() {

        if(GitGame.gameState == null) return false // fake singleton-like behaviour of pluggable gamestate instance

        if(commits.size() == commitsAtLastInventoryCalculation){
            return inventoryValueAtLastCalculation
        }

        int value = 0
        ArrayList<String> commitSeen = new ArrayList<>()

        HashMap<String, Commit> commitsOnOrigin = GitGame.gameState.ORIGIN.commits

        for (Commit c in commits.values()){

            if(!commitSeen.contains(c.sha) && !commitsOnOrigin.containsKey(c.sha)){
                commitSeen.add(c.sha)
                c.changes.each { Work w ->
                    value += w.value
                }
            }

        }

        // Update values for constant no-recalc-exit
        commitsAtLastInventoryCalculation = commits.size()
        inventoryValueAtLastCalculation = value

        return value
    }

    /**
     * Attempt to pull from origin, the pullMoney determines whether the pull can succeed
     * @param pullMoney the cost of making the pull
     * @return
     */
    boolean pull(Developer developer) {

        if(GitGame.gameState == null) return false // fake singleton-like behaviour of pluggable gamestate instance

        // get difference from origin

        HashMap<String, Commit> commitsOnOrigin = GitGame.gameState.ORIGIN.commits

        ArrayList<String> commitNotOnLocal = new ArrayList<>()

        // Get a list of the commits on the remote, not on the local
        for (String sha in commitsOnOrigin.keySet()){

            if(commits.containsKey(sha)){
                continue // we have the commit locally
            }

            commitNotOnLocal.add(sha)
        }

        if(commitNotOnLocal.size() == 0){
            // local is equal to origin
            return true
        }

        // Is there a merge conflict?
        if(mcps.isMergeConflict()){

            // Can we afford to solve the merge conflict?
            int mergeConflictCost = mccs.getCost()

            if(mergeConflictCost > developer.actionsLeftThisStep){
                return false // could not pull
            }

            developer.actionsLeftThisStep -= mergeConflictCost

        }

        // Add the commits not on local, to the local commits
        for (String sha in commitNotOnLocal){
            commits.put(sha, commitsOnOrigin.get(sha))
        }

        ORIGINvalue = GitGame.gameState.ORIGIN.getValue() // Update the local idea of the remote value

        return true

    }
}
