package org.azd.interfaces;

import org.azd.enums.PullRequestStatus;
import org.azd.exceptions.AzDException;
import org.azd.exceptions.ConnectionException;
import org.azd.git.types.*;

public interface GitDetails {
    Repository createRepository(String repositoryName, String projectId) throws ConnectionException, AzDException;

    void deleteRepository(String repositoryId) throws ConnectionException, AzDException;

    void deleteRepositoryFromRecycleBin(String repositoryId) throws ConnectionException, AzDException;

    GitDeletedRepositories getDeletedRepositories() throws ConnectionException, AzDException;

    GitDeletedRepositories getRecycleBinRepositories() throws ConnectionException, AzDException;

    Repository getRepository(String repositoryName) throws ConnectionException, AzDException;

    Repositories getRepositories() throws ConnectionException, AzDException;

    Repository restoreRepositoryFromRecycleBin(String repositoryId, boolean deleted) throws ConnectionException, AzDException;

    Repository updateRepository(String repositoryId, String repositoryName, String defaultBranchName) throws ConnectionException, AzDException;

    PullRequest createPullRequest(
            String repositoryId, String sourceRefName, String targetRefName,
            String title, String description, String[] reviewers) throws ConnectionException, AzDException;

    PullRequest createPullRequest(
            String repositoryId, String sourceRefName, String targetRefName,
            String title, String description, boolean isDraft) throws ConnectionException, AzDException;

    PullRequest getPullRequest(String repositoryName, int pullRequestId) throws ConnectionException, AzDException;

    PullRequest getPullRequestById(int pullRequestId) throws ConnectionException, AzDException;

    PullRequests getPullRequests(String repositoryName) throws ConnectionException, AzDException;

    PullRequests getPullRequestsByProject() throws ConnectionException, AzDException;

    PullRequests getPullRequestsByProject(int top) throws ConnectionException, AzDException;

    PullRequests getPullRequestsByProject(PullRequestStatus status) throws ConnectionException, AzDException;

    PullRequests getPullRequestsByProject(int skip, int top, String creatorId, boolean includeLinks,
                                          String repositoryId, String reviewerId, String sourceRefName,
                                          String sourceRepositoryId, PullRequestStatus status, String targetRefName) throws ConnectionException, AzDException;

    GitRef updateBranchLock(String repositoryName, String branchName, boolean isLocked) throws ConnectionException, AzDException;

    ResourceRefs getPullRequestWorkItems(int pullRequestId, String repositoryName) throws ConnectionException, AzDException;

    WebApiTagDefinition createPullRequestLabel(String repositoryName, int pullRequestId, String labelName) throws ConnectionException, AzDException;

    void deletePullRequestLabel(String repositoryName, int pullRequestId, String labelName) throws ConnectionException, AzDException;

    WebApiTagDefinition getPullRequestLabel(String repositoryName, int pullRequestId, String labelName) throws ConnectionException, AzDException;

    WebApiTagDefinitions getPullRequestLabels(String repositoryName, int pullRequestId) throws ConnectionException, AzDException;

    PullRequestReviewer createPullRequestReviewer(int pullRequestId, String repositoryId,
                                                  String reviewerId, int vote, boolean isRequired) throws ConnectionException, AzDException;

    void deletePullRequestReviewer(int pullRequestId, String repositoryId, String reviewerId) throws ConnectionException, AzDException;

    PullRequestReviewer getPullRequestReviewer(int pullRequestId, String repositoryId,
                                               String reviewerId) throws ConnectionException, AzDException;

    PullRequestReviewers getPullRequestReviewers(int pullRequestId, String repositoryId) throws ConnectionException, AzDException;

    PullRequestReviewer updatePullRequestReviewer(int pullRequestId, String repositoryId, String reviewerId,
                                                  boolean isFlagged, boolean hasDeclined) throws ConnectionException, AzDException;
}
