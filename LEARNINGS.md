> V/ProcessLifeCycleScopedWorker: [Tony_Gym#RxComputationThreadPool-3]:ProcessLifeCycleScopedWorker:11
> I/ProcessLifeCycleScopedWorker: [Tony_Gym#main]:logOnDispose
> V/UnboundedScopedWorker: [Tony_Gym#RxComputationThreadPool-2]:UnboundedScopedWorker:12


This means that by default `ProcessLifeCycleScopedWorker` will dispose when its `ScopeProvider` is constructed in default approach like the following way.

```
AndroidLifecycleScopeProvider.from(ProcessLifecycleOwner.get())
```

Instead, it should be :
```
AndroidLifecycleScopeProvider.from(ProcessLifecycleOwner.get(), Event.ON_DESTROY)
```

The new log will look like this, neither of them gets disposed.:

> V/UnboundedScopedWorker: [Tony_Gym#RxComputationThreadPool-2]:UnboundedScopedWorker:8
> V/ProcessLifeCycleScopedWorker: [Tony_Gym#RxComputationThreadPool-3]:ProcessLifeCycleScopedWorker:8
> V/ProcessLifeCycleScopedWorker: [Tony_Gym#RxComputationThreadPool-3]:ProcessLifeCycleScopedWorker:9
> V/UnboundedScopedWorker: [Tony_Gym#RxComputationThreadPool-2]:UnboundedScopedWorker:9