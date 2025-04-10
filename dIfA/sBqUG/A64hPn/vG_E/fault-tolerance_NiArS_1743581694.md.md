# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Unreleased

## [3.2.7] - 2023-12-09

### Changes

- Replace dependent class library github.com/struCoder/pidusage with github.com/shirou/gopsutil/v3/process
- pkg import only once https://github.com/distribworks/dkron/pull/1406
- Bump deps
- Use Go 1.21

### Features

- add TLS options for kafka executor plugin https://github.com/distribworks/dkron/pull/1384
- Resource usage from child processes spawned by the task startup process.
- Introduce two new metrics, dkron_job_execution_time_seconds and dkron_job_execution_done_count, for improved monitoring and alerting capabilities.

### Fixes

- cmd.Process.Kill() does not clean up the started process, causing the job to remain running. https://github.com/distribworks/dkron/pull/1385
- The metric dkron_job_mem_usage_kb now exclusively calculates the memory usage of the task startup process, excluding 
- Should output.TotalWritten be judged after cmd.Wait() is executed? https://github.com/distribworks/dkron/pull/1319
- The metrics exposed by dkron are only of summary type and cannot cover related analysis for specific jobs. https://github.com/distribworks/dkron/pull/1310

## [3.2.6] - 2023-09-02

### Changes

- Bump deps & update docs

## [3.2.5] - 2023-06-15

### Changes

- Bump deps

## [3.2.4] - 2023-05-29

### Fixes

- Do not retry job when unknown status [#1335](https://github.com/distribworks/dkron/pull/1335)

### Features

- Prometheus metrics for shell executor [#1330](https://github.com/distribworks/dkron/pull/1330)

### Changes

- Bump deps
- Fix linting issues [#1336](https://github.com/distribworks/dkron/pull/1336)

## [3.2.3] - 2023-03-17

### Fixes

- Fix non existing dependent job (https://github.com/distribworks/dkron/pull/1297)
### Changes

- Bump deps

## [3.2.2] - 2023-03-11

### Changes

- Bump deps
- Update docusaurus

## [3.2.1] - 2022-09-18

### Features

- Optionally set an alternative raftStore

### Fixes

- Re-add arm-v7 docker build
- Minor fixes

### Changes

- Add golintci config
- More variety to testing infra
- Bump deps

## [3.2.0] - 2022-06-05

### Fixes

- Refactor cron lifecycle (https://github.com/distribworks/dkron/pull/1119)
- Leadership transfer (https://github.com/distribworks/dkron/pull/1109)

### Changes

- Add prewebhook and cronitor telemetry integration (https://github.com/distribworks/dkron/pull/1099)
- Implement GRPC executor (https://github.com/distribworks/dkron/pull/1049)
- Use golang/cross to build multiarch docker (https://github.com/distribworks/dkron/pull/1105)
- Implement some suggestions to the UI (https://github.com/distribworks/dkron/pull/1120)
- New web site (https://github.com/distribworks/dkron/pull/1072)
- Bump deps

## [3.1.11] - 2022-04-07

### Fixes

- Try to fix scheduler not starting [#1053](https://github.com/distribworks/dkron/pull/1053)
- look for plugins in the config path used (as documented) instead of hardcoded /etc/dkron [#1024](https://github.com/distribworks/dkron/pull/1024)
- Do not exit on failed leadership [#1082](https://github.com/distribworks/dkron/pull/1082)
### Changes

- Allow multiple brokers in kafka executor [#1037](https://github.com/distribworks/dkron/pull/1037)
- Allow passing through a message key for Kafka executor [#1021](https://github.com/distribworks/dkron/pull/1021)
- Bump dependencies
- Create codeql-analysis.yml
- refresh current page after toggle/run job is used [#1026](https://github.com/distribworks/dkron/pull/1026)
- supported terraform provider link [#1029](https://github.com/distribworks/dkron/pull/1029)
- Update metrics docs for Prometheus config file [#1058](https://github.com/distribworks/dkron/pull/1058)
- truncate execution output in default view [#1025](https://github.com/distribworks/dkron/pull/1025)
- Add execution endpoint [#1085](https://github.com/distribworks/dkron/pull/1085)
## [3.1.10] - 2021-10-01

### Fixes

- Nats executor consume all available connections [#1020](https://github.com/distribworks/dkron/pull/1020)

### Changes

- Update assets with latest changes

## [3.1.9] - 2021-09-29

### Features

- Update node status text [#1012](https://github.com/distribworks/dkron/pull/1012)

### Fixes

- Add 'next' field to swagger definition [#991](https://github.com/distribworks/dkron/pull/991)
- Fix comment typo [#1000](https://github.com/distribworks/dkron/pull/1000)
- Fix typos [#1005](https://github.com/distribworks/dkron/pull/1005)
- Fix notification bug [#993](https://github.com/distribworks/dkron/pull/993)
- fd-leak when RPC client communication fail [#1009](https://github.com/distribworks/dkron/pull/1009)
- Panic or network errors [#1008](https://github.com/distribworks/dkron/pull/1008)
- Wait all jobs done [#1010](https://github.com/distribworks/dkron/pull/1010)
- Wait for scheduler stop first [#1016](https://github.com/distribworks/dkron/pull/1016)

### Changes

- Swagger assets from cdn [#997](https://github.com/distribworks/dkron/pull/997)
- Use relative API_URL [#1006](https://github.com/distribworks/dkron/pull/1006)


## [3.1.8] - 2021-06-16

### Fixes

- urls were not working well in documentation [#979](https://github.com/distribworks/dkron/pull/979)
- Improve upgrade doc [#980](https://github.com/distribworks/dkron/pull/980)
- Update agent tags on config reload [#983](https://github.com/distribworks/dkron/pull/983)
- Fix intermittent test false negative [#982](https://github.com/distribworks/dkron/pull/982)
- Fix TLSRaftLayer init [#987](https://github.com/distribworks/dkron/pull/987)

### Changes

- Use Buildkit stable [#977](https://github.com/distribworks/dkron/pull/977)
- Control errors on scheduler start [#978](https://github.com/distribworks/dkron/pull/978)


## [3.1.7] - 2021-05-29

### Features

- Implement ephemeral and expires at feature [#972](https://github.com/distribworks/dkron/pull/972)
- Add @minutely custom schedule again [#970](https://github.com/distribworks/dkron/pull/970)

### Fixes

- DataGrid UI fixes for long fields [#965](https://github.com/distribworks/dkron/pull/965)
- Fix race conditions [#967](https://github.com/distribworks/dkron/pull/967)
- Fix shell executor crashing on missing command [#948](https://github.com/distribworks/dkron/pull/948)

### Changes

- Overhaul logging to avoid package level var [#963](https://github.com/distribworks/dkron/pull/963)
- Improve tests for http executor [#936](https://github.com/distribworks/dkron/pull/936)
- Refactor processFilteredNodes for testing [#968](https://github.com/distribworks/dkron/pull/968)

## [3.1.6] - 2021-03-23

### Features

- Add a filter on disabled state [#923](https://github.com/distribworks/dkron/pull/923) @educlos
- Provide filter query by job displayName, add pristine jobs reports [#897](https://github.com/distribworks/dkron/pull/897) @MGSousa

### Fixes

- Fix XSS vulnerability [#922](https://github.com/distribworks/dkron/pull/922) @yvanoers
- Correct title of NATS executor page [#929](https://github.com/distribworks/dkron/pull/929) @yvanoers
- UI fixes [#926](https://github.com/distribworks/dkron/pull/926) @yvanoers

### Changes

- Use go embed for assets embedding [#931](https://github.com/distribworks/dkron/pull/931)

## [3.1.5] - 2021-03-08

### Features

- Configurable job timeout [#906](https://github.com/distribworks/dkron/pull/906)
- Add kafka and nats executor [#854](https://github.com/distribworks/dkron/pull/854)
- Add reporting usage stats [#910](https://github.com/distribworks/dkron/pull/910)

### Changes

- Bump some deps

### Fixes

- Add the ui/public dir [#919](https://github.com/distribworks/dkron/pull/919)

## [3.1.4] - 2021-01-25

### Changes

- Bump some deps
- UI fixes
    * Add timezone back to UI
    * Show execution status

## [3.1.3] - 2021-01-17

### Changes

- Several UI Improvements [#891](https://github.com/distribworks/dkron/pull/891)
    * Visual status for jobs
    * Bulk actions for toggle and run
    * Flexible jobs datagrid layout
    * Fix clock style
    * Fix date rendering on non finished executions

## [3.1.2] - 2021-01-08

### Features

- Added some query filters on Job executions [#878](https://github.com/distribworks/dkron/pull/878) @MGSousa
- Executions sorting in UI [#885](https://github.com/distribworks/dkron/pull/885)

### Fixes

- Fix panic on recovering cluster (with peers.json) [#882](https://github.com/distribworks/dkron/pull/882) @fopina
- Use correct wrapping and monospace font for execution's output [#879](https://github.com/distribworks/dkron/pull/879) @sc0rp10


## [3.1.1] - 2020-12-21

### Features

- Some info cards in dashboard [#873](https://github.com/distribworks/dkron/pull/873)
- Add status to JobOption to filter from UI and API [#872](https://github.com/distribworks/dkron/pull/872)

## [3.1.0] - 2020-12-18

### Features

- React admin web UI [#864](https://github.com/distribworks/dkron/pull/864)

### Changes

- Use newer gRPC library version [#855](https://github.com/distribworks/dkron/pull/855)
- Bump deps

### Fixes

- Clean up log message [#860](https://github.com/distribworks/dkron/pull/860) @yvanoers
- Remove duplicate lines from Recovery documentation [#861](https://github.com/distribworks/dkron/pull/861) @vishalsngl
- How errors are handled in the AgentRun call [#858](https://github.com/distribworks/dkron/pull/858)
- Fix uneven balancing [#865](https://github.com/distribworks/dkron/pull/865) @yvanoers

## [3.0.8] - 2020-11-20

### Changes

- Clean-up and lint some code [#853](https://github.com/distribworks/dkron/pull/853)
- Better metrics [#852](https://github.com/distribworks/dkron/pull/852)

### Fixes

- Represent execution times in the job's timezone [#615](https://github.com/distribworks/dkron/pull/615) @yvanoers
- avoid nil pointer if job was deleted on ExecutionDone [#851](https://github.com/distribworks/dkron/pull/851)

## [3.0.7] - 2020-11-10

### Changes

- Bump deps

### Fixes

- Server crash when adding new job [#840](https://github.com/distribworks/dkron/pull/840)
- Fix method of busy endpoint in swagger.yaml [#843](https://github.com/distribworks/dkron/pull/843) @yvanoers
- Fix multitag cardinality bug [#842](https://github.com/distribworks/dkron/pull/842) @yvanoers

## [3.0.6] - 2020-10-15

### Changes

- Revert "feat: Include scheduler entries in status API" [#829](https://github.com/distribworks/dkron/pull/829)
- Bump some deps

## [3.0.5] - 2020-09-04

### Changes

- Github Action for release (binary and docker) [#770](https://github.com/distribworks/dkron/pull/770)
- Include scheduler entries in status API [#813](https://github.com/distribworks/dkron/pull/813)
- Bump deps [#814](https://github.com/distribworks/dkron/pull/814)

### Fixes

- free s.Cron on proper opportunity to avoid unexpected crash [#779](https://github.com/distribworks/dkron/pull/779)

## [3.0.4] - 2020-06-12

### Fixes

- processFilteredNodes do not return nodes when specified tag has no nodes [#785](https://github.com/distribworks/dkron/pull/785)

## [3.0.3] - 2020-06-10

### Fixes

- Log start job and logging prefix in grpc agent [#776](https://github.com/distribworks/dkron/pull/776)

### Features

- busyhandler execution sorting [#772](https://github.com/distribworks/dkron/pull/772)
- Parse retry-join addresses with sockaddr templates [#783](https://github.com/distribworks/dkron/pull/783)

### Changes

- Bump several dependencies

## [3.0.2] - 2020-05-15

### Fixes

- Fix binding to Advertise address [#763](https://github.com/distribworks/dkron/pull/763)

## [3.0.1] - 2020-05-12

### Features

- New Processor that sends job output to fluent destination [#759](https://github.com/distribworks/dkron/pull/759) @andreygolev
- Configurable serf reconnect timeout [#757](https://github.com/distribworks/dkron/pull/757) @andreygolev

### Fixes

- Fix Alerts JS [#762](https://github.com/distribworks/dkron/pull/762)


## [3.0.0] - 2020-05-09

### Features

- Add prometheus endpoint for metrics [#740](https://github.com/distribworks/dkron/pull/740)

### Fixes

- Ignored RaftMultiplier config param [#753] (https://github.com/distribworks/dkron/pull/753)
- Increase serf events buffer size [#732](https://github.com/distribworks/dkron/pull/732)
- Resetting the status and next params [#730](https://github.com/distribworks/dkron/pull/730)

### Changes

- Upgrade deps and add codename to version [#751](https://github.com/distribworks/dkron/pull/751)
- Better alerts with growl like notifications [#750](https://github.com/distribworks/dkron/pull/750)
- Refactor Run jobs [#749](https://github.com/distribworks/dkron/pull/749)
- Add job name tags to log events to improve debugging [#739](https://github.com/distribworks/dkron/pull/739)

## [2.2.2] - 2020-04-22

### Fixes

- Increase serf events buffer size [#732](https://github.com/distribworks/dkron/pull/732) 
- Resetting the status and next params [#730](https://github.com/distribworks/dkron/pull/730)

### Changes

- Bump protobuf to 1.4.0 [#729](https://github.com/distribworks/dkron/pull/729)

## [2.2.1] - 2020-04-15

### Changes

- Restore jobs with file [#654](https://github.com/distribworks/dkron/pull/654) @vision9527
- Upgrade deps [#724](https://github.com/distribworks/dkron/pull/724) [#725](https://github.com/distribworks/dkron/pull/725) [#726](https://github.com/distribworks/dkron/pull/726)

## [2.2.0] - 2020-04-11

### Changes

- Bump dependencies
- Change Execution output type from []byte -> string, this works as we need with Go's JSON Marshal

### Breaking Changes

- Streaming executions: Implement persistent gRPC connections from agents to server during executions, refactored plugins interface to provide ability to stream output to the server and implement the new `/busy` endpoint to display running executions. Also refactored the Job status compute, to simplify it by removing the `running` status, this could be computed by the user using the `/busy` endpoint. (#716, #719, #720, #721, #723)

## [2.1.1] - 2020-03-20

### Fixes

- Graceful shutdown [#690](https://github.com/distribworks/dkron/pull/690) @andreygolev
- Fixes crash when plugin configuration is not defined in a job [#689](https://github.com/distribworks/dkron/pull/689) @andreygolev
- Defer panic fix in ExecutionDone gRPC call [#691](https://github.com/distribworks/dkron/pull/691) @andreygolev

### Changes

- Default config will start and bootstrap a server
- isLeader handler added [#695](https://github.com/distribworks/dkron/pull/695)
- Compile with go 1.14
- Execution load balancing [#692](https://github.com/distribworks/dkron/pull/692) @andreygolev
- Update Bootstrap and JQuery [#700](https://github.com/distribworks/dkron/pull/700)
- Upgrade all dependencies [#703](https://github.com/distribworks/dkron/pull/703)

### Breaking Changes

- Decrease plugin size by 75%, refactored plugin coding interface could affect new plugins development and require adaptations for existing plugin. [#696](https://github.com/distribworks/dkron/pull/696)
- Use BuntDB for local storage, fixes [#687](https://github.com/distribworks/dkron/issues/687), require rolling upgrade. [#702](https://github.com/distribworks/dkron/pull/702) @andreygolev

## [2.0.6] - 2020-02-14

### Fixes

- Memory consumption on startup [#682](https://github.com/distribworks/dkron/pull/682)

## [2.0.5] - 2020-02-12

### Fixes

- Set the agent on dependent run [#675](https://github.com/distribworks/dkron/pull/675)
- Return the correct status code on leader endpoint [#671](https://github.com/distribworks/dkron/pull/671)

### Changes

- Check for missing agent [#675](https://github.com/distribworks/dkron/pull/675)
- Add code comment [#675](https://github.com/distribworks/dkron/pull/675)

## [2.0.4] - 2020-01-31

- Remove dependency of the agent in store and reduce usage in Job [#669](https://github.com/distribworks/dkron/pull/669)
- Upgrade gin [#669](https://github.com/distribworks/dkron/pull/669)
- Add helper methods [#669](https://github.com/distribworks/dkron/pull/669)
- Move directory creation to the Store instantiation [#669](https://github.com/distribworks/dkron/pull/669)
- Accept middlewares for API routes [#669](https://github.com/distribworks/dkron/pull/669)
- ACL docs

## [2.0.3] - 2020-01-04

### Fixes

- Fix modal indexing in UI [#666](https://github.com/distribworks/dkron/pull/666)

### Changes

- Bump BadgerDB to 2.0.1 [#667](https://github.com/distribworks/dkron/pull/667)
- Allow templating of time format in notifications webhook [#665](https://github.com/distribworks/dkron/pull/665)

## [2.0.2] - 2019-12-11

### Features

- Search all jobs in dashboards with the search box [#653](https://github.com/distribworks/dkron/pull/653)

### Fixes

- Validate empty job names [#659](https://github.com/distribworks/dkron/pull/659)
- Die on plugin communication error [#658](https://github.com/distribworks/dkron/pull/658)
- Revert GetStatus with concurrency forbid [#655](https://github.com/distribworks/dkron/pull/655)

### Changes

- Upgrade Angular to latest [#641](https://github.com/distribworks/dkron/pull/641)

## [2.0.1] - 2019-12-03

### Fixed

- Child jobs not being executed due to running Job status [#651](https://github.com/distribworks/dkron/pull/651)

### Refactored

- Remove copied cron lib and add as dependency [#646](https://github.com/distribworks/dkron/pull/646)

## [2.0.0] - 2019-11-27

### Changed

- This file will be based on Keep a Changelog guidelines

### Added

- TBD

## 1.2.5

- Fix: dependent job updates (@yvanoers)
- Fix: Job reschedule on every page refresh
- Fix: paginator gap and add First and Last button
- Fix: new jobs counting as failed in dashboard (@yvanoers)
- Feature: backend TLS support (@fopina)
- Feature: support for authentication with etcd backend (@fopina)
- Fix: sample dkron.yml for slack configuration (@kakakikikeke-fork)

## 1.2.4

- Update swagger spec: fix executor_config, add status
- New site design
- Testing: Parameterize test suite to use any backend
- Refactor: GetLastExecutionGroup to simplify code

## 1.2.3

- Fix: Bump valkeyrie with redis watches fix
- Implement server selection using consistent hash
- Upgrade serf to 0.8.2
- refactor: Store should implement the interface

## 1.2.2

- Fix: Use branched valkeyrie fixing DynamoDB return all items

## 1.2.1

- Fix: scheduler_started expvar fix

## 1.2.0

- Fix: Update executor support error log instead of fail only (@tengattack)
- Feature: Return next execution field in API and dashboard.
- Feature: Add backend redis password flag (@lisuo3389)
- Feature: Add backend consul token
- Improvement: Main graph showing running jobs

**NOTE: Breaking change for 3rd party plugins, executors plugins interface signature changed, should be recompiled and adapted.**

## 1.1.1

- Add RPC leave command
- Fix: Missing tzdata in release image

## 1.1.0

- Add DynamoDB support
- Breaking: Remove support and deprecation message for old command, environment_variables and shell parameters
- Change scheduler_started expvar to int to be parseable
- Several docs improvements
- Fixed swagger.yaml syntax error
- Add new line before END OUTPUT for log processor

## 1.0.2

- Allow sending mail without credentials
- Fix docker tagging
- Log plugin fix and improvements
- More specific processor plugin usage logging

## 1.0.1

- Don't dockerignore dist folder, it is needed by gorelease docker builder
- fa323c2 Ignore node modules
- 2475b37 Move static assets to it's own directory inside static folder
- 987dd5d Remove hash from url on modal close
- 455495c Remove node_modules
- 0c02ce0 Reorg asset generation in subpackages

## 1.0.0

- c91852b Cookie consent in website
- 9865012 Do not install build tools
- e280d31 Docker login
- 3229dc2 Ensure building static binaries
- 01e62b6 Error on test
- 69380f5 Ignore system files
- a02a1ab Release with docker
- e795210 Remove old dockerignore entries
- c9c692c Remove unmarshalTags from dkron.go
- c5f5de0 Report errors on unmarshal config
- 62e1e15 Sums for release
- 1cf235a UnmarshalTags belongs to the agent and should be public
- 36f9318 Update readme
- 80b2ab1 mail-port flag is uint

## 1.0.0-rc4

- 913ee87 Bump mapstructure
- 5bd120f Remove legacy config loading
- f20fbe5 Update mods

## 1.0.0-rc3

- 4811e48 Fix UI run and delete
- 8695242 Redirect to dashboard

## 1,0.0-rc2

- d6dbb1a Add toggle to swagger
- ffa4feb Deep linking to job views
- fdc5344 Don't fury on make
- 236b5f4 Don't query jobs on interval in Dashboard
- ea5e60b Fixes rescheduling on boltdb store
- f55e2e3 Gardening and anchor links open modal
- b22b362 Gen
- 6887c36 Logging info
- d21cf16 Logging info and use store.Backend type instead of strings in config
- 28c130b Open modal with anchor links and gardening
- 1afb3df Several ui fixes introduced when migrating to glyphicons


## 1.0.0-rc1

- ef86e13 Bump go-plugin
- d09b942 Bump several dependencies
- f96d622 GRPC
- 8e3b4b9 Ignore dist folder
- 1b7d4bc Issue template
- caf4711 Logrus
- 5821c8c Mainly etcd
- 33a12c5 Revert "Bump several dependencies"
- fb9460d Update cron-spec.md
- 706e65d Upgrade pflag

## 0.11.3

- 723326f Add logging for pending executions response
- df76e9c Add real examples to swagger spec
- d1318a1 Add tags param to swagger spec
- 4da0b3b Big docs refactor
- 2d91a5e Break on errors
- 8fac831 Command to generate cli docs from cobra
- bdcd09c Don't use swagger2markup
- 253fe57 ECS and email pro docs
- e89b353 Expvar dep
- 187190e Fix indentation
- c8320b5 Fix testing
- 9037d65 Fix typo in getting started
- 9c60fe8 Formatting
- f11ed84 Formatting
- 20be8e5 Integrate swagger-ui for a bit better API visualization
- 2cede00 Merge branch 'master' into boltdb
- 53d8464 Only query for pending executions when there is some
- 712be35 Remove extra useless locking introduced in 88c072c
- dacb379 This should be TrimSuffix
- dec6701 Update contacts
- c21e565 Update getting-started.md
- 3fdba5f Use boltdb as default storage
- 70d9229 Wrong dash in example config file
- 9653bbc expvars are back and simple health endpoint

## 0.11.2

- 7d88742 Add code of conduct
- aed2f44 Proper serf debug logging
- 1226c93 Publish docker
- a0b6f59 Publish docker
- f1aaecc Reorg imports
- 8758bac Tests should use etcdv3
- fa3aaa4 Tests should use v3 client
- 5bcea4c Update create or update job api endpoint
- 39728d0 refactor: Methond name
- 1c64da4 refactor: Proper gin logging and mode

## 0.11.1 (2018-10-07)

 - Add support for passing payload to command STDIN (@gustavosbarreto)
 - add support for etcdv3 (@kevynhale)
 - Use etcdv3 by default
 - Jobs static URLs fixed

## 0.11.0 (2018-09-24)

- 1.11 stable not in docker hub yet
- Add builtin http plugin
- Add executor shell su option (@tengattack)
- Better dockerfile for testing
- Better flag help
- Don't depend on michellh/cli
- Filter jobs by tags (@digitalcrab)
- Fix cluster panic bug (@tengattack)
- Release with goreleaser
- Use cobra for flags
- Use go modules
- add create & update job features (@wd1900)

## 0.10.4 (2018-07-30)

- Replace RPC with gRPC
- Fix compose files (@kevynhale)

## 0.10.3 (2018-06-20)

- Replace goxc with makefile
- Pro docs

## 0.10.2 (2018-05-23)

### Bug fixes

- Fix status check
- Remove unnecessary updates of job finish times (@sysadmind)
- Reflect store status in API
- Fix windows plugins (@sysadmind)
- Stop Job update on JSON parse error in API (@gromo)

## 0.10.1 (2018-05-17)

### Bug fixes

- Fix dashboard job view/delete modals

## 0.10.0 (2018-05-14)

### Bug fixes

- Fix RPCconfig query missing address (#336 and related)
- Fix concurrency issue due to race condition on lock jobs [#299](https://github.com/distribworks/dkron/pull/299)
- Fix execution done missing on restart blocking concurrency forbid jobs [#349](https://github.com/distribworks/dkron/pull/349)
- Fix plugin load paths [#275](https://github.com/distribworks/dkron/pull/275)
- Fix RPC address lost on reload config [#262](https://github.com/distribworks/dkron/pull/262)

### Features and code improvements

- Slightly improve processing of last execution group (@sysadmind)
- Improve job dependencies handling (@sysadmind)
- Move dkron command to it's own package
- Milliseconds range API job create or update
- Refactor scheduler restart
- Replace bower with npm
- Executor plugins based on GRPC
- Toggle job from UI
- Search Job by name and pagination in the UI
- Add redis as storage backend (@digitalcrab)
- UI refactor with new bootstrap version and replace fontawesome with glyphicons
- Compute job status and return the value from the API providing the user with more info
- Timezone aware scheduling (@didiercrunch)


## 0.9.8 (2018-04-27)

- Fix broken release 0.9.7

## 0.9.7 (2018-02-12)

- Less verbose plugin logging
- Update broken osext dep (@ti)
- Switch from libkv to valkeyrie
- Refactor for usable core code
- Fix unsorted execution groups (@firstway)
- Fix GetLastExecutionGroup (@firstway)

## 0.9.6 (2017-11-14)

- Migrate from glide to dep
- Fix params precedence, cli params on top
- More robust test suite
- Gin logging to common logger
- Better systemd script
- Don't panic or fatal when sending notification
- Serf upgrade
- Fix templating breaking change on Go 1.9 upgrade

## 0.9.5 (2017-09-12)

Features

- New docs website using hugo

Bug fixes:

- Clean up clients upon an exit signal (@danielhan)
- Fix #280 (@didiecrunch)
- Upgrade several dependencies
- Fix static assets relative path

## 0.9.4 (2017-06-07)

- Fix mistakes in API docs
- Using "jobs", "1am" or "1pm" in the name of job leads to a dashboard bug
- Fix crash on non existent plugin name
- Embed all assets in binary, removed -ui-dir config param

### Upgrade notes

This is a breaking change; `ui-dir` configuration param has been removed, all scripting using this param should be updated.
## 0.9.3 (2017-02-12)

- Fix RPC server listen address (@firstway)
- Basic implementation of the testing infrastructure using swarm
- Basic Telemetry implementation for sending metrics to statsd and datadog
- Fix crash on backend failure
- Reverse sort executions in UI (@Eyjafjallajokull)

## 0.9.2 (2016-12-28)

Features:

- Implement concurrency policy
- Improved UI: allow delete jobs from UI, highlight JSON
- Execution Processor plugins, allows flexible routing of execution results
- Template variables for customization of notification emails (@oldmantaiter)
- Go 1.7
- Test with docker-compose, this will allow to test multiple stores easily

Bug fixes:

- Fix tests randomly failing (@oldmantaiter)
- Return empty list when no jobs instead of null
- Allow POST usage on /leave method, deprecate GET

## 0.9.1 (2016-09-24)

Bug fixes:

- Fix job stats not being updated #180
- Fix zookeeper get list of executions #184
- Fix crash when deleting a job that doesn't exists #182
- Fix Travis in forks

## 0.9.0 (2016-08-24)

Features:

- Support any size jobs
- Support chained jobs
- Schedule and other job properties validation
- New site, logo and dashboard design

Bug fixes:

- Fix execution retries
- Fix executions merge by same prefix
- Fix correct HTTP status on create/update

## 0.7.3 (2016-07-12)

- One off jobs
- Added cron spec to docs
- Execution retry on failure
- Switch JSON schema spec for it's corresponding Open API spec
- Reload config
- Fix scheduling bug
- New job Status gives more info on the job execution

## 0.7.2 (2016-06-01)

- Add some helpers and bugfixes
- Add shell property to job, reintroduced the shell execution method but now it's a choice
- Add reporting node to execution reports
- Replace server tag for dkron_server and add dkron_version

### Upgrade notes

Due to the change in the internal tags `server` to `dkron_server`, you'll need to adjust job tags if they where using that tag.

## 0.7.1 (2016-05-03)

- Don't use shell call when executing commands, exploding the command line.
- Add advertise, add `advertise` option that solves joining between hosts when running docker
- Validate job size, limit to serf maximum size
- Job overwrite, now sending existing jobs doesn't overwrite non existing fields in request
- Fix for dashboard crash on non existent leader

## 0.7.0 (2016-04-10)

- Refactor leader election, the old method could lead to cases where 2 or more nodes could have the scheduler running without noticing the other master.
- Get rid of `keys`, in a serf cluster node names are unique so using it for leader keys now.
- Fix [#85](https://github.com/distribworks/dkron/issues/85) Restart scheduler on job deletion
- Refactor logging, replace `debug` with `log-level`
- Order nodes in UI [#81](https://github.com/distribworks/dkron/issues/81) (kudos @whizz)
- Add exposed vars to easy debugging
- Go 1.6
- Add @minutely as predefined schedule (kudos @mlafeldt)

### Upgrade from 0.6.x

To upgrade an existing installation you must first delete the pre-exiting leader key from the store. The leader key is in the form of: `[keyspace]/leader`

## 0.6.4 (2016-02-18)

- Use expvars to expose metrics
- fix https://github.com/distribworks/dkron/issues/71
- Better example config in package and docs

## 0.6.3 (2015-12-28)

- UI: Better job view
- Logic to store only the last 100 executions

## 0.6.2 (2015-12-22)

- Fixed [#62](https://github.com/distribworks/dkron/issues/55)

## 0.6.1 (2015-12-21)

- Fixed bugs [#55](https://github.com/distribworks/dkron/issues/55), [#52](https://github.com/distribworks/dkron/issues/52), etc.
- Build for linux arm

## 0.6.0 (2015-12-11)

- Some other improvements and bug fixing
- Vendoring now using Go vendor experiment + glide
- Fix: Remove executions on job delete
- Show full execution output in UI modal
- New executions results internals using RPC
- Standarized logging
- Show job tooltips with info
- Accept just "pretty" for formatting api requests
- Change how execution groups work to not use the directory concept.

## 0.5.5 (2015-11-19)

- More backend compatibility
- Accept just pretty for formatting api requests
- Show executions grouped in web UI
- Show job tooltips with all job JSON info in web UI
- Better alerts

## 0.5.4 (2015-11-17)

- Fix to web UI paths

## 0.5.3 (2015-11-16)

- Web UI works behind http proxy

## 0.5.2 (2015-11-09)

- Fix bug in join config parameter that rendered it unusable from config file.

## 0.5.1 (2015-11-06)

- Deb package
- Upgraded libkv to latest
- New config options (log level, web UI path)

## 0.5.0 (2015-09-27)

- Email and Webhook configurable notifications for job executions.
- Ability to encrypt serf network traffic between nodes.
- Pretty formating API responses
- UI now shows the execution status with color coding and partial execution.
- More API stability and predictability
- Provided API JSON schema, generated API docs based in the schema
- Tested on Travis
- Using Libkv allows to use different storage backends (etcd, consul, zookeeper)
- Add v1 versioning to the API routes

## 0.0.4 (2015-08-23)

- Compiled with Go 1.5
- Includes cluster nodes view in the UI

## 0.0.3 (2015-08-20)

- Initial release
