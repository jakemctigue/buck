/*
 * Copyright 2016-present Facebook, Inc.
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

package com.facebook.buck.shell;

import com.facebook.buck.cli.FakeBuckConfig;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.model.Either;
import com.facebook.buck.rules.AbstractNodeBuilderWithMutableArg;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Optional;

public class WorkerToolBuilder
    extends AbstractNodeBuilderWithMutableArg<
        WorkerToolDescription.Arg, WorkerToolDescription, DefaultWorkerTool> {
  private WorkerToolBuilder(BuildTarget target) {
    super(new WorkerToolDescription(FakeBuckConfig.builder().build()), target);
  }

  public static WorkerToolBuilder newWorkerToolBuilder(BuildTarget target) {
    return new WorkerToolBuilder(target);
  }

  public WorkerToolBuilder setEnv(ImmutableMap<String, String> env) {
    arg.env = env;
    return this;
  }

  public WorkerToolBuilder setExe(BuildTarget exe) {
    arg.exe = exe;
    return this;
  }

  public WorkerToolBuilder setArgs(String... args) {
    arg.args = Either.ofRight(ImmutableList.copyOf(args));
    return this;
  }

  public WorkerToolBuilder setMaxWorkers(Integer maxWorkers) {
    arg.maxWorkers = Optional.of(maxWorkers);
    return this;
  }
}
