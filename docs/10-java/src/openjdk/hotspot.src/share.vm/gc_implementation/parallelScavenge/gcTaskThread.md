openjdk8/hotspot/src/share/vm/gc_implementation/parallelScavenge/gcTaskThread.cpp


## GCTaskThread
```c++
GCTaskThread::GCTaskThread(GCTaskManager* manager,
                           uint           which,
                           uint           processor_id) :
  _manager(manager),
  _processor_id(processor_id),
  _time_stamps(NULL),
  _time_stamp_index(0)
{
  if (!os::create_thread(this, os::pgc_thread))
    vm_exit_out_of_memory(0, OOM_MALLOC_ERROR, "Cannot create GC thread. Out of system resources.");

  if (PrintGCTaskTimeStamps) {
    _time_stamps = NEW_C_HEAP_ARRAY(GCTaskTimeStamp, GCTaskTimeStampEntries, mtGC);

    guarantee(_time_stamps != NULL, "Sanity");
  }
  set_id(which);
  set_name("GC task thread#%d (ParallelGC)", which);
}
```