/*
 * Copyright 2015-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.util.cache;

import com.facebook.buck.io.ArchiveMemberPath;
import com.facebook.buck.io.ProjectFilesystem;
import com.facebook.buck.log.Logger;
import com.facebook.buck.util.WatchmanOverflowEvent;
import com.facebook.buck.util.WatchmanPathEvent;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.eventbus.Subscribe;
import com.google.common.hash.HashCode;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Optional;

public class WatchedFileHashCache extends DefaultFileHashCache {

  private static final Logger LOG = Logger.get(WatchedFileHashCache.class);

  private long newCacheAggregatedNanoTime = 0;
  private long oldCacheAggregatedNanoTime = 0;
  private long numberOfInvalidations = 0;
  private long sha1Mismatches = 0;

  public WatchedFileHashCache(ProjectFilesystem projectFilesystem) {
    super(projectFilesystem, Optional.empty());
  }

  /**
   * Called when file change events are posted to the file change EventBus to invalidate cached
   * build rules if required. {@link Path}s contained within events must all be relative to the
   * {@link ProjectFilesystem} root.
   */
  @Subscribe
  public synchronized void onFileSystemChange(WatchmanPathEvent event) {
    // Path event, remove the path from the cache as it has been changed, added or deleted.
    Path path = event.getPath().normalize();
    LOG.verbose("Invalidating %s", path);
    // invalidate(path) will invalidate all the child paths of the given path and that all the
    // parent paths will be invalidated and, if possible, removed too.
    long start = System.nanoTime();
    invalidateNew(path);
    newCacheAggregatedNanoTime += System.nanoTime() - start;
    start = System.nanoTime();
    invalidateOldCache(path);
    oldCacheAggregatedNanoTime += System.nanoTime() - start;
    numberOfInvalidations++;
  }

  // TODO(rvitale): remove block below after the file hash cache experiment is over.
  /* *****************************************************************************/
  public void resetCounters() {
    newCacheAggregatedNanoTime = 0;
    oldCacheAggregatedNanoTime = 0;
    numberOfInvalidations = 0;
    sha1Mismatches = 0;
  }

  public long getNewCacheAggregatedNanoTime() {
    return newCacheAggregatedNanoTime;
  }

  public long getOldCacheAggregatedNanoTime() {
    return oldCacheAggregatedNanoTime;
  }

  public long getNumberOfInvalidations() {
    return numberOfInvalidations;
  }

  public long getSha1Mismatches() {
    return sha1Mismatches;
  }

  private void invalidateOldCache(Path path) {
    Iterable<Path> pathsToInvalidate =
        Maps.filterEntries(
                loadingCache.asMap(),
                entry -> {
                  Preconditions.checkNotNull(entry);

                  // If we get a invalidation for a file which is a prefix of our current one, this
                  // means the invalidation is of a symlink which points to a directory (since events
                  // won't be triggered for directories).  We don't fully support symlinks, however,
                  // we do support some limited flows that use them to point to read-only storage
                  // (e.g. the `project.read_only_paths`).  For these limited flows to work correctly,
                  // we invalidate.
                  if (entry.getKey().startsWith(path)) {
                    return true;
                  }

                  // Otherwise, we want to invalidate the entry if the path matches it.  We also
                  // invalidate any directories that contain this entry, so use the following
                  // comparison to capture both these scenarios.
                  if (path.startsWith(entry.getKey())) {
                    return true;
                  }

                  return false;
                })
            .keySet();
    LOG.verbose("Paths to invalidate: %s", pathsToInvalidate);
    for (Path pathToInvalidate : pathsToInvalidate) {
      invalidate(pathToInvalidate);
    }
  }

  @Override
  public HashCode get(ArchiveMemberPath archiveMemberPath) throws IOException {
    HashCode sha1 = super.get(archiveMemberPath);
    HashCode newSha1 = getFromNewCache(archiveMemberPath);
    sha1Mismatches += sha1.equals(newSha1) ? 0 : 1;
    return sha1;
  }
  /* *****************************************************************************/

  private HashCode getFromNewCache(ArchiveMemberPath archiveMemberPath) throws IOException {
    Path relativeFilePath = archiveMemberPath.getArchivePath().normalize();
    HashCodeAndFileType fileHashCodeAndFileType = newLoadingCache.get(relativeFilePath);
    Path memberPath = archiveMemberPath.getMemberPath();
    HashCodeAndFileType memberHashCodeAndFileType =
        fileHashCodeAndFileType.getContents().get(memberPath);
    if (memberHashCodeAndFileType == null) {
      throw new NoSuchFileException(archiveMemberPath.toString());
    }

    return memberHashCodeAndFileType.getHashCode();
  }

  @SuppressWarnings("unused")
  @Subscribe
  public synchronized void onFileSystemChange(WatchmanOverflowEvent event) {
    // Non-path change event, likely an overflow due to many change events: invalidate everything.
    LOG.debug("Invalidating all");
    long start = System.nanoTime();
    invalidateAll();
    oldCacheAggregatedNanoTime += System.nanoTime() - start;
    start = System.nanoTime();
    invalidateAllNew();
    newCacheAggregatedNanoTime += System.nanoTime() - start;
    numberOfInvalidations++;
  }
}
