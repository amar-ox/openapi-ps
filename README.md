# OpenAPI PS Generator
Code generator for Publish/Subscribe APIs based on OpenAPI.

## Build
```bash
mvn clean package -DskipTests
```

## Generate code
```bash
java -jar .\target\pubsub-1.0-SNAPSHOT.jar generate -g <generator> -i <spec-file> -o generated-code
```

Replace `<generator>` with `pubsub-java` or `pubsub-riot`.

To try the examples, replace `<spec-file>` with `pubsub-java.yaml` or `pubsub-riot.yaml`.