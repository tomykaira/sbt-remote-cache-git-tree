# CAUTION

This is a very experimental plugin. We plan to introduce many destructive changes to enhance the plugin.
We are very welcome for any contribution, such as fixing coding style and suggesting better methods.

# sbt RemoteCache git tree

[ ![Bintray](https://api.bintray.com/packages/tomykaira/sbt-plugins/sbt-remote-cache-git-tree/images/download.svg) ](https://bintray.com/tomykaira/sbt-plugins/sbt-remote-cache-git-tree/_latestVersion)

## Installation

Write the line to `project/plugins.sbt`.
This is auto plugin, `remoteCacheId` and `remoteCacheIdCandidates` of your projects are automatically configured.

```
addSbtPlugin("io.github.tomykaira" % "sbt-remote-cache-git-tree" % "0.1.0")
```

If you want to disable the feature for subprojects, use `disablePlugins`.
See [sbt doc](https://www.scala-sbt.org/1.x/docs/Plugins.html) for details.

## Motivation

Sbt 1.4 introduced [RemoteCache](https://eed3si9n.com/remote-caching-sbt-builds-with-bintray) mechanism.

The feature is attractive, but naive for production use.
I want to go back histories on VCS commit tree and find the nearest parent's cache.
This is useful for development with CI. If CI push all build outputs as remote caches,
all developers and following build jobs can get the suitable cache from the internal maven repo.

## Implementation

The motivation is not specific to git, but this plugin currently supports only Git.

The plugin assumes that git command is installed and the current working directory is git directory.

Basically `remoteCacheId` is first 10 letters of git commit sha.

`pullRemoteCache` will search up to 10 previous commits.
This plugin uses `remoteCacheIdCandidates` to achieve that.

In order to prevent developers from overwriting remote caches by source files with local changes,
if the git working directory is dirty, sbt original `remoteCacheId` will be attached after git-based `remoteCacheId`.

## Configurations

- `remoteCacheGitTreeGoBackCommitCount`: Configure how many previous commits are included in `remoteCacheIdCandidates`. Default is `10`.
- `remoteCacheGitTreeIgnoreDirtyState`: If true, `remoteCacheId` does not contain file hash based suffix even if the working tree is dirty. This is useful if other steps in CI change versioned files. Default is `false`.

## Release

(note for me)

Rewrite version in `build.sbt` and `README.md`.

```
publish
```
