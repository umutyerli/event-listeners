# event-listeners

Event Listeners for Decision Manager
- WorkingMemoryListener (Rule Runtime Event Listener)
- RuleTraceEventListener (Agenda Event Listener)
- DMNTraceEventListener (DMN Event listener)

Extra Event Listeners (Only for Process Automation Manager)
- ProcessTraceEventListener (Process Event Listener)

## Installing Event Listener Artifact

1. Go into the `event-listeners` directory, and install the Artifact
   ```
   cd event-listeners
   mvn clean install
   ```
   
   
## Using Event Listeners with Decision Manager

The Decision Manager instance needs to be connected to a Maven Repository that has the `com.example.listeners:event-listeners:1.0.0-SNAPSHOT` artifact.

### WorkingMemoryListener

1. For Demo Purposes, import the `HelloWorld` Project from https://github.com/umutyerli/DM-HelloWorld into Decision Manager

2. Add the Event Listeners project as a dependency. Inside Decision Manager, go to HelloWorld -> Settings -> Dependencies -> 'Add a Dependency'

Fill in the Values where needed:

- GroupId = `com.example.listeners`
- ArtifactId = `event-listeners`
- Version = `1.0.0-SNAPSHOT`

Check `Whitelist all Packages`

Save Settings

3. Add the WorkingMemoryListener Event Listener to the project. Inside settings for HelloWorld, go to -> KIE bases -> 'default-kbase KIE sessions (Click 'KIE sessions' hyperlink) -> Listeners -> Add Listener.

Fill in Values:
- Type `com.example.listeners.WorkingMemoryListener`
- Kind `Rule Runtime Event Listener`
- Done
- Save Settings

#### Testing

4. Deploy the HelloWorld project

5. via REST API, send a CURL request to the KIE Server.

```
curl -u kieserver:kieserver -X POST "http://localhost:8080/kie-server/services/rest/server/containers/instances/HelloWorld_1.0.0-SNAPSHOT" -H "accept: application/json" -H "content-type: application/json" -d "{\"commands\":[ { \"insert\":{ \"object\":{ \"com.newspace.helloworld.HelloWorldModel\":{ \"name\": \"Hello\", \"address\":\"1130 street\" } }, \"out-identifier\":\"item\" } }, { \"fire-all-rules\":{ } }]}"
```

6. View JBoss Logs to validate that the sample WorkingMemoryListener worked.
```
14:19:23,275 INFO  [com.example.listeners.WorkingMemoryListener] (default task-7) --> Fact inserted: type=class com.newspace.helloworld.HelloWorldModel, fact=com.newspace.helloworld.HelloWorldModel@71664494
```

### RuleTraceEventListener

1. For Demo Purposes, import the `HelloWorld` Project from https://github.com/umutyerli/DM-HelloWorld into Decision Manager

2. Add the Event Listeners project as a dependency. Inside Decision Manager, go to HelloWorld -> Settings -> Dependencies -> 'Add a Dependency'

Fill in the Values where needed:

- GroupId = `com.example.listeners`
- ArtifactId = `event-listeners`
- Version = `1.0.0-SNAPSHOT`

Check `Whitelist all Packages`

Save Settings

3. Add the RuleTraceEventListener Event Listener to the project. Inside settings for HelloWorld, go to -> KIE bases -> 'default-kbase KIE sessions (Click 'KIE sessions' hyperlink) -> Listeners -> Add Listener.

Fill in Values:
- Type `com.example.listeners.RuleTraceEventListener`
- Kind `Agenda Event Listener`
- Done
- Save Settings

#### Testing

4. Deploy the HelloWorld project

5. via REST API, send a CURL request to the KIE Server.

```
curl -u kieserver:kieserver -X POST "http://localhost:8080/kie-server/services/rest/server/containers/instances/HelloWorld_1.0.0-SNAPSHOT" -H "accept: application/json" -H "content-type: application/json" -d "{\"lookup\" : \"default-stateless-ksession\",\"commands\":[ { \"insert\":{ \"object\":{ \"com.newspace.helloworld.HelloWorldModel\":{ \"name\": \"Hello\", \"address\":\"1130 street\" } }, \"out-identifier\":\"item\" } }, { \"fire-all-rules\":{ } }]}"
```

6. View JBoss Logs to validate that the sample RuleTraceEventListener worked.
```

```

### DMNTraceEventListener
   
DMNEventListener is not enabled in DecisionManager by default and must be enabled by either two ways:

1. Start standalone.sh with an `KIE DMN` property value. ```./standalone.sh -c standalone-full.xml -Dorg.kie.dmn.runtime.listeners.mylistener=com.example.listeners.DMNTraceEventListener```

2. 

