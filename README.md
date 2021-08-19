# lwe-producer

This Kotlin application exposes an endpoint to generate random instances of the LWE problem. 

First clone the repo!

Enter the Producer folder from current directory
```
cd  lwe-producer
cd Producer
```

Build the Kotlin application with Gradle
```
./gradle clean build
```

Run the application

```
java -jar build/libs/Producer-0.0.1-SNAPSHOT.jar
```

Use the endpoint in Python3! All you need is to pip install requests
```
pip install requests
```
and the following script will supply you with an instance of LWE:

```
import json
import requests

url = "http://localhost:8080/lwe/samples/NUM_OF_SAMPLE/dim/DIM/q/Q/sigma/SIGMA/dist/DISTRIBUTION"

a = json.loads(requests.get().text)
```

Capitalizations represents constants. Replace these with your own values.

Contact
```
scm@enfuse.io
me@shaunmiller.info
```

