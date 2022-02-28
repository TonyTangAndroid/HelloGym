> 2022-02-27 21:23:30.152 26629-26684/com.uber.rib.sample V/UnboundedScopedWorker: [Tony_Gym#RxComputationThreadPool-2]:UnboundedScopedWorker:11
2022-02-27 21:23:30.154 26629-26685/com.uber.rib.sample V/ProcessLifeCycleScopedWorker: [Tony_Gym#RxComputationThreadPool-3]:ProcessLifeCycleScopedWorker:11
2022-02-27 21:23:30.899 26629-26629/com.uber.rib.sample I/ProcessLifeCycleScopedWorker: [Tony_Gym#main]:logOnDispose
2022-02-27 21:23:31.154 26629-26684/com.uber.rib.sample V/UnboundedScopedWorker: [Tony_Gym#RxComputationThreadPool-2]:UnboundedScopedWorker:12


This means that by default `ProcessLifeCycleScopedWorker` will dispose when its `ScopeProvider` is constructed in default approach like the following way.

```
AndroidLifecycleScopeProvider.from(ProcessLifecycleOwner.get())
```

Instead, it should be :
```
AndroidLifecycleScopeProvider.from(ProcessLifecycleOwner.get(), Event.ON_DESTROY)
```

The new log will look like this, neither of them gets disposed.:

> 2022-02-27 21:28:07.721 28240-28280/com.uber.rib.sample V/UnboundedScopedWorker: [Tony_Gym#RxComputationThreadPool-2]:UnboundedScopedWorker:8
2022-02-27 21:28:07.721 28240-28281/com.uber.rib.sample V/ProcessLifeCycleScopedWorker: [Tony_Gym#RxComputationThreadPool-3]:ProcessLifeCycleScopedWorker:8
2022-02-27 21:28:08.722 28240-28281/com.uber.rib.sample V/ProcessLifeCycleScopedWorker: [Tony_Gym#RxComputationThreadPool-3]:ProcessLifeCycleScopedWorker:9
2022-02-27 21:28:08.722 28240-28280/com.uber.rib.sample V/UnboundedScopedWorker: [Tony_Gym#RxComputationThreadPool-2]:UnboundedScopedWorker:9