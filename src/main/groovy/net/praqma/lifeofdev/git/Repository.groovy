package net.praqma.lifeofdev.git

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.Work
import net.praqma.lifeofdev.game.GameState
import net.praqma.lifeofdev.game.GitGame

class Repository {
    ArrayList<Branch> branches = new ArrayList<Branch>()

    // dictionary of commits, order-less-"LinkedHashMap" for 1-to-1 get/put
    HashMap<String, Commit> commits = new HashMap<String, Commit>()

    Treeish HEAD

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

        commits.values().each { Commit commit ->

            // if the commit is already on origin, continue (return inside each = continue)
            if(GitGame.gameState.ORIGIN.commits.containsKey(commit.sha)) return

            GitGame.gameState.ORIGIN.addCommit(commit)
        }

        return true

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
        ArrayList<String> workSeen = new ArrayList<>()

        HashMap<String, Commit> commitsOnOrigin = GitGame.gameState.ORIGIN.commits

        for (Commit c in commits.values()){

            if(!workSeen.contains(c.sha) && !commitsOnOrigin.containsKey(c.sha)){
                workSeen.add(c.sha)
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


}
