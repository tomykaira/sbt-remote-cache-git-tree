package sbtremotecachegittree

import sbt.Keys._
import sbt.{Def, _}
import sbt.internal.RemoteCache

object RemoteCacheGitTreePlugin extends AutoPlugin {
  override def trigger = allRequirements

  private[this] val cacheSettings = Seq(
    remoteCacheId   :=  {
      if (remoteCacheGitTreeIgnoreDirtyState.value || !gitTreeIsDirty) {
        RemoteCache.gitCommitId
      } else {
        // Because file hash used for original remoteCacheId is private[sbt],
        // rely on the previous remoteCacheId value.
        s"${RemoteCache.gitCommitId}-${remoteCacheId.value}"
      }
    },
    remoteCacheIdCandidates := {
      (remoteCacheId.value +: RemoteCache.gitCommitIds(remoteCacheGitTreeGoBackCommitCount.value)).distinct
        }
  )

  override lazy val projectSettings: Seq[Def.Setting[_]] = Seq(
    remoteCacheGitTreeGoBackCommitCount := 10,
    remoteCacheGitTreeIgnoreDirtyState := false,
  ) ++ inConfig(Compile)(cacheSettings) ++ inConfig(Test)(cacheSettings)

  lazy val remoteCacheGitTreeGoBackCommitCount = settingKey[Int]("Git commit count included in remoteCacheIdCandidates")

  lazy val remoteCacheGitTreeIgnoreDirtyState = settingKey[Boolean]("If true, remoteCacheId becomes plain git hash even if git working directory is dirty. If false, remoteCacheId contains file based ID if dirty.")

  def gitTreeIsDirty: Boolean =
    scala.sys.process.Process("git diff --quiet").! != 0
}
