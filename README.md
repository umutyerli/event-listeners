# event-listeners

Event Listeners for Decision Manager
- WorkingMemoryListener (Rule Runtime Event Listener)
- RuleTraceEventListener (Agenda Event Listener)
- DMNTraceEventListener (DMN Event listener)

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
    14:19:23,275 INFO  [stdout] (default task-9) Insert Object
    14:19:23,275 INFO  [stdout] (default task-9) Agenda Rule Output
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
    14:43:16,067 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) matchCreated: ==>[ActivationCreatedEvent: getActivation()=[[ Rule1 active=false ] [ [fact 0:1:278567249:278567249:1:DEFAULT:NON_TRAIT:com.newspace.helloworld.HelloWorldModel:com.newspace.helloworld.HelloWorldModel@109a9951] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,069 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) beforeMatchFired: ==>[BeforeActivationFiredEvent:  getActivation()=[[ Rule1 active=false ] [ [fact 0:1:278567249:278567249:1:DEFAULT:NON_TRAIT:com.newspace.helloworld.HelloWorldModel:com.newspace.helloworld.HelloWorldModel@109a9951] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,071 INFO  [stdout] (default task-9) Insert Object
    14:43:16,073 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) afterMatchFired: ==>[AfterActivationFiredEvent: getActivation()=[[ Rule1 active=false ] [ [fact 0:1:278567249:278567249:1:DEFAULT:NON_TRAIT:com.newspace.helloworld.HelloWorldModel:com.newspace.helloworld.HelloWorldModel@109a9951] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,074 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) matchCreated: ==>[ActivationCreatedEvent: getActivation()=[[ Rule2 active=false ] [ [fact 0:0:795934628:1306428912:0:DEFAULT:NON_TRAIT:org.drools.core.reteoo.InitialFactImpl:org.drools.core.reteoo.InitialFactImpl@4dde85f0] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,074 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) beforeMatchFired: ==>[BeforeActivationFiredEvent:  getActivation()=[[ Rule2 active=false ] [ [fact 0:0:795934628:1306428912:0:DEFAULT:NON_TRAIT:org.drools.core.reteoo.InitialFactImpl:org.drools.core.reteoo.InitialFactImpl@4dde85f0] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,076 INFO  [stdout] (default task-9) Agenda Rule Output
    14:43:16,076 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) afterMatchFired: ==>[AfterActivationFiredEvent: getActivation()=[[ Rule2 active=false ] [ [fact 0:0:795934628:1306428912:0:DEFAULT:NON_TRAIT:org.drools.core.reteoo.InitialFactImpl:org.drools.core.reteoo.InitialFactImpl@4dde85f0] ] ], getKnowledgeRuntime()=KieSession[0]]
    ```

### DMNTraceEventListener
   
DMNEventListener is not enabled in DecisionManager by default and must be enabled.

DMNEventListener can be enabled in two ways:
- Start standalone.sh with an `org.kie.dmn.runtime.listeners` property value. ```./standalone.sh -c standalone-full.xml -Dorg.kie.dmn.runtime.listeners.mylistener=com.example.listeners.DMNTraceEventListener```
- Add a `org.kie.dmn.runtime.listeners` property value to project configuration.

For this demo purpose, the DMN Event Listener will be enabled via the jboss start up property value.

1. Start standalone.sh with an `org.kie.dmn.runtime.listeners` property value. ```./standalone.sh -c standalone-full.xml -Dorg.kie.dmn.runtime.listeners.mylistener=com.example.listeners.DMNTraceEventListener```

2. For Demo Purposes, create a `Traffic Violation` project from the Decision Manager provided sample projects. Click the Caret right of 'Add Project' -> Try Sample -> Select `Traffic_Violation` -> Ok.

3. Add the Event Listeners project as a dependency. Inside Decision Manager, go to Traffic_Violation -> Settings -> Dependencies -> 'Add a Dependency'

Fill in the Values where needed:

- GroupId = `com.example.listeners`
- ArtifactId = `event-listeners`
- Version = `1.0.0-SNAPSHOT`

Check `Whitelist all Packages`

Save Settings

4. Add the DMNTraceEventListener Event Listener to the project. Inside settings for Traffic_Violation, go to -> Deployments -> Event listeners -> Add Event Listener.

Fill in Values:
- Name `com.example.listeners.DMNTraceEventListener`
- Resolver Type `Reflection`
- Done
- Save Settings


#### Testing

5. Deploy the Traffic Violation project

6. via REST API, send a CURL request to the KIE Server.

    ```
    curl -u kieserver:kieserver -X POST "http://localhost:8080/kie-server/services/rest/server/containers/traffic-violation_1.0.0-SNAPSHOT/dmn" -H "accept: application/json" -H "content-type: application/json" -d "{ \"model-namespace\" : \"https://github.com/kiegroup/drools/kie-dmn/_A4BCA8B8-CF08-433F-93B2-A2598F19ECFF\", \"model-name\" : \"Traffic Violation\", \"dmn-context\" : { \"Driver\" : { \"Points\" : 15 }, \"Violation\" : { \"Type\" : \"speed\", \"Actual Speed\" : 135, \"Speed Limit\" : 100 } }}"
    ```

7. View JBoss Logs to validate that the sample DMNTraceEventListener worked.
    ```
    15:24:33,443 INFO  [com.example.listeners.DMNTraceEventListener] (default task-9) beforeEvaluateDecision: BeforeEvaluateDecisionEvent{ name='Fine' id='_4055D956-1C47-479C-B3F4-BAEB61F1C929' }
    15:24:33,445 INFO  [com.example.listeners.DMNTraceEventListener] (default task-9) beforeEvaluateDecisionTable: BeforeEvaluateDecisionTableEvent{ nodeName='Fine' decisionTableName='Fine' }
    15:24:33,456 INFO  [com.example.listeners.DMNTraceEventListener] (default task-9) afterEvaluateDecisionTable: AfterEvaluateDecisionTableEvent{ nodeName='Fine' decisionTableName='Fine' matches=[2] fired=[2] }
    15:24:33,457 INFO  [com.example.listeners.DMNTraceEventListener] (default task-9) beforeEvaluateDecision: BeforeEvaluateDecisionEvent{ name='Should the driver be suspended?' id='_8A408366-D8E9-4626-ABF3-5F69AA01F880' }
    15:24:33,457 INFO  [com.example.listeners.DMNTraceEventListener] (default task-9) beforeEvaluateContextEntry: BeforeEvaluateContextEntryEventImpl{nodeName='Should the driver be suspended?', variableName='Total Points', variableId='_09385E8D-68E0-4DFD-AAD8-141C15C96B71', expressionId='_F1BEBF16-033F-4A25-9523-CAC23ACC5DFC'}
    15:24:33,459 INFO  [com.example.listeners.DMNTraceEventListener] (default task-9) afterEvaluateContextEntry: AfterEvaluateContextEntryEventImpl{nodeName='Should the driver be suspended?', variableName='Total Points', variableId='_09385E8D-68E0-4DFD-AAD8-141C15C96B71', expressionId='_F1BEBF16-033F-4A25-9523-CAC23ACC5DFC', expressionResult=22}
    15:24:33,459 INFO  [com.example.listeners.DMNTraceEventListener] (default task-9) beforeEvaluateContextEntry: BeforeEvaluateContextEntryEventImpl{nodeName='Should the driver be suspended?', variableName='__RESULT__', variableId='null', expressionId='_1929D813-B1C9-43C5-9497-CE5D8B2B040C'}
    15:24:33,460 INFO  [com.example.listeners.DMNTraceEventListener] (default task-9) afterEvaluateContextEntry: AfterEvaluateContextEntryEventImpl{nodeName='Should the driver be suspended?', variableName='__RESULT__', variableId='null', expressionId='_1929D813-B1C9-43C5-9497-CE5D8B2B040C', expressionResult=Yes}
    ```

