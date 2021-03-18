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

5. via CURL command, send a POST request to the KIE Server.

    Assuming kieserver:kieserver is the username and password with kieserver authorization.

    `username:password`

    ```
    curl -u kieserver:kieserver -X POST "http://localhost:8080/kie-server/services/rest/server/containers/instances/HelloWorld_1.0.0-SNAPSHOT" -H "accept: application/json" -H "content-type: application/json" -d "{\"lookup\" : \"default-stateless-ksession\",\"commands\":[ { \"insert\":{ \"object\":{ \"com.newspace.helloworld.HelloWorldModel\":{ \"name\": \"Hello\", \"address\":\"1130 street\" } }, \"out-identifier\":\"item\" } }, { \"fire-all-rules\":{ } }]}"
    ```

    OR via Postman, send a POST request to the KIE Server

    ```
    URL: http://localhost:8080/kie-server/services/rest/server/containers/instances/HelloWorld_1.0.0-SNAPSHOT

    Authorization: 
        Basic Auth
            Username: kieserver
            Password: kieserver

    Headers:
        Content-Type: application/json
        Accept: application/json
    
    Body:

        {
            "lookup": "default-stateless-ksession",
            "commands": [
                {
                "insert": {
                    "object": {
                    "com.newspace.helloworld.HelloWorldModel": {
                        "name": "Hello",
                        "address": "1130 street"
                    }
                    },
                    "out-identifier": "item"
                }
                },
                {
                "fire-all-rules": {}
                }
            ]
        }

    ```

6. View JBoss Logs to validate that the sample WorkingMemoryListener worked.
    ```
    14:19:23,275 INFO  [com.example.listeners.WorkingMemoryListener] (default task-7) --> Fact inserted: type=class com.newspace.helloworld.HelloWorldModel, fact=com.newspace.helloworld.HelloWorldModel@71664494
    14:19:23,275 INFO  [stdout] (default task-9) Rule 1 Fired::Insert Object into Working Memory
    14:19:23,275 INFO  [stdout] (default task-9) Rule 2 Fired
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


5. via CURL command, send a POST request to the KIE Server.

    Assuming kieserver:kieserver is the username and password with kieserver authorization.

    `username:password`

    ```
    curl -u kieserver:kieserver -X POST "http://localhost:8080/kie-server/services/rest/server/containers/instances/HelloWorld_1.0.0-SNAPSHOT" -H "accept: application/json" -H "content-type: application/json" -d "{\"lookup\" : \"default-stateless-ksession\",\"commands\":[ { \"insert\":{ \"object\":{ \"com.newspace.helloworld.HelloWorldModel\":{ \"name\": \"Hello\", \"address\":\"1130 street\" } }, \"out-identifier\":\"item\" } }, { \"fire-all-rules\":{ } }]}"
    ```

    OR via Postman, send a POST request to the KIE Server

    ```
    URL: http://localhost:8080/kie-server/services/rest/server/containers/instances/HelloWorld_1.0.0-SNAPSHOT

    Authorization: 
        Basic Auth
            Username: kieserver
            Password: kieserver

    Headers:
        Content-Type: application/json
        Accept: application/json
    
    Body:

        {
            "lookup": "default-stateless-ksession",
            "commands": [
                {
                "insert": {
                    "object": {
                    "com.newspace.helloworld.HelloWorldModel": {
                        "name": "Hello",
                        "address": "1130 street"
                    }
                    },
                    "out-identifier": "item"
                }
                },
                {
                "fire-all-rules": {}
                }
            ]
        }
        
    ```

6. View JBoss Logs to validate that the sample RuleTraceEventListener worked.
    ```
    14:43:16,067 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) matchCreated: ==>[ActivationCreatedEvent: getActivation()=[[ Rule1 active=false ] [ [fact 0:1:278567249:278567249:1:DEFAULT:NON_TRAIT:com.newspace.helloworld.HelloWorldModel:com.newspace.helloworld.HelloWorldModel@109a9951] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,069 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) beforeMatchFired: ==>[BeforeActivationFiredEvent:  getActivation()=[[ Rule1 active=false ] [ [fact 0:1:278567249:278567249:1:DEFAULT:NON_TRAIT:com.newspace.helloworld.HelloWorldModel:com.newspace.helloworld.HelloWorldModel@109a9951] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,071 INFO  [stdout] (default task-9) Rule 1 Fired::Insert Object into Working Memory
    14:43:16,073 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) afterMatchFired: ==>[AfterActivationFiredEvent: getActivation()=[[ Rule1 active=false ] [ [fact 0:1:278567249:278567249:1:DEFAULT:NON_TRAIT:com.newspace.helloworld.HelloWorldModel:com.newspace.helloworld.HelloWorldModel@109a9951] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,074 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) matchCreated: ==>[ActivationCreatedEvent: getActivation()=[[ Rule2 active=false ] [ [fact 0:0:795934628:1306428912:0:DEFAULT:NON_TRAIT:org.drools.core.reteoo.InitialFactImpl:org.drools.core.reteoo.InitialFactImpl@4dde85f0] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,074 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) beforeMatchFired: ==>[BeforeActivationFiredEvent:  getActivation()=[[ Rule2 active=false ] [ [fact 0:0:795934628:1306428912:0:DEFAULT:NON_TRAIT:org.drools.core.reteoo.InitialFactImpl:org.drools.core.reteoo.InitialFactImpl@4dde85f0] ] ], getKnowledgeRuntime()=KieSession[0]]
    14:43:16,076 INFO  [stdout] (default task-9) Rule 2 Fired
    14:43:16,076 INFO  [com.example.listener.RuleTraceEventListener] (default task-9) afterMatchFired: ==>[AfterActivationFiredEvent: getActivation()=[[ Rule2 active=false ] [ [fact 0:0:795934628:1306428912:0:DEFAULT:NON_TRAIT:org.drools.core.reteoo.InitialFactImpl:org.drools.core.reteoo.InitialFactImpl@4dde85f0] ] ], getKnowledgeRuntime()=KieSession[0]]
    ```

### DMNTraceEventListener
   
DMNEventListener is not enabled in DecisionManager by default and must be enabled.

1. For Demo Purposes, create a `Traffic Violation` project from the Decision Manager provided sample projects. Click the Caret right of 'Add Project' -> Try Sample -> Select `Traffic_Violation` -> Ok.

2. Add the Event Listeners project as a dependency. Inside Decision Manager, go to Traffic_Violation -> Settings -> Dependencies -> 'Add a Dependency'

Fill in the Values where needed:

- GroupId = `com.example.listeners`
- ArtifactId = `event-listeners`
- Version = `1.0.0-SNAPSHOT`

Check `Whitelist all Packages`

Save Settings

3. Add the DMNTraceEventListener Event Listener to the project. Inside settings for Traffic_Violation, go to -> Deployments -> Event listeners -> Add Event Listener.

Fill in Values:
- Name `com.example.listeners.DMNTraceEventListener`
- Resolver Type `Reflection`
- Done
- Save Settings

4. Enable DMNEventListener for this project by updating the kmodule.xml. 

-> Go to the `Traffic_Violation` project

-> Inside the project, click on any created asset (this can be a DRL, Data Bojects, or Test Scenario file that is ALREADY created inside the Traffic Violation project)

-> Click on the top left hand Caret underneath `Spaces`, Project explorer should come up.

-> Click the Settings icon (6 Options should come up - Project View, Repository View, Show as Links, etc.)

-> Click Repository View

-> Go to src/main/resources/META-INF/kmodule.xml

kmodule.xml should be edited and changed to:

    ```
    <kmodule xmlns="http://www.drools.org/xsd/kmodule">
    <configuration>
        <property key="org.kie.dmn.runtime.listeners.mylistener" value="com.example.listeners.DMNTraceEventListener"/>
    </configuration>
    </kmodule>
    ```

-> Save the file


#### Testing

5. Deploy the Traffic Violation project

6. via REST API, send a CURL request to the KIE Server.

    Assuming kieserver:kieserver is the username and password with kieserver authorization.

    `username:password`

    ```
    curl -u kieserver:kieserver -X POST "http://localhost:8080/kie-server/services/rest/server/containers/traffic-violation_1.0.0-SNAPSHOT/dmn" -H "accept: application/json" -H "content-type: application/json" -d "{ \"model-namespace\" : \"https://github.com/kiegroup/drools/kie-dmn/_A4BCA8B8-CF08-433F-93B2-A2598F19ECFF\", \"model-name\" : \"Traffic Violation\", \"dmn-context\" : { \"Driver\" : { \"Points\" : 15 }, \"Violation\" : { \"Type\" : \"speed\", \"Actual Speed\" : 135, \"Speed Limit\" : 100 } }}"
    ```

    OR via Postman, send a POST request to the KIE Server

    ```
    URL: http://localhost:8080/kie-server/services/rest/server/containers/traffic-violation_1.0.0-SNAPSHOT/dmn

    Authorization: 
        Basic Auth
            Username: kieserver
            Password: kieserver

    Headers:
        Content-Type: application/json
        Accept: application/json
    
    Body:

       {
            "model-namespace": "https://github.com/kiegroup/drools/kie-dmn/_A4BCA8B8-CF08-433F-93B2-A2598F19ECFF",
            "model-name": "Traffic Violation",
            "dmn-context": {
                "Driver": {
                    "Points": 15
                },
                "Violation": {
                    "Type": "speed",
                    "Actual Speed": 135,
                    "Speed Limit": 100
                }
            }
        }  
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

