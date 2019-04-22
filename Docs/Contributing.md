## Contributing Process

1. Ensure working on latest codebase (`git pull --rebase origin master`)
2. Create your feature branch (`git checkout -b feature_foo`)
3. Make code changes...
4. Run all Unit/Component/Acceptance tests to ensure no test failures
5. Commit your changes (`git commit -am 'Add some foo'`)
6. Ensure working on latest codebase (`git pull --rebase origin master`)
7. Push to the remote branch (`git push origin feature_foo`)
8. Create a new Pull Request

## Commit Message Standards

* Every commit should include a commit message
* The message should describe both:
  * _What_ was done in the commit
  * _Why_ the commit was done
* "Write a story/letter to your future self"

## Pull Request Standards

* Incomplete / WIP (Work in Progress) code should **never** be merged into the `master` branch.
* The `master` branch should be "runnable" at every single merge point into `master`.

## Code Review Standards

* All Pull Requests should have _at least_ one other developer perform a thorough code review before a 
Pull Request should be merged.
* Comments should be made in GitHub
* Requests for Changes should be discussed and addressed before merging into `master`

## Branching Model

* We prefer short-lived, feature branches for development work.
* Use descriptive branch names (example `feature_x` or `jira-123` or `add_docs_to_backend`) instead of 
developer names (example `chris_branch`) or vague / automatic branch names (example `patch-1` or `branch-6`).
