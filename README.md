# README

This project demonstrates dynamically attaching a java agent to a running process.

It consists of:
- `agent` - Module containing a trivial `Agent-Class` which is required for dynamic attach, which simply perpetually counts in a background thread.
- `app` - Module containing a trivial application, which simply counts in the main thread.
- `attacher` - Module containing logic to detect the `app` and attach the `agent` using dynamic attach.

To demonstrate, open a terminal and run the app via `./gradlew :app:run`. The app will begin counting:

```
App still running, count 0
App still running, count 1
App still running, count 2
```

In a second terminal window, run the attacher via `./gradlew :attacher:run`. The attacher will scan for the application vm and attach the agent:

```
Getting java vms.
Ignoring id: 66477; displayName: org.gradle.launcher.daemon.bootstrap.GradleDaemon 6.5.1
Ignoring id: 68044; displayName: org.gradle.wrapper.GradleWrapperMain :attacher:run
Attaching to id: 67567; displayName: com.example.Application
Loading agent to id: 67567
```

Back in the app terminal, the agent will begin running in the background of the app process:

```
App still running, count 0
App still running, count 1
App still running, count 2
App still running, count 3
App still running, count 4
App still running, count 5
App still running, count 6
App still running, count 7
App still running, count 8
App still running, count 9
App still running, count 10
App still running, count 11
App still running, count 12
Agent still running, count 0
App still running, count 13
Agent still running, count 1
App still running, count 14
Agent still running, count 2
App still running, count 15
Agent still running, count 3
```

And that's it. 